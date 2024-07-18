package features.newsDetails.data.remote

import common.util.Constant.BASE_URL
import features.newsDetails.domain.NewsModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.path
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import networking.util.NetworkError
import networking.util.Result

class NewsDetailsClient(
    private val httpClient: HttpClient
) {

    suspend fun getNewsDetailsBuId(
        id: Long
    ): Result<NewsModel, NetworkError> {
        val response = try {
            httpClient.get(
                BASE_URL + "api/news/details"
            ) {
                parameter("news_id",id)
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when (response.status.value) {
            in 200..299 -> {
                val shortNewsModelList = response.body<NewsModel>()
                Result.Success(shortNewsModelList)
            }

            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            409 -> Result.Error(NetworkError.CONFLICT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }
}
