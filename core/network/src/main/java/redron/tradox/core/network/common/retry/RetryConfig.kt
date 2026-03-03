package redron.tradox.core.network.common.retry

object RetryConfig {
    const val MAX_RETRIES = 5

    const val BASE_DELAY_MS = 1000L
    const val MAX_DELAY_MS = 10_000L
}
