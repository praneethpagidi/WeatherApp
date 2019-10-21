package praneeth.com.sample.utils

import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import praneeth.com.sample.domain.adapters.ForecastListAdapater
import praneeth.com.sample.domain.commands.RequestForecastCommand
import praneeth.com.sample.domain.service.LocationServiceHelper

/**
 * Created by Praneeth on 2019-10-18.
 */

class ServiceAndUIHelper {

    fun performServiceAndPaintUI(
        locationService: LocationServiceHelper,
        forecast_list: RecyclerView
    ) {
        doAsync {
            val result2 = RequestForecastCommand(locationService.latitude, locationService.longitude).execute()
            val weeklyData = result2.weeklyData
            uiThread {
                forecast_list.adapter = ForecastListAdapater(weeklyData.weeklyForecastList)
            }
        }
    }
}