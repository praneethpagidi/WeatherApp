package praneeth.com.sample.domain.mappers

import praneeth.com.sample.R

/**
 * Created by Praneeth on 2019-10-22.
 */
class ForecastImageMapper {
    companion object {
        private fun getDrawables(): HashMap<String, Int> {
            val drawables = hashMapOf<String, Int>()
            drawables["clear-day"] = R.drawable.sunny
            //change the below icon
//            drawables["clear-night"] = R.drawable.clear

            drawables["cloudy"] = R.drawable.cloudy
            drawables["fog"] = R.drawable.fog
            drawables["partly_cloudy_day"] = R.drawable.partly_cloudy_day
            drawables["partly_cloudy_night"] = R.drawable.partly_cloudy_night
            drawables["rain"] = R.drawable.rain
            drawables["sleet"] = R.drawable.sleet
            drawables["snow"] = R.drawable.snow
            drawables["wind"] = R.drawable.wind

            return drawables

        }

        fun getImage(iconKey: String): Int {
            val images = getDrawables()
            //default
            var image: Int = R.drawable.sunny

            for (item in images.entries) {
                if (iconKey.contentEquals(item.key))
                    image = item.value
            }
            return image
        }
    }
}