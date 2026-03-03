package redron.tradox.domain.model.instrument

import kotlinx.datetime.Instant

data class InstrumentDetails(
    val code: String,
    val staticDetails: InstrumentStaticDetails? = null,
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

data class InstrumentStaticDetails(
    val sector: String?,
    val bps: Double?,
    val circulatingShares: Long?,
    val currency: String?,
    val dividend: Double?,
    val earningsPerShare: Double?,
    val earningsPerShareTTM: Double?,
    val exchange: String?,
    val lotSize: Int?,
    val totalShares: Long?,
)
