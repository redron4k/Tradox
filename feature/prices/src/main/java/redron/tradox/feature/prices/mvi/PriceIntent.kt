package redron.tradox.feature.prices.mvi

sealed class PriceIntent {
    data class Load(val symbols: List<String>) : PriceIntent()
    data object Retry : PriceIntent()

    data class InitPrices(val symbols: List<String>) : PriceIntent()

    data object StopObserving : PriceIntent()
}
