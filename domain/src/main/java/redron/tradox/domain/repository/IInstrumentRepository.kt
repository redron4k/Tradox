package redron.tradox.domain.repository

import redron.tradox.domain.model.instrument.InstrumentDetails

interface IInstrumentRepository {
    suspend fun getDetails(
        code: String,
    ): InstrumentDetails
}
