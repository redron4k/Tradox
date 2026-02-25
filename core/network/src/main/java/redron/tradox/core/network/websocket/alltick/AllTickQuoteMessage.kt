package redron.tradox.core.network.websocket.alltick

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllTickQuoteMessage(
    val cmd_id: Int,
    val data: Data
) {
    @Serializable
    data class Data(
        val code: String,
        @SerialName("price") val last_price: String? = null,
        @SerialName("tick_time") val ts: String? = null,
        val seq: String? = null,
        val volume: String? = null,
        val turnover: String? = null,
        val trade_direction: Int? = null
    )
}
