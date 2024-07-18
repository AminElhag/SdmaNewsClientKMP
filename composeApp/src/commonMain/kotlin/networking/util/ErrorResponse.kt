package networking.util

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("status_code")
    val statusCode: Int,
    @SerialName("message")
    val message: String
) : Error