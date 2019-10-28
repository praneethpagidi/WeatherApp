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
class ForecastListAdapter(private val items: List<WeekForecast>, private val clickListener: ListItemClickListener) :
    RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.weekly_container, parent, false)
        return ViewHolder(view, clickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    class ViewHolder(view: View, private val clickListener: ListItemClickListener) : RecyclerView.ViewHolder(view) {
        private val tvDay: TextView = view.find(R.id.tv_day)
        private val ivWeather: ImageView = view.find(R.id.iv_weatherIcon)
        private val tvTemphigh: TextView = view.find(R.id.tv_highTemp)
        private val tvTemplow: TextView = view.find(R.id.tv_lowTemp)
        private val tvDescription: TextView = view.find(R.id.tv_description)

        fun bindData(weekForecast: WeekForecast) {
            with(weekForecast) {
                tvDay.text = day
                tvTemphigh.text = high
                tvTemplow.text = low
                tvDescription.text = description
                ivWeather.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        ForecastImageMapper.getImage(icon)
                    )
                )
                itemView.setOnClickListener{clickListener(this)}
            }
        }
    }

    interface ListItemClickListener {
        operator fun invoke(weekForecast: WeekForecast)
    }
}