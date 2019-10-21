package praneeth.com.sample.domain.commands

import praneeth.com.sample.domain.data.ForecastData
import praneeth.com.sample.domain.data.WeeklyForecastListData
import praneeth.com.sample.domain.mappers.ForecastDataMapper
import praneeth.com.sample.domain.service.WeatherRequest

/**
 * Created by Praneeth on 2019-10-18.
 */
class RequestForecastCommand(private val latitude: String,
                             private val longitude: String) : Command<ForecastData> {

    override fun execute(): ForecastData {
        val weatherResponse = WeatherRequest(latitude, longitude).execute()
        val weeklyForecastData = WeeklyForecastListData(ForecastDataMapper().convertToWeeklyForecastList(weatherResponse))
        val dailyForecastData = ForecastDataMapper().convertToDailyData(weatherResponse)
        return ForecastData(dailyForecastData, weeklyForecastData)
    }
}