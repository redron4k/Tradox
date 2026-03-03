package redron.tradox.data.mapper

import redron.tradox.core.network.common.model.dto.InstrumentDto
import redron.tradox.core.network.common.model.dto.InstrumentPointDto
import redron.tradox.data.utils.toStringDate
import redron.tradox.domain.model.instrument.InstrumentDetails
import redron.tradox.domain.model.instrument.InstrumentPoint

fun InstrumentDto.toDomain() = InstrumentDetails(
    code = code,
    points = points.map { it.toDomain() }
)


private fun InstrumentPointDto.toDomain() = InstrumentPoint(
    timestamp = timestamp,
    date = this.timestamp.toStringDate(),
    openPrice = openPrice,
    closePrice = closePrice,
    highPrice = highPrice,
    lowPrice = lowPrice,
    volume = volume,
    turnover = turnover,
)
