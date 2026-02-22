package redron.tradox.core.network.websocket.datasource

import redron.tradox.core.network.websocket.alltick.AllTickWebSocketClient
import redron.tradox.core.network.websocket.alltick.toPriceDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import redron.tradox.core.network.common.model.PriceDto

class AllTickDataSource(
    private val client: AllTickWebSocketClient
) {

    fun observePrices(symbols: List<String>): Flow<PriceDto> {
        return client.observePrices(symbols)
            .mapNotNull { it.toPriceDto() }
    }
}
