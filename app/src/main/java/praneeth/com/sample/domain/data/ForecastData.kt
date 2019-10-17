package praneeth.com.sample.domain.data

data class ForecastData(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val currently: Currently,
    val minutely: Minutely,
    val hourly: Hourly,
    val daily: Daily,
    val flags: Flags,
    val offset: Long
)