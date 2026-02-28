package redron.tradox.core.network.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InstrumentDetailsDto(
    @SerialName("code") val code: String,
    @SerialName("name") val name: String? = null,
    @SerialName("exchange") val exchange: String? = null,
    @SerialName("currency") val currency: String? = null,
    @SerialName("type") val type: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("sector") val sector: String? = null,
    @SerialName("industry") val industry: String? = null,
    @SerialName("lot_size") val lotSize: Int? = null,
    @SerialName("tick_size") val tickSize: Double? = null,
    @SerialName("dividends") val dividends: List<DividendDto> = emptyList(),
)