package common.paging

import networking.util.NetworkError
import networking.util.Result
import networking.util.onError
import networking.util.onSuccess

class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Result<List<Item>, NetworkError>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (NetworkError?) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
) : Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if (isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false
        result.onError {
            onError(it)
            onLoadUpdated(false)
            return
        }.onSuccess {
            currentKey = getNextKey(it)
            onSuccess(it, currentKey)
            onLoadUpdated(false)
        }
    }

    override fun reset() {
        currentKey = initialKey
    }
}