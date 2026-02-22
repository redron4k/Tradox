package redron.tradox.core.network.common.retry

object RetryConfig {
    const val MAX_RETRIES = 3

    const val BASE_DELAY_MS = 500L
    const val MAX_DELAY_MS = 5_000L

    const val ENABLE_JITTER = true
}
