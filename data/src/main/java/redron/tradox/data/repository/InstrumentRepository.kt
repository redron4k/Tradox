package redron.tradox.data.repository

import redron.tradox.core.network.rest.alltick.AllTickRestApi
import redron.tradox.data.mapper.toDomain
import redron.tradox.domain.model.instrument.InstrumentDetails
import redron.tradox.domain.repository.IInstrumentRepository

class InstrumentRepository(
    private val api: AllTickRestApi,
) : IInstrumentRepository {
    override suspend fun getDetails(
        code: String
    ): InstrumentDetails {
        val details = api.getInstrumentDetails(code)
        return details.toDomain()
    }
}
