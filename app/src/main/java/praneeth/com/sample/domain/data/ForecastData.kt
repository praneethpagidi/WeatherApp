package praneeth.com.sample.domain.data

/**
 * Created by Praneeth on 2019-10-18.
 */
data class ForecastData(
    val dailyData : DailyForecastData,
    val weeklyData : WeeklyForecastListData
)