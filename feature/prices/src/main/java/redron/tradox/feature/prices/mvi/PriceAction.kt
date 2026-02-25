package redron.tradox.feature.prices.mvi

sealed class PriceAction {
    data class Load(val symbols: List<String>) : PriceAction()
    data object Retry : PriceAction()
}
