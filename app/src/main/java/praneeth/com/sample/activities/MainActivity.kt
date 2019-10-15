package praneeth.com.sample.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import praneeth.com.sample.R
import praneeth.com.sample.adapters.ForecastListAdapater

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forecast_list = findViewById<RecyclerView>(R.id.forecast_list)
        forecast_list.layoutManager = LinearLayoutManager(this)
        forecast_list.adapter = ForecastListAdapater(items)
    }

    private val items = listOf(
        "sample",
        "sample",
        "sample",
        "sample",
        "sample",
        "sample",
        "sample",
        "sample"
    )
}

