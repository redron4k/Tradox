package redron.tradox.core.network.websocket.alltick

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SubscribeCommand(

    @SerialName("cmd_id")
    val cmdId: Int = 22004,

    @SerialName("seq_id")
    val seqId: Int,

    val trace: String,

    val data: Data,
) {

    @Serializable
    data class Data(

        @SerialName("symbol_list")
        val symbolList: List<Symbol>
    )

    @Serializable
    data class Symbol(
        val code: String
    )
}

@Serializable
data class HeartbeatCommand(
    @SerialName("cmd_id")
    val cmdId: Int = 22000,

    @SerialName("seq_id")
    val seqId: Int,

    val trace: String,

    val data: Map<String, String> = emptyMap(),
)
