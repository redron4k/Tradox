package redron.tradox.core.network.common.result

suspend inline fun <T> safeApiCall(
    crossinline block: suspend () -> T
): NetworkResult<T> {
    return try {
        NetworkResult.Success(block())
    } catch (t: Throwable) {
        NetworkResult.Error(t)
    }
}
