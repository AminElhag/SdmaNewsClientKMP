package features.main.data.remote

import common.util.Constant.BASE_URL
import features.main.domain.NewsModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import networking.util.NetworkError
import networking.util.Result

class MainClient(
    private val httpClient: HttpClient
) {

    suspend fun getLastNews(
        page: Int,
        pageSize: Int
    ): Result<List<NewsModel>, NetworkError> {
        val response = try {
            httpClient.get(
                BASE_URL + "api/news"
            ) {
                parameter("page", page)
                parameter("page_size", pageSize)
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when (response.status.value) {
            in 200..299 -> {
                val newsModelList = response.body<List<NewsModel>>()
                Result.Success(newsModelList)
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