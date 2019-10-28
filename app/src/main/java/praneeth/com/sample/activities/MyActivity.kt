package praneeth.com.sample.activities

import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import praneeth.com.sample.R
import praneeth.com.sample.domain.Utils.API_KEY
import praneeth.com.sample.domain.commands.UICommand
import praneeth.com.sample.domain.data.DailyForecastData
import praneeth.com.sample.domain.data.WeeklyForecastListData
import praneeth.com.sample.domain.service.LocationServiceHelper
import praneeth.com.sample.domain.service.WeatherService
import praneeth.com.sample.domain.service.WeatherServiceController

class MyActivity : AppCompatActivity(), UICommand {

    private val LOCATION_REQUEST_CODE = 101

    private var googleApiClient: GoogleApiClient? = null
    private val context = this
    private val forecastApi: WeatherService by lazy { WeatherService.create() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        initGoogleApiClient()

        val locationService = LocationServiceHelper(context)
        locationService.getCurrentLocation(googleApiClient)

        forecastApi.getForecast(
            API_KEY,
            LocationServiceHelper.latitude,
            LocationServiceHelper.longitude
        )
            .enqueue(WeatherServiceController(this))
    }

    override fun onStart() {
        super.onStart()

        googleApiClient?.connect()
    }

    override fun onStop() {
        super.onStop()

        googleApiClient?.disconnect()
    }


    private fun initGoogleApiClient() {
        googleApiClient = GoogleApiClient.Builder(context)
            .addApi(LocationServices.API)
            .build()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            LOCATION_REQUEST_CODE -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LocationServiceHelper(context).getCurrentLocation(googleApiClient)
            } else
                Toast.makeText(context, "Location permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    override fun bindUI(
        dailyForecastData: DailyForecastData,
        weeklyForecastData: WeeklyForecastListData
    ) {
        ViewHelper(this, dailyForecastData, weeklyForecastData).bindUI(day_container, forecast_list)
    }


}

