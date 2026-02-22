package redron.tradox.core.network.websocket.reconnect

import kotlinx.coroutines.delay
import kotlin.math.min
import kotlin.random.Random

class ReconnectPolicy {

    private val baseDelay = 1_000L
    private val maxDelay = 10_000L

    suspend fun delayBeforeRetry(attempt: Int) {
        val exponential = baseDelay * (1 shl attempt)
        val capped = min(exponential, maxDelay)
        val jitter = Random.nextLong(capped / 2, capped)
        delay(jitter)
    }
}
