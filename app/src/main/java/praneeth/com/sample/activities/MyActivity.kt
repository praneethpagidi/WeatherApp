package praneeth.com.sample.activities

import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import org.jetbrains.anko.find
import praneeth.com.sample.R
import praneeth.com.sample.domain.service.LocationServiceHelper
import praneeth.com.sample.utils.ServiceAndUIHelper

class MyActivity : AppCompatActivity() {
    private var googleApiClient: GoogleApiClient? = null
    private val context = this

    private val LOCATION_REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        initGoogleApiClient()

        val forecast_list: RecyclerView = find(R.id.forecast_list)
        forecast_list.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        val locationService = LocationServiceHelper(context)
        locationService.getCurrentLocation(googleApiClient)

        ServiceAndUIHelper().performServiceAndPaintUI(locationService, forecast_list)
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
}

