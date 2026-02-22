package redron.tradox.core.network.websocket.alltick

import kotlinx.serialization.Serializable

@Serializable
data class AllTickQuoteMessage(
    val type: String,
    val symbol: String? = null,
    val price: Double? = null,
    val timestamp: Long? = null
)
