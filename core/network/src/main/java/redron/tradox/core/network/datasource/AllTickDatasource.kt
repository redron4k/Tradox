package redron.tradox.core.network.datasource

import redron.tradox.core.network.websocket.alltick.AllTickWebSocketClient
import redron.tradox.core.network.websocket.alltick.toPriceDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import redron.tradox.core.network.common.model.dto.InstrumentDto
import redron.tradox.core.network.common.model.dto.InstrumentStaticDetailsDto
import redron.tradox.core.network.common.model.dto.PriceDto
import redron.tradox.core.network.rest.alltick.AllTickRestApi
import redron.tradox.core.network.rest.alltick.toInstrumentDto
import redron.tradox.core.network.rest.alltick.toPriceDto
import redron.tradox.core.network.rest.alltick.toStaticDetailsDto

class AllTickDataSource(
    private val client: AllTickWebSocketClient,
    private val restApi: AllTickRestApi,
) {

    fun observePrices(symbols: List<String>): Flow<PriceDto> {
        return client.observePrices(symbols)
            .mapNotNull { it.toPriceDto() }
    }

    suspend fun getInitialPrices(symbols: List<String>): Result<List<PriceDto>> {
        return restApi.getLastPrices(symbols).map { result ->
            result.data
                .tickList
                .map { it.toPriceDto() }
        }

    }

    suspend fun getInstrumentDetails(code: String): Result<InstrumentDto> {
        return restApi.getInstrumentDetails(code).map { result ->
            result.data
            .toInstrumentDto()
        }
    }

    suspend fun getInstrumentStaticDetails(code: String): Result<InstrumentStaticDetailsDto> {
        return restApi.getInstrumentStaticDetails(code).map { result ->
            result.toStaticDetailsDto()
        }
    }
}
