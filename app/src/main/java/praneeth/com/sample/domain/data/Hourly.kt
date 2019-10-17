package praneeth.com.sample.domain.data

data class Hourly(
    val data: List<Currently>,
    val icon: String,
    val summary: String
)