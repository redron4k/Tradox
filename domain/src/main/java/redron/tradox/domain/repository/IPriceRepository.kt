package redron.tradox.domain.repository


import kotlinx.coroutines.flow.Flow
import redron.tradox.domain.model.Price

interface IPriceRepository {

    fun observePrices(symbols: List<String>): Flow<Price>

    fun observePrice(symbol: String): Flow<Price>
}
