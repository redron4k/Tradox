package redron.tradox.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import redron.tradox.data.stream.PriceStreamManager
import redron.tradox.domain.model.Price
import redron.tradox.domain.repository.IPriceRepository

class PriceRepository(
    private val streamManager: PriceStreamManager
) : IPriceRepository {

    override fun observePrices(symbols: List<String>): Flow<Price> {
        return streamManager.observe(symbols)
    }

    override fun observePrice(symbol: String): Flow<Price> {
        return streamManager
            .observe(listOf(symbol))
            .filter { it.symbol == symbol }
    }
}
