package redron.tradox.core.network.common.model.plain_object

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StaticDetailsResponse(
    @SerialName("ret") val ret: Int,
    @SerialName("msg") val msg: String,
    @SerialName("trace") val trace: String? = null,
    @SerialName("data") val data: StaticDetailsData,
)

@Serializable
data class StaticDetailsData(
    @SerialName("static_info_list") val staticInfoList: List<StaticDetails>
)

@Serializable
data class StaticDetails(
    @SerialName("board") val sector: String?,
    @SerialName("bps") val bps: String?,
    @SerialName("circulating_shares") val circulatingShares: String?,
    @SerialName("currency") val currency: String?,
    @SerialName("dividend_yield") val dividend: String?,
    @SerialName("eps") val earningsPerShare: String?,
    @SerialName("eps_ttm") val earningsPerShareTTM: String?,
    @SerialName("exchange") val exchange: String?,
    @SerialName("lot_size") val lotSize: String?,
    @SerialName("total_shares") val totalShares: String?,
)