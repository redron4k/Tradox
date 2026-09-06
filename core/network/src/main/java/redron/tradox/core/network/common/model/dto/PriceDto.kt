package redron.tradox.core.network.common.model.dto

import kotlinx.datetime.Instant

data class PriceDto(
    val symbol: String,
    val price: Double,
    val timestamp: Instant,
    val source: String,
    val isDelayed: Boolean,
)