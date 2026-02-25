package redron.tradox.core.network.websocket.alltick


data object AllTickConfig {

    const val HOST = "quote.alltick.co"
    const val PATH = "/quote-stock-b-ws-api"

    const val SOURCE_NAME = "AllTick"

    const val HEARTBEAT_INTERVAL_MS = 10_000L

    const val queriesTokenParamName = "token"
}
