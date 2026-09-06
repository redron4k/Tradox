package redron.tradox.core.network.websocket.alltick

import kotlinx.datetime.Instant
import redron.tradox.core.network.common.config.AllTickConfig
import redron.tradox.core.network.common.model.dto.PriceDto

fun AllTickQuoteMessage.toPriceDto(): PriceDto {
    val d = data

    return PriceDto(
        symbol = d.code,
        price = d.price?.toDoubleOrNull() ?: 0.0,
        timestamp = Instant.fromEpochMilliseconds(d.tickTime?.toLong() ?: 0L),
        source = AllTickConfig.SOURCE_NAME,
        isDelayed = false
    )
}
