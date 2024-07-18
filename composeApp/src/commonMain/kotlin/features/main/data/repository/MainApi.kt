package features.main.data.repository

import features.main.domain.ShortNewsModel
import networking.util.ErrorResponse
import networking.util.Result

interface MainApi {
    suspend fun getLastNews(page: Int, pageSize: Int): Result<List<ShortNewsModel>, ErrorResponse>
}