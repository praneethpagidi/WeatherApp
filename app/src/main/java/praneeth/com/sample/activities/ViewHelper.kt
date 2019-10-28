package praneeth.com.sample.activities

import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import org.jetbrains.anko.find
import praneeth.com.sample.R
import praneeth.com.sample.domain.adapters.ForecastListAdapter
import praneeth.com.sample.domain.data.DailyForecastData
import praneeth.com.sample.domain.data.WeekForecast
import praneeth.com.sample.domain.data.WeeklyForecastListData
import praneeth.com.sample.domain.mappers.ForecastImageMapper

/**
 * Created by Praneeth on 2019-10-23.
 */
const val DEFAULT_CITY = "Houston"

class ViewHelper(
    private val context: MyActivity, private val dailyForecastData: DailyForecastData,
    private val weeklyForecastData: WeeklyForecastListData
) {
    fun bindUI(day_container: MaterialCardView, forecast_list: RecyclerView) {
        initAndBindDayCard(day_container)
        initAndBindWeeklyCard(forecast_list, day_container)
    }

    private fun initAndBindWeeklyCard(
        forecast_list: RecyclerView,
        day_container: MaterialCardView
    ) {
        forecast_list.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        forecast_list.adapter = ForecastListAdapter(
            weeklyForecastData.weeklyForecastList,
            object : ForecastListAdapter.ListItemClickListener {
                override fun invoke(weekForecast: WeekForecast) {
                    Toast.makeText(day_container.context, "item click", Toast.LENGTH_LONG).show()
                }
            })
    }

    private fun initAndBindDayCard(day_container: MaterialCardView) {
        day_container.find<TextView>(R.id.tv_cityName).text = DEFAULT_CITY
        day_container.find<ImageView>(R.id.iv_weatherImage)
            .setImageDrawable(
                ContextCompat.getDrawable(
                    day_container.context
                    , ForecastImageMapper.getImage(dailyForecastData.icon)
                )
            )
        day_container.find<TextView>(R.id.tv_currentDescription)
            .setText(dailyForecastData.description)
        day_container.find<TextView>(R.id.tv_rainChance).text = dailyForecastData.chanceOfRain
        day_container.find<TextView>(R.id.tv_windSpeed).text = dailyForecastData.windSpeed
        day_container.find<TextView>(R.id.tv_currentTemp).text = dailyForecastData.currentTemperature
    }
}