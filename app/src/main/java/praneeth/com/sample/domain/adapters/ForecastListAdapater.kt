package praneeth.com.sample.domain.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.find
import praneeth.com.sample.R
import praneeth.com.sample.domain.data.WeekForecast


/**
 * Created by Praneeth on 2019-10-15.
 */
class ForecastListAdapater(val items: List<WeekForecast>) :
    RecyclerView.Adapter<ForecastListAdapater.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weekly_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(items[position]){
            holder.tv_day.text = day
            holder.tv_tempHigh.text = high.toString()
            holder.tv_tempLow.text = low.toString()
            holder.tv_description.text = description
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_day: TextView = view.find(R.id.tv_day)
        val iv_weather: ImageView = view.find(R.id.iv_weatherIcon)
        val tv_tempHigh: TextView = view.find(R.id.tv_highTemp)
        val tv_tempLow: TextView = view.find(R.id.tv_lowTemp)
        val tv_description: TextView = view.find(R.id.tv_description)

    }


}