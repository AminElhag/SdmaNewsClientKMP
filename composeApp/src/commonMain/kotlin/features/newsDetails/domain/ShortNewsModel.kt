package features.newsDetails.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsModel(
    @SerialName("id")
    val id: Long = 0,
    @SerialName("image_url")
    val imageUrl: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("update_time")
    val updateTime: String = "",
    @SerialName("content")
    val content: String = ""
)