package praneeth.com.sample.domain.commands

import praneeth.com.sample.domain.data.DailyForecastData
import praneeth.com.sample.domain.data.WeeklyForecastListData

/**
 * Created by Praneeth on 2019-10-23.
 */
interface UICommand {
    fun bindUI(
        dailyForecastData: DailyForecastData,
        weeklyForecastData: WeeklyForecastListData
    )
}