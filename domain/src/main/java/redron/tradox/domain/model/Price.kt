package redron.tradox.domain.model

import kotlinx.datetime.Instant

data class Price(
    val symbol: String,
    val value: Double,
    val timestamp: Instant,
    val source: String
)
