package redron.tradox.feature.prices.mvi

import redron.tradox.feature.prices.model.SortType

sealed class PriceIntent {
    data class Load(val symbols: List<String>) : PriceIntent()
    data object Retry : PriceIntent()

    data class InitPrices(val symbols: List<String>) : PriceIntent()

    data object StopObserving : PriceIntent()

    data class ChangeSortType(val sortType: SortType) : PriceIntent()

    data class Pin(val symbol: String) : PriceIntent()
}
