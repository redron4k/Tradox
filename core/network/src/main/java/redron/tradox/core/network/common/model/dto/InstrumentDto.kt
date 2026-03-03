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

data class InstrumentStaticDetailsDto(
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
