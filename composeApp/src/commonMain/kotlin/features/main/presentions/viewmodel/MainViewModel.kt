package features.main.presentions.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import common.ResultState
import common.paging.DefaultPaginator
import features.main.data.repository.MainRepository
import features.main.domain.NewsModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import networking.util.NetworkError
import networking.util.Result
import networking.util.onError
import networking.util.onSuccess

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {
    companion object {
        const val PAGE_SIZE = 10;
    }

    var state by mutableStateOf(ScreenState())

    private val paginator = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdated = {
            state = state.copy(isListLoading = it, isLoading = false)
        },
        onRequest = { nextPage ->
            repository.getLastNews(nextPage, PAGE_SIZE)
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            state = state.copy(error = it?.message, isLoading = false)
        },
        onSuccess = { items, newKey ->
            state = state.copy(
                items = state.items + items,
                page = newKey,
                endReached = items.isEmpty(),
                isLoading = false
            )
        }
    )

    init {
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            paginator.loadNextItems()
        }
    }

    private val _getLastNews =
        MutableStateFlow<ResultState<Result<List<NewsModel>, NetworkError>>>(ResultState.Loading)
    val getLastNews: StateFlow<ResultState<Result<List<NewsModel>, NetworkError>>> =
        _getLastNews.asStateFlow()


    fun getLastNews() {
        viewModelScope.launch {
            _getLastNews.value = ResultState.Loading
            try {
                repository.getLastNews(
                    pageSize = PAGE_SIZE,
                    page = 1
                ).onError {
                    _getLastNews.value = ResultState.Success(Result.Error(it))
                }.onSuccess {
                    _getLastNews.value = ResultState.Success(Result.Success(it))
                }
            } catch (e: Exception) {
                _getLastNews.value = ResultState.Error(e)
            }
        }
    }
}

data class ScreenState(
    val isLoading: Boolean = false,
    val isListLoading: Boolean = false,
    val items: List<NewsModel> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
)