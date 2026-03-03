package redron.tradox.domain.repository


import kotlinx.coroutines.flow.Flow
import redron.tradox.domain.model.Price
import redron.tradox.domain.model.instrument.InstrumentDetails

interface IPriceRepository {

    suspend fun loadInitialPrices(symbols: List<String>): Result<List<Price>>

    suspend fun getInstrumentDetails(code: String): Result<InstrumentDetails>

    fun observePrices(symbols: List<String>): Flow<Price>

    fun observePrice(symbol: String): Flow<Price>
}
