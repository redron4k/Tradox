package redron.tradox.feature.prices.mvi

import redron.tradox.domain.model.Price
import redron.tradox.feature.prices.model.SortType

data class PriceState(
    val isLoading: Boolean = false,
    val prices: List<Price> = emptyList(),
    val error: String? = null,
    val sortType: SortType = SortType.PriceUp,
)
