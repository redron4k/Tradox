package redron.tradox.core.network.websocket.alltick

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import io.ktor.websocket.send
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import redron.tradox.core.network.websocket.reconnect.ReconnectPolicy

class AllTickWebSocketClient(
    private val client: HttpClient,
    private val apiKey: String,
    private val json: Json = Json { ignoreUnknownKeys = true }
) {

    private val reconnectPolicy = ReconnectPolicy()

    fun observePrices(symbols: List<String>): Flow<AllTickQuoteMessage> = callbackFlow {

        var attempt = 0

        while (true) {
            try {

                client.webSocket(
                    host = AllTickConfig.HOST,
                    path = AllTickConfig.PATH
                ) {

                    attempt = 0

                    send(json.encodeToString(AuthCommand(token = apiKey)))

                    send(json.encodeToString(SubscribeCommand(symbols = symbols)))

                    for (frame in incoming) {
                        if (frame is Frame.Text) {
                            val message = json.decodeFromString<AllTickQuoteMessage>(
                                frame.readText()
                            )
                            trySend(message)
                        }
                    }
                }

            } catch (_: Exception) {
                attempt++
                reconnectPolicy.delayBeforeRetry(attempt)
            }
        }

        awaitClose { }
    }
}
