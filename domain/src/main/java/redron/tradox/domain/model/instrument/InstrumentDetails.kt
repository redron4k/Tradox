package redron.tradox.domain.model.instrument

import kotlinx.datetime.Instant

data class InstrumentDetails(
    val code: String,
    val points: List<InstrumentPoint>,
)

data class InstrumentPoint(
    val timestamp: Instant,
    val date: String,
    val openPrice: Double,
    val closePrice: Double,
    val highPrice: Double,
    val lowPrice: Double,
    val volume: Int,
    val turnover: Double,
)
