package praneeth.com.sample.domain.service

import praneeth.com.sample.domain.Utils.BASE_URL
import praneeth.com.sample.domain.data.ForecastResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Praneeth on 2019-10-21.
 */

interface WeatherService {

    companion object {
        fun create(): WeatherService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(WeatherService::class.java)
        }
    }

    @GET("forecast/{key}/{latitude},{longitude}")
    fun getForecast(
        @Path("key") key: String,
        @Path("latitude") latitude: String,
        @Path("longitude") longitude: String
    ): Call<ForecastResponse>
}