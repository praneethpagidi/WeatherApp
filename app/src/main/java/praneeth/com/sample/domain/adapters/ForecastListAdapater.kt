package praneeth.com.sample.domain.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.find
import praneeth.com.sample.R
import praneeth.com.sample.domain.data.WeekForecast
import praneeth.com.sample.domain.mappers.ForecastImageMapper


/**
 * Created by Praneeth on 2019-10-15.
 */
class ForecastListAdapater(val items: List<WeekForecast>) :
    RecyclerView.Adapter<ForecastListAdapater.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.weekly_container, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_day: TextView = view.find(R.id.tv_day)
        val iv_weather: ImageView = view.find(R.id.iv_weatherIcon)
        val tv_tempHigh: TextView = view.find(R.id.tv_highTemp)
        val tv_tempLow: TextView = view.find(R.id.tv_lowTemp)
        val tv_description: TextView = view.find(R.id.tv_description)

        fun bindData(weekForecast: WeekForecast) {
            with(weekForecast) {
                tv_day.text = day
                tv_tempHigh.text = high
                tv_tempLow.text = low
                tv_description.text = description
                iv_weather.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        ForecastImageMapper.getImage(icon)
                    )
                )
            }
        }
    }
}