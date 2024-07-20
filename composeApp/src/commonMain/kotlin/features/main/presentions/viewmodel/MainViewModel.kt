package features.main.presentions.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import common.paging.DefaultPaginator
import features.main.data.repository.MainRepository
import features.main.domain.ShortNewsModel
import kotlinx.coroutines.launch
import networking.util.ErrorResponse

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
            state = state.copy(isListLoading = it)
        },
        onRequest = { nextPage ->
            repository.getLastNews(nextPage, PAGE_SIZE)
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            if (it is ErrorResponse) {
                state = state.copy(error = it.message, isFirstLoad = false)
            }
        },
        onSuccess = { items, newKey ->
            state = state.copy(
                items = state.items + items,
                page = newKey,
                endReached = items.isEmpty(),
                isFirstLoad = false
            )
        }
    )

    init {
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            try {
                paginator.loadNextItems()
            } catch (e: Exception) {
                e.printStackTrace()
                state = state.copy(error = "الخادم في حالة صيانة حاليا", isFirstLoad = false)
            }
        }
    }

    fun retry() {
        paginator.reset()
        loadNextItems()
    }
}

data class ScreenState(
    val isFirstLoad: Boolean = true,
    val isListLoading: Boolean = false,
    val items: List<ShortNewsModel> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
)