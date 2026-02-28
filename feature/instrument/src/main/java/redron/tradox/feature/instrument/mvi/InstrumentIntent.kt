package redron.tradox.feature.instrument.mvi

sealed interface InstrumentIntent {
    data class Load(
        val code: String,
    ) : InstrumentIntent
}