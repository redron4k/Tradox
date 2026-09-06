package redron.tradox.core.network.common.result

sealed interface NetworkResult<out T> {

    data class Success<T>(
        val data: T
    ) : NetworkResult<T>

    data class Error(
        val throwable: Throwable
    ) : NetworkResult<Nothing>
}
