package redron.tradox.core.network.common.client

import io.ktor.client.HttpClient

object KtorClientProvider {

    val client: HttpClient by lazy {
        HttpClientFactory.create()
    }
}
