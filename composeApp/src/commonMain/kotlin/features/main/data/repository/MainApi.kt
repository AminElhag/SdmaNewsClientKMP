package features.main.data.repository

import features.main.domain.NewsModel
import networking.util.NetworkError
import networking.util.Result

interface MainApi {
    suspend fun getLastNews(page: Int, pageSize: Int): Result<List<NewsModel>, NetworkError>
}