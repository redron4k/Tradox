package redron.tradox.core.network.common.retry

import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.http.HttpStatusCode
import kotlin.math.min
import kotlin.random.Random

fun HttpRequestRetry.Configuration.configureDefaultRetry() {

    maxRetries = RetryConfig.MAX_RETRIES

    retryIf { _, response ->
        when (response.status) {
            in HttpStatusCode.InternalServerError
                    ..HttpStatusCode.InsufficientStorage -> true
            HttpStatusCode.TooManyRequests -> true
            else -> false
        }
    }

    retryOnExceptionIf { _, _ ->
        true
    }

    delayMillis { retry ->
        val exponential =
            RetryConfig.BASE_DELAY_MS * (1 shl retry)

        val capped = min(exponential, RetryConfig.MAX_DELAY_MS)

        if (RetryConfig.ENABLE_JITTER) {
            Random.nextLong(capped / 2, capped)
        } else {
            capped
        }
    }
}
