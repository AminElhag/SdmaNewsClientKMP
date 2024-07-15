package features.main.data.repository

import features.main.data.remote.MainClient
import features.main.domain.NewsModel
import networking.util.NetworkError
import networking.util.Result

class MainRepository(
    private val mainClient: MainClient
) : MainApi {
    override suspend fun getLastNews(
        page: Int,
        pageSize: Int
    ): Result<List<NewsModel>, NetworkError> {
        return mainClient.getLastNews(page, pageSize)
    }
}