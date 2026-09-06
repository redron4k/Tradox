package redron.tradox.core.network.rest.alltick

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import redron.tradox.core.network.common.config.AllTickConfig
import redron.tradox.core.network.common.model.plain_object.KLineQueryData
import redron.tradox.core.network.common.model.plain_object.KLineRequest
import redron.tradox.core.network.common.model.plain_object.KLineResponse
import redron.tradox.core.network.common.model.plain_object.LatestTickQueryData
import redron.tradox.core.network.common.model.plain_object.LatestTickRequest
import redron.tradox.core.network.common.model.plain_object.LatestTickResponse
import redron.tradox.core.network.common.model.plain_object.StaticDetailsResponse
import redron.tradox.core.network.common.model.plain_object.Symbol
import java.util.UUID

class AllTickRestApi(
    private val client: HttpClient,
    private val apiKey: String,
    private val json: Json,
) {
    suspend fun getInstrumentDetails(code: String): Result<KLineResponse> {
        val queryString = json.encodeToString(
            KLineRequest(
                trace = UUID.randomUUID().toString(),
                data = KLineQueryData(
                    code = code,
                    type = 8,
                    timestampEnd = 0,
                    lineNum = 100,
                    adjust = 0,
                )
            )
        )

        return try {
            val response = client.get(AllTickConfig.Rest.HISTORICAL_DATA_PATH) {
                parameter(AllTickConfig.queriesTokenParamName, apiKey)
                parameter(
                    AllTickConfig.queriesQueryParamName,
                    queryString,
                )
            }.body<KLineResponse>()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getLastPrices(symbols: List<String>): Result<LatestTickResponse> {

        val queryString = json.encodeToString(
            LatestTickRequest(
                trace = UUID.randomUUID().toString(),
                data = LatestTickQueryData(
                    symbolList = symbols.map {
                        Symbol(it)
                    }
                )
            )
        )

        return try {
            val response = client.get(AllTickConfig.Rest.PRICES_PATH) {
                parameter(AllTickConfig.queriesTokenParamName, apiKey)
                parameter(
                    AllTickConfig.queriesQueryParamName,
                    queryString,
                )
            }.body<LatestTickResponse>()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getInstrumentStaticDetails(code: String): Result<StaticDetailsResponse> {
        val queryString = json.encodeToString(
            LatestTickRequest(
                trace = UUID.randomUUID().toString(),
                data = LatestTickQueryData(
                    symbolList = listOf(
                        Symbol(code)
                    )
                )
            )
        )

        return try {
            val response = client.get(AllTickConfig.Rest.INSTRUMENT_PATH) {
                parameter(AllTickConfig.queriesTokenParamName, apiKey)
                parameter(
                    AllTickConfig.queriesQueryParamName,
                    queryString,
                )
            }.body<StaticDetailsResponse>()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
