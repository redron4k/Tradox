package redron.tradox.feature.prices.mvi

import kotlin.collections.filterNot
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import redron.tradox.domain.usecase.LoadInitialPricesUseCase
import redron.tradox.domain.usecase.ObservePricesUseCase
import javax.inject.Inject

class PriceViewModel @Inject constructor(
    private val observePricesUseCase: ObservePricesUseCase,
    private val loadInitialPricesUseCase: LoadInitialPricesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(PriceState())
    val state: StateFlow<PriceState> = _state.asStateFlow()

    private var observeJob: Job? = null

    fun process(action: PriceIntent) {
        when (action) {
            is PriceIntent.InitPrices -> loadInitialPrices(action.symbols)
            is PriceIntent.Load -> loadBySocket(action.symbols)
            is PriceIntent.StopObserving -> stopObserving()
            is PriceIntent.Retry -> {
                // TODO
            }
        }
    }

    private fun stopObserving() {
        observeJob?.cancel()
        observeJob = null
    }

    private fun loadInitialPrices(symbols: List<String>) {
        _state.update {
            it.copy(
                isLoading = true,
                error = null
            )
        }

        viewModelScope.launch {

            loadInitialPricesUseCase(symbols).fold(
                onSuccess = { initialPrices ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            prices = initialPrices,
                        )
                    }
                },

                onFailure = { throwable ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = throwable.message ?: "Unknown error",
                        )
                    }
                }
            )
        }
    }

    private fun loadBySocket(symbols: List<String>) {
        _state.update {
            it.copy(
                isLoading = false,
                error = null
            )
        }

        observeJob = viewModelScope.launch {
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
