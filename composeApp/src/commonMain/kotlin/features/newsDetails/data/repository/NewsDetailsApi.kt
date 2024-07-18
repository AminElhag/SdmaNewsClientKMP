package features.newsDetails.data.repository

import features.newsDetails.domain.NewsModel
import networking.util.NetworkError
import networking.util.Result

interface NewsDetailsApi {
    suspend fun getNewsDetailsById(id: Long): Result<NewsModel, NetworkError>
}
