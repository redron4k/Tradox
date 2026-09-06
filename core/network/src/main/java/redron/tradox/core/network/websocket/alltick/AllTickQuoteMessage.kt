package redron.tradox.core.network.websocket.alltick

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllTickQuoteMessage(
    @SerialName("cmd_id") val cmdId: Int,
    @SerialName("data") val data: Data,
)

@Serializable
data class Data(
    @SerialName("code") val code: String,
    @SerialName("price") val price: String? = null,
    @SerialName("tick_time") val tickTime: String? = null,
    @SerialName("seq") val seq: String? = null,
    @SerialName("volume") val volume: String? = null,
    @SerialName("turnover") val turnover: String? = null,
    @SerialName("trade_direction") val tradeDirection: Int? = null,
)
