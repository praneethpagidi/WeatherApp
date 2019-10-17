package praneeth.com.sample.domain.mappers


import android.util.Log
import praneeth.com.sample.domain.data.Forecast
import praneeth.com.sample.domain.data.ForecastData
import java.util.*
import kotlin.collections.ArrayList
/**
 * Created by Praneeth on 2019-10-17.
 */
class ForecastDataMapper {

    private val daysList = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

    fun convertToWeeklyForecastList(forecastData: ForecastData) : List<Forecast> {
        val forecastList: ArrayList<Forecast> = ArrayList()
        var dayCount = Calendar.getInstance()[Calendar.DAY_OF_WEEK] - 1

        for (data in forecastData.daily.data) {
            if (dayCount == 7) {
                dayCount = 0
            }

            val today = daysList.get(dayCount)
            Log.d("current day", today)
            val forecast = Forecast(today, data.summary, data.temperatureHigh.toInt(), data.temperatureLow.toInt())
            forecastList.add(forecast)
            dayCount++
        }
        return forecastList
    }
}