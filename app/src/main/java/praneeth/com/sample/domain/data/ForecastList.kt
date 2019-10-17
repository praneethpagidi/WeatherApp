package praneeth.com.sample.domain.data

/**
 * Created by Praneeth on 2019-10-17.
 */
data class ForecastList (
    val city: String,
    val country: String,
    val dailyForecast: List<Forecast>
)