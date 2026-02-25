package redron.tradox.core.network.common.config

object NetworkConfig {
    const val REQUEST_TIMEOUT = 15_000L
    const val CONNECT_TIMEOUT = 10_000L
    const val SOCKET_TIMEOUT = 15_000L

    const val PING_INTERVAL = 15_000L

    const val ENABLE_LOGGING = true

    const val YAHOO1_BASE_URL = "https://query1.finance.yahoo.com"
    const val YAHOO2_BASE_URL = "https://query2.finance.yahoo.com"
    const val MOEX_BASE_URL = "https://iss.moex.com"
}
