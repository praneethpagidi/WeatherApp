package praneeth.com.sample.domain.data

import android.util.Log
import java.net.URL

/**
 * Created by Praneeth on 2019-10-15.
 */
class Request(private val url : String) {

    fun run() {
        val forecastJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, forecastJsonStr)
    }
}