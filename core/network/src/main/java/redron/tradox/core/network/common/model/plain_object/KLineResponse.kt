package redron.tradox.core.network.common.model.plain_object

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KLineResponse (
    @SerialName("trace") val trace: String,
    @SerialName("data") val data: KLineResponseData
)

@Serializable
data class KLineResponseData (
    @SerialName("code") val code: String,
    @SerialName("kline_type") val type: Int,
    @SerialName("kline_list") val points: List<KLinePoint>,
)

@Serializable
data class KLinePoint (
    @SerialName("timestamp") val timestamp: String,
    @SerialName("open_price") val openPrice: String,
    @SerialName("close_price") val closePrice: String,
    @SerialName("high_price") val highPrice: String,
    @SerialName("low_price") val lowPrice: String,
    @SerialName("volume") val volume: String,
    @SerialName("turnover") val turnover: String,
)
