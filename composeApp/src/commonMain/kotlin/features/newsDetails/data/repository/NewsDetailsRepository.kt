package features.newsDetails.data.repository

import features.newsDetails.data.remote.NewsDetailsClient
import features.newsDetails.domain.NewsModel
import networking.util.NetworkError
import networking.util.Result

class NewsDetailsRepository(
    private val newsDetailsClient: NewsDetailsClient
):NewsDetailsApi {
    override suspend fun getNewsDetailsById(id: Long): Result<NewsModel, NetworkError> {
        return newsDetailsClient.getNewsDetailsBuId(id)
    }

}
