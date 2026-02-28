package redron.tradox.core.network.websocket.alltick

import kotlinx.datetime.Instant
import redron.tradox.core.network.common.model.PriceDto

fun AllTickQuoteMessage.toPriceDto(): PriceDto {

    val d = data

    return PriceDto(
        symbol = d.code,
        price = d.last_price?.toDoubleOrNull() ?: 0.0,
        timestamp = Instant.fromEpochMilliseconds(d.ts?.toLong() ?: 0L),
        source = AllTickConfig.SOURCE_NAME,
        isDelayed = false
    )
}
