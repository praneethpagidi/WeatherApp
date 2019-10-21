package praneeth.com.sample.domain.service

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import praneeth.com.sample.domain.adapters.ForecastListAdapater
import praneeth.com.sample.domain.data.ForecastResponse
import praneeth.com.sample.domain.data.WeeklyForecastListData
import praneeth.com.sample.domain.mappers.ForecastDataMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Praneeth on 2019-10-21.
 */

class WeatherServiceController(val forecast_list: RecyclerView) : Callback<ForecastResponse> {

    override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
        Log.d("Error", t.message)
    }


    override fun onResponse(call: Call<ForecastResponse>, response: Response<ForecastResponse>) {
        if (response.isSuccessful && response.body() != null) {
            val weeklyForecastData = WeeklyForecastListData(ForecastDataMapper().convertToWeeklyForecastList(response.body()!!))
            val dailyForecastData = ForecastDataMapper().convertToDailyData(response.body()!!)

            forecast_list.adapter = ForecastListAdapater(weeklyForecastData.weeklyForecastList)
        }
    }
}
