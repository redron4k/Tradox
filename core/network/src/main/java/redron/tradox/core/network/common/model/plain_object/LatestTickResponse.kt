package redron.tradox.core.network.common.model.plain_object

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LatestTickResponse(
    @SerialName("ret") val ret: Int,
    @SerialName("msg") val msg: String,
    @SerialName("trace") val trace: String? = null,
    @SerialName("data") val data: LatestTickData,
)

@Serializable
data class LatestTickData(
    @SerialName("tick_list") val tickList: List<LatestTick>
)

@Serializable
data class LatestTick(
    @SerialName("code") val code: String,
    @SerialName("seq") val seq: String,
    @SerialName("tick_time") val tickTime: String,
    @SerialName("price") val price: String,
    @SerialName("volume") val volume: String,
    @SerialName("turnover") val turnover: String,
    @SerialName("trade_direction") val tradeDirection: Int
)