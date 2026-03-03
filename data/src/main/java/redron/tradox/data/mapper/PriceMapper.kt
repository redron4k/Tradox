package redron.tradox.data.mapper

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import redron.tradox.core.network.common.model.dto.PriceDto
import redron.tradox.domain.model.Price


fun PriceDto.toDomain() = Price(
    symbol = symbol,
    value = price,
    timestamp = timestamp,
    source = source,
    isDelayed = isDelayed,
    updateTime = timestamp.toLocalDateTime(
        TimeZone.currentSystemDefault()
    )
)
