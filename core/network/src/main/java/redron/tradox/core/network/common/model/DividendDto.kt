package redron.tradox.core.network.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DividendDto(
    @SerialName("date") val date: String,
    @SerialName("amount") val amount: Double,
)