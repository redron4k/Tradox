package redron.tradox.feature.prices.mvi

import kotlin.collections.filterNot
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import redron.tradox.domain.usecase.ObservePricesUseCase
import javax.inject.Inject

class PriceViewModel @Inject constructor(
    private val observePricesUseCase: ObservePricesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(PriceState())
    val state: StateFlow<PriceState> = _state.asStateFlow()

    fun process(action: PriceAction) {
        when (action) {
            is PriceAction.Load -> load(action.symbols)
            is PriceAction.Retry -> {
                // TODO
            }
        }
    }

    private fun load(symbols: List<String>) {
        _state.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            try {
                observePricesUseCase(symbols)
                    .collect { quote ->
                        _state.update { current ->
                            current.copy(
                                isLoading = false,
                                prices = current.prices
                                    .filterNot { it.symbol == quote.symbol } + quote
                            )
                        }
                    }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Unknown error"
                    )
                }
            }
        }
    }
}
