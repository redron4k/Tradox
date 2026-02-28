package redron.tradox.feature.instrument.mvi

import redron.tradox.domain.model.instrument.InstrumentDetails

data class InstrumentState(
    val isLoading: Boolean = true,
    val details: InstrumentDetails? = null,
    val error: String? = null,
)