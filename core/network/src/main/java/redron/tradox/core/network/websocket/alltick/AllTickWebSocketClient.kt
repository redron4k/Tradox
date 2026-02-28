package redron.tradox.core.network.websocket.alltick

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.URLProtocol
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import io.ktor.websocket.send
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import redron.tradox.core.network.websocket.reconnect.ReconnectPolicy
import java.util.UUID

class AllTickWebSocketClient(
    private val client: HttpClient,
    private val apiKey: String,
    private val json: Json,
) {

    private val reconnectPolicy = ReconnectPolicy()

    fun observePrices(
        symbols: List<String>
    ): Flow<AllTickQuoteMessage> = callbackFlow {

        val wsJob = launch {

            var attempt = 0

            while (isActive) {

                try {

                    println("Starting WS connection")

                    client.webSocket(
                        host = AllTickConfig.HOST,
                        path = AllTickConfig.PATH,
                        request = {
                            url.protocol = URLProtocol.WSS
                            url.parameters.append(
                                AllTickConfig.queriesTokenParamName,
                                apiKey
                            )
                        }
                    ) {

                        println("WS CONNECTED")
                        attempt = 0

                        sendSubscribe(symbols)

                        val heartbeatJob = startHeartbeat()

                        try {
                            for (frame in incoming) {
                                if (frame is Frame.Text) {
                                    val text = frame.readText()
                                    println("WS RAW:")
                                    println(text)

                                    // subscribe response
                                    if (text.contains("\"cmd_id\":22005"))
                                        continue

                                    // heartbeat response
                                    if (text.contains("\"cmd_id\":22001"))
                                        continue

                                    val message =
                                        json.decodeFromString<AllTickQuoteMessage>(
                                            text
                                        )

                                    trySend(message)
                                }
                            }

                        } finally {
                            heartbeatJob.cancel()
                        }
                    }

                } catch (e: Exception) {
                    println("WS ERROR:")
                    e.printStackTrace()

                    attempt++
                    reconnectPolicy.delayBeforeRetry(attempt)
                }
            }
        }

        awaitClose {
            println("WS FLOW CLOSED")
            wsJob.cancel()
        }
    }


    private suspend fun DefaultClientWebSocketSession.sendSubscribe(
        symbols: List<String>
    ) {

        val subscribeRequest =
            SubscribeCommand(
                seqId = 1,
                trace = UUID.randomUUID().toString(),
                data = SubscribeCommand.Data(
                    symbolList = symbols.map {
                        SubscribeCommand.Symbol(it)
                    }
                )
            )

        val subscribeJson = json.encodeToString(subscribeRequest)

        println("SUBSCRIBE JSON:")
        println(subscribeJson)

        send(subscribeJson)
    }


    private fun DefaultClientWebSocketSession.startHeartbeat(): Job =
        launch {
            var seq = 100

            while (isActive) {
                delay(AllTickConfig.HEARTBEAT_INTERVAL_MS)

                val heartbeat = HeartbeatCommand(
                    seqId = seq++,
                    trace = UUID.randomUUID().toString()
                )

                val heartbeatJson = json.encodeToString(heartbeat)

                println("HEARTBEAT:")
                println(heartbeatJson)

                send(heartbeatJson)
            }
        }
}