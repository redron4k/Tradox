package redron.tradox.data.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import redron.tradox.core.network.datasource.AllTickDataSource
import redron.tradox.data.mapper.appendStaticDetails
import redron.tradox.data.mapper.toDomain
import redron.tradox.domain.model.Price
import redron.tradox.domain.model.instrument.InstrumentDetails
import redron.tradox.domain.repository.IPriceRepository
import kotlin.map

class PriceRepository(
    private val datasource: AllTickDataSource,
) : IPriceRepository {

    override suspend fun loadInitialPrices(
        symbols: List<String>,
    ): Result<List<Price>> {
        return datasource.getInitialPrices(symbols).map { result ->
            result.map { it.toDomain() }
        }

    }

    override suspend fun getInstrumentDetails(
        code: String,
    ): Result<InstrumentDetails> {
        val staticDetails = datasource.getInstrumentStaticDetails(code).map { result ->
            result.toDomain()
        }

        delay(1200L)

        return datasource.getInstrumentDetails(code).map { result ->
            if (staticDetails.isSuccess) {
                result.toDomain()
                    .appendStaticDetails(staticDetails.getOrNull())
            } else {
                result.toDomain()
            }
        }
    }


    override fun observePrices(
        symbols: List<String>,
    ): Flow<Price> {
        return datasource.observePrices(symbols)
            .map { it.toDomain() }
    }

    override fun observePrice(
        symbol: String,
    ): Flow<Price> {
        return datasource.observePrices(listOf(symbol))
            .filter { it.symbol == symbol }
            .map { it.toDomain() }
    }
}
