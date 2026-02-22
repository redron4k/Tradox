package redron.tradox.core.network.common.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import redron.tradox.core.network.common.config.NetworkConfig
import redron.tradox.core.network.common.retry.configureDefaultRetry

object HttpClientFactory {

    fun create(): HttpClient {
        return HttpClient(OkHttp) {

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        explicitNulls = false
                        coerceInputValues = true
                    }
                )
            }

            install(HttpTimeout) {
                requestTimeoutMillis = NetworkConfig.REQUEST_TIMEOUT
                connectTimeoutMillis = NetworkConfig.CONNECT_TIMEOUT
                socketTimeoutMillis = NetworkConfig.SOCKET_TIMEOUT
            }

            install(WebSockets)

            install(HttpRequestRetry) {
                configureDefaultRetry()
            }

            if (NetworkConfig.ENABLE_LOGGING) {
                install(Logging) {
                    level = LogLevel.BODY
                }
            }
        }
    }
}
