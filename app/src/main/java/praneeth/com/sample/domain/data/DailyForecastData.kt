package praneeth.com.sample.domain.data

/**
 * Created by Praneeth on 2019-10-18.
 */

data class DailyForecastData(
    val description: String,
    val currentTemperature : String,
    val chanceOfRain : String,
    val windSpeed: String,
    val icon: String
)