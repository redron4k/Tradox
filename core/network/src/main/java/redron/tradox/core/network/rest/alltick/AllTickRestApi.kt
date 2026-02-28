package redron.tradox.core.network.rest.alltick

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import redron.tradox.core.network.common.model.InstrumentDetailsDto

class AllTickRestApi(
    private val client: HttpClient,
) {
    suspend fun getInstrumentDetails(
        code: String
    ): InstrumentDetailsDto {
        return client.get(
            "https://api.alltick.co/instrument/$code"
        ).body()
    }
}
