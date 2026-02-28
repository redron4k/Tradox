package redron.tradox.domain.model.instrument

data class InstrumentDetails(
    val code: String,
    val name: String,
    val description: String?,
    val currency: String?,
    val dividends: List<Dividend> = emptyList(),
)
