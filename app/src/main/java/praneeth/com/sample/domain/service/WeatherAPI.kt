package praneeth.com.sample.domain.service

import android.util.Log
import com.google.gson.Gson
import praneeth.com.sample.domain.data.ForecastData
import java.net.URL

class WeatherAPI {

    companion object {
        private const val BASE_URL = "https://api.darksky.net/forecast/"
        private const val API_KEY = "b88955ec4cc1ea841e29d0114e158fc4"
        private const val COMPLETE_URL = "$BASE_URL$API_KEY/"
    }

    fun getWeatherForCurrentLocation(longitude: String, latitude: String): ForecastData {
        val link = COMPLETE_URL + "$latitude,$longitude"
        val forecastJsonStr = URL(link).readText()
        //log the response
        Log.d("Response", forecastJsonStr)

        //deserialise the response here
        return Gson().fromJson(forecastJsonStr, ForecastData::class.java)
    }
}
