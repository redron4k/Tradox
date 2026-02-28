package redron.tradox.feature.instrument.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import redron.tradox.domain.usecase.GetInstrumentDetailsUseCase
import javax.inject.Inject

class InstrumentViewModel @Inject constructor(
    private val getDetails: GetInstrumentDetailsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(InstrumentState())

    val state =  _state.asStateFlow()

    fun process(action: InstrumentIntent) {
        when (action) {
            is InstrumentIntent.Load -> load(action.code)
        }
    }

    private fun load(code: String) {
        viewModelScope.launch {
            val details = getDetails(code)

            _state.update {
                it.copy(
                    isLoading = false,
                    details = details,
                )
            }
        }
    }
}
