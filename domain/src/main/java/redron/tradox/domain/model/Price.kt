package redron.tradox.domain.model

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime

data class Price(
    val symbol: String,
    val value: Double,
    val country: String,
    val dayOpenValue: Double = 250.3,
    val timestamp: Instant,
    val updateTime: LocalDateTime,
    val source: String,
    val isDelayed: Boolean,
    val isPinned: Boolean = false,
)
