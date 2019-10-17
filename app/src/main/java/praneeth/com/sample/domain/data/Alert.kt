package praneeth.com.sample.domain.data


data class Alert(
    val description: String,
    val expires: Double,
    val regions: List<String>,
    val severity: String,
    val time: Double,
    val title: String,
    val uri: String
)