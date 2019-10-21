package praneeth.com.sample.domain.mappers


import android.util.Log
import praneeth.com.sample.domain.data.DailyForecastData
import praneeth.com.sample.domain.data.ForecastResponse
import praneeth.com.sample.domain.data.WeekForecast
import java.util.*
import kotlin.collections.ArrayList
/**
 * Created by Praneeth on 2019-10-17.
 */
class ForecastDataMapper {

    private val daysList = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

    fun convertToWeeklyForecastList(forecastData: ForecastResponse) : List<WeekForecast> {
        val forecastList: ArrayList<WeekForecast> = ArrayList()
        var dayCount = Calendar.getInstance()[Calendar.DAY_OF_WEEK] - 1

        for (data in forecastData.daily.data) {
            if (dayCount == 7) {
                dayCount = 0
            }

            val today = daysList.get(dayCount)
            Log.d("current day", today)
            val forecast = WeekForecast(today, data.summary, data.temperatureHigh.toInt(), data.temperatureLow.toInt())
            forecastList.add(forecast)
            dayCount++
        }
        return forecastList
    }

    fun convertToDailyData(forecastData: ForecastResponse) : DailyForecastData {
        return DailyForecastData(forecastData.currently.summary,
            forecastData.currently.temperature.toString(),
            forecastData.currently.precipProbability.toString(),
            forecastData.currently.windSpeed.toString(),
            forecastData.currently.icon)
    }
}