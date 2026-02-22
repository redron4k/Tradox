package redron.tradox.data.mapper

import redron.tradox.core.network.common.model.PriceDto
import redron.tradox.domain.model.Price


fun PriceDto.toDomain(): Price {
    return Price(
        symbol = symbol,
        value = price,
        timestamp = timestamp,
        source = source
    )
}
