package redron.tradox.domain.model

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime

data class Price(
    val symbol: String,
    val value: Double,
    val timestamp: Instant,
    val updateTime: LocalDateTime,
    val source: String,
    val isDelayed: Boolean,
)
