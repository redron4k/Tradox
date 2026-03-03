package redron.tradox.core.network.common.model.dto

import kotlinx.datetime.Instant

data class InstrumentDto(
    val code: String,
    val points: List<InstrumentPointDto>,
)

data class InstrumentPointDto(
    val timestamp: Instant,
    val openPrice: Double,
    val closePrice: Double,
    val highPrice: Double,
    val lowPrice: Double,
    val volume: Int,
    val turnover: Double,
)
