package redron.tradox.data.stream

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import redron.tradox.core.network.websocket.datasource.AllTickDataSource
import redron.tradox.data.mapper.toDomain
import redron.tradox.domain.model.Price

class PriceStreamManager(
    private val allTickSource: AllTickDataSource
) {

    fun observe(symbols: List<String>): Flow<Price> {
        return allTickSource
            .observePrices(symbols)
            .map { it.toDomain() }
    }
}
