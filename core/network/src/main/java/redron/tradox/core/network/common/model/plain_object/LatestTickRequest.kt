package redron.tradox.core.network.common.model.plain_object

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LatestTickRequest(
    @SerialName("trace") val trace: String,
    @SerialName("data") val data: LatestTickQueryData
)

@Serializable
data class LatestTickQueryData(
    @SerialName("symbol_list") val symbolList: List<Symbol>
)

@Serializable
data class Symbol(
    @SerialName("code") val code: String
)
