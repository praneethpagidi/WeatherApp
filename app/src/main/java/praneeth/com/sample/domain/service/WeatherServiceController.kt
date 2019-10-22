package praneeth.com.sample.domain.service

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import org.jetbrains.anko.find
import praneeth.com.sample.R
import praneeth.com.sample.domain.adapters.ForecastListAdapater
import praneeth.com.sample.domain.data.DailyForecastData
import praneeth.com.sample.domain.data.ForecastResponse
import praneeth.com.sample.domain.data.WeeklyForecastListData
import praneeth.com.sample.domain.mappers.ForecastDataMapper
import praneeth.com.sample.domain.mappers.ForecastImageMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Praneeth on 2019-10-21.
 */

class WeatherServiceController(
    val day_container: MaterialCardView,
    val forecast_list: RecyclerView
) : Callback<ForecastResponse> {

    override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
        Log.d("Error", t.message)
    }


    override fun onResponse(call: Call<ForecastResponse>, response: Response<ForecastResponse>) {
        if (response.isSuccessful && response.body() != null) {
            val weeklyForecastData =
                WeeklyForecastListData(ForecastDataMapper().convertToWeeklyForecastList(response.body()!!))
            val dailyForecastData = ForecastDataMapper().convertToDailyData(response.body()!!)

            bindDataToUi(dailyForecastData, weeklyForecastData)
        }
    }

    private fun bindDataToUi(
        dailyForecastData: DailyForecastData,
        weeklyForecastData: WeeklyForecastListData
    ) {
        day_container.find<TextView>(R.id.tv_cityName).setText("Houston")
        day_container.find<ImageView>(R.id.iv_weatherImage)
            .setImageDrawable(
                ContextCompat.getDrawable(
                    day_container.context
                    , ForecastImageMapper.getImage(dailyForecastData.icon)
                )
            )
        day_container.find<TextView>(R.id.tv_currentDescription)
            .setText(dailyForecastData.description)
        day_container.find<TextView>(R.id.tv_rainChance).setText(dailyForecastData.chanceOfRain)
        day_container.find<TextView>(R.id.tv_windSpeed).setText(dailyForecastData.windSpeed)
        day_container.find<TextView>(R.id.tv_currentTemp)
            .setText(dailyForecastData.currentTemperature)

        forecast_list.adapter = ForecastListAdapater(weeklyForecastData.weeklyForecastList)
    }
}
