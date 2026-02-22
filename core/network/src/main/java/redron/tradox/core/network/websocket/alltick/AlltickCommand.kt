package redron.tradox.core.network.websocket.alltick

import kotlinx.serialization.Serializable

@Serializable
data class AuthCommand(
    val type: String = "auth",
    val token: String
)

@Serializable
data class SubscribeCommand(
    val type: String = "subscribe",
    val symbols: List<String>
)

@Serializable
data class PingCommand(
    val type: String = "ping"
)
