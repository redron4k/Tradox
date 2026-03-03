package redron.tradox.core.network.common.config

data object AllTickConfig {

    const val BASE_URL = "quote.alltick.co"

    const val SOURCE_NAME = "AllTick"

    const val queriesTokenParamName = "token"
    const val queriesQueryParamName = "query"

    data object WebSocket {

        const val PATH = "/quote-stock-b-ws-api"

        const val HEARTBEAT_INTERVAL_MS = 10_000L
    }

    data object Rest {

        const val PRICES_PATH = "/quote-stock-b-api/trade-tick"
        const val INSTRUMENT_PATH = "/quote-stock-b-api/static_info"
        const val HISTORICAL_DATA_PATH = "/quote-stock-b-api/kline"

    }
}