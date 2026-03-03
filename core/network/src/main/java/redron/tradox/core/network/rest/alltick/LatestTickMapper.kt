package redron.tradox.core.network.rest.alltick

import kotlinx.datetime.Instant
import redron.tradox.core.network.common.config.AllTickConfig
import redron.tradox.core.network.common.model.plain_object.LatestTick
import redron.tradox.core.network.common.model.dto.PriceDto

fun LatestTick.toPriceDto() =  PriceDto (
    symbol = code,
    price = price.toDouble(),
    timestamp = Instant.fromEpochMilliseconds(tickTime.toLong()),
    source = AllTickConfig.SOURCE_NAME,
    isDelayed = false
)
