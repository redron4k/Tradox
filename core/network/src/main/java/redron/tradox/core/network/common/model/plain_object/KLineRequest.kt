package redron.tradox.core.network.common.model.plain_object

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KLineRequest(
    @SerialName("trace") val trace: String,
    @SerialName("data") val data: KLineQueryData
)

@Serializable
data class KLineQueryData(
    @SerialName("code") val code: String,
    @SerialName("kline_type") val type: Int,
    @SerialName("kline_timestamp_end") val timestampEnd: Long,
    @SerialName("query_kline_num") val lineNum: Int,
    @SerialName("adjust_type") val adjust: Int,
)
