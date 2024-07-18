package features.main.data.remote

import common.util.Constant.BASE_URL
import features.main.domain.ShortNewsModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import networking.util.ErrorResponse
import networking.util.Result

class MainClient(
    private val httpClient: HttpClient
) {

    suspend fun getLastNews(
        page: Int,
        pageSize: Int
    ): Result<List<ShortNewsModel>, ErrorResponse> {
        val response = try {
            httpClient.get(
                BASE_URL + "api/news"
            ) {
                parameter("page", page)
                parameter("page_size", pageSize)
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(ErrorResponse(0, "No internet available"))
        } catch (e: SerializationException) {
            return Result.Error(
                ErrorResponse(
                    1,
                    "You’re trying to serialize an object that contains non-serializable fields"
                )
            )
        }

        if (response.status == HttpStatusCode.OK) {
            val shortNewsModelList = response.body<List<ShortNewsModel>>()
            return Result.Success(shortNewsModelList)
        } else {
            val errorResponse = response.body() as ErrorResponse
            return Result.Error(errorResponse)
        }
    }
}