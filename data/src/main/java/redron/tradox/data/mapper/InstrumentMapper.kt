package redron.tradox.data.mapper

import redron.tradox.core.network.common.model.dto.InstrumentDto
import redron.tradox.core.network.common.model.dto.InstrumentPointDto
import redron.tradox.core.network.common.model.dto.InstrumentStaticDetailsDto
import redron.tradox.data.utils.toStringDate
import redron.tradox.domain.model.instrument.InstrumentDetails
import redron.tradox.domain.model.instrument.InstrumentPoint
import redron.tradox.domain.model.instrument.InstrumentStaticDetails

fun InstrumentDto.toDomain() = InstrumentDetails(
    code = code,
    points = points.map { it.toDomain() }
)

fun InstrumentStaticDetailsDto.toDomain() = InstrumentStaticDetails(
    sector = sector,
    bps = bps,
    circulatingShares = circulatingShares,
    currency = currency,
    dividend = dividend,
    earningsPerShare = earningsPerShare,
    earningsPerShareTTM = earningsPerShareTTM,
    exchange = exchange,
    lotSize = lotSize,
    totalShares = totalShares,
)

fun InstrumentDetails.appendStaticDetails(
    staticDetails: InstrumentStaticDetails?,
) = InstrumentDetails(
    code = code,
    staticDetails = staticDetails,
    points = points,
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
