package redron.tradox.core.network.websocket.alltick

import kotlinx.datetime.Instant
import redron.tradox.core.network.common.model.PriceDto

fun AllTickQuoteMessage.toPriceDto(): PriceDto? {
    val symbol = symbol ?: return null
    val price = price ?: return null

    return PriceDto(
        symbol = symbol,
        price = price,
        timestamp = timestamp?.let { Instant.fromEpochMilliseconds(it) }
            ?: Instant.fromEpochMilliseconds(System.currentTimeMillis()),
        source = AllTickConfig.SOURCE_NAME,
        isDelayed = false,
    )
}
