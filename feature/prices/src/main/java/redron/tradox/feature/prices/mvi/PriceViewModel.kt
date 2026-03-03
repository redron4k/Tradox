package redron.tradox.feature.prices.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import redron.tradox.domain.usecase.GetPinnedUseCase
import redron.tradox.domain.usecase.LoadInitialPricesUseCase
import redron.tradox.domain.usecase.ObservePricesUseCase
import redron.tradox.feature.prices.model.SortType
import javax.inject.Inject

class PriceViewModel @Inject constructor(
    private val observePricesUseCase: ObservePricesUseCase,
    private val loadInitialPricesUseCase: LoadInitialPricesUseCase,
    private val getPinnedUseCase: GetPinnedUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(PriceState())
    val state: StateFlow<PriceState> = _state.asStateFlow()

    val symbols = listOf("AAPL.US", "MSFT.US", "TSLA.US", "AMZN.US", "NKE.US",
        "ORCL.US", "9988.HK", "867.HK", "1907.HK", "NVDA.US")

    private var observeJob: Job? = null

    fun process(action: PriceIntent) {
        when (action) {
            is PriceIntent.InitPrices -> loadInitialPrices(action.symbols)
            is PriceIntent.Load -> loadBySocket(action.symbols)
            is PriceIntent.StopObserving -> stopObserving()
            is PriceIntent.ChangeSortType -> changeSortType(action.sortType)
            is PriceIntent.Pin -> pinPrice(action.symbol)
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
                    _state.update { state ->
                        state.copy(
                            isLoading = false,
                            prices = initialPrices.map {
                                it.copy(isPinned = it.symbol in getPinnedUseCase().value)
                            },
                        )
                    }
                    applySort()
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
                                prices = current.prices.map { item ->
                                    if (item.symbol == quote.symbol) {
                                        item.copy(value = quote.value)
                                    } else {
                                        item
                                    }
                                }
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

    private fun changeSortType(sortType: SortType) {
        _state.update {
            it.copy(
                sortType = sortType
            )
        }
        applySort()
    }

    private fun applySort() {
        _state.update { state ->
            state.copy(
                prices = when (state.sortType) {
                    SortType.PriceUp -> state.prices.sortedBy { it.value }
                    SortType.PriceDown -> state.prices.sortedByDescending { it.value }
                    SortType.Name -> state.prices.sortedBy { it.symbol }
                    SortType.Country -> state.prices.sortedByDescending { it.country }
                }.sortedByDescending { it.isPinned }
            )
        }
    }

    private fun pinPrice(symbol: String) {
        _state.update {
            it.copy(
                prices = it.prices.map { quote ->
                    if (quote.symbol == symbol) {
                        quote.copy(isPinned = !quote.isPinned)
                    } else {
                        quote
                    }
                }
            )
        }
        applySort()
    }
}
