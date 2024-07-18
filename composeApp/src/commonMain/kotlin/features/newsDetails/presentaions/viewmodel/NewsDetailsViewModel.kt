package features.newsDetails.presentaions.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import common.ResultState
import features.newsDetails.data.repository.NewsDetailsRepository
import features.newsDetails.domain.NewsModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import networking.util.NetworkError
import networking.util.Result
import networking.util.onError
import networking.util.onSuccess

class NewsDetailsViewModel(
    private val repository: NewsDetailsRepository
) : ViewModel() {

    private var id: Long? = null

    private val _getNewsDetails =
        MutableStateFlow<ResultState<Result<NewsModel, NetworkError>>>(ResultState.Loading)
    val getNewsDetails: StateFlow<ResultState<Result<NewsModel, NetworkError>>> =
        _getNewsDetails.asStateFlow()

    init {
        id?.let {
            loadingNewsDetaild()
        }
    }

    fun setId(id: Long) {
        this.id = id
    }

    fun loadingNewsDetaild() {
        viewModelScope.launch {
            _getNewsDetails.value = ResultState.Loading
            try {
                repository.getNewsDetailsById(id!!).onError {
                    _getNewsDetails.value = ResultState.Success(Result.Error(it))
                }.onSuccess {
                    _getNewsDetails.value = ResultState.Success(Result.Success(it))
                }
            } catch (e: Exception) {
                _getNewsDetails.value = ResultState.Error(e)
            }
        }
    }
}

data class ScreenState(
    val isLoading: Boolean = false,
    val item: NewsModel? = null,
    val error: String? = null,
)