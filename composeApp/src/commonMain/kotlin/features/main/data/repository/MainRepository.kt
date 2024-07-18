package features.main.data.repository

import features.main.data.remote.MainClient
import features.main.domain.ShortNewsModel
import networking.util.Error
import networking.util.ErrorResponse
import networking.util.Result

class MainRepository(
    private val mainClient: MainClient
) : MainApi {
    override suspend fun getLastNews(
        page: Int,
        pageSize: Int
    ): Result<List<ShortNewsModel>, ErrorResponse> {
        return mainClient.getLastNews(page, pageSize)
    }
}