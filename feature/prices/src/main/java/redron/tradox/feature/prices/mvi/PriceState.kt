package redron.tradox.feature.prices.mvi

import redron.tradox.domain.model.Price

data class PriceState(
    val isLoading: Boolean = false,
    val prices: List<Price> = emptyList(),
    val error: String? = null
)
