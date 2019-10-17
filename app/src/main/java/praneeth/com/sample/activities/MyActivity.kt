package praneeth.com.sample.activities

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import praneeth.com.sample.R
import praneeth.com.sample.domain.adapters.ForecastListAdapater
import praneeth.com.sample.domain.service.LocationService

class MyActivity : AppCompatActivity() {
    private var googleApiClient: GoogleApiClient? = null
    private val context = this

    private val LOCATION_REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        googleApiClient = GoogleApiClient.Builder(context)
            .addApi(LocationServices.API)
            .build()

        doAsync {
            LocationService(context).getCurrentLocation(googleApiClient)
        }

        val forecast_list: RecyclerView = find(R.id.forecast_list)
        forecast_list.layoutManager = LinearLayoutManager(context)
        forecast_list.adapter = ForecastListAdapater(items)
    }


    override fun onStart() {
        super.onStart()

        googleApiClient?.connect()
    }

    override fun onStop() {
        super.onStop()

        googleApiClient?.disconnect()
    }

    private val items = listOf(
        "Mon 6/23 - Sunny - 31/17",
        "Tue 6/24 - Foggy - 21/8",
        "Wed 6/25 - Cloudy - 22/17",
        "Thurs 6/26 - Rainy - 18/11",
        "Fri 6/27 - Foggy - 21/10",
        "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
        "Sun 6/29 - Sunny - 20/7"
    )

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            LOCATION_REQUEST_CODE -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LocationService(context).getCurrentLocation(googleApiClient)
            } else
                Toast.makeText(context, "Location permission denied", Toast.LENGTH_SHORT).show()
        }
    }
}

