package praneeth.com.sample.domain.service

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices

/**
 * Created by Praneeth on 2019-10-16.
 */

class LocationService(context: Context) {

    private val activity = context as Activity
    private val LOCATION_REQUEST_CODE = 101

    fun getCurrentLocation(googleApiClient: GoogleApiClient?) {
        //Checking if the location permission is granted
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION), LOCATION_REQUEST_CODE)
            return
        }
        //Fetching location using FusedLOcationProviderAPI
        val fusedLocationApi = LocationServices.FusedLocationApi
        val location = fusedLocationApi.getLastLocation(googleApiClient)

        //default lat and long - Houston
        var latitude = "29.7604"
        var longitude = "-95.3698"

        //In some rare cases Location obtained can be null
        if (location == null) {
            Log.d("LOCATION STATUS ", "Failed")
            WeatherAPI().getWeatherForCurrentLocation(longitude, latitude)
            return
        }
        else {
            Log.d("LOCATION STATUS: ", "Succesfull, the latitude is ${location.latitude}, and the longitude is ${location.longitude}")
            latitude = location.latitude.toString()
            longitude = location.longitude.toString()
        }

        WeatherAPI().getWeatherForCurrentLocation(longitude, latitude)
    }
}