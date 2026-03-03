package redron.tradox.core.network.rest.alltick

import kotlinx.datetime.Instant
import redron.tradox.core.network.common.model.dto.InstrumentDto
import redron.tradox.core.network.common.model.dto.InstrumentPointDto
import redron.tradox.core.network.common.model.plain_object.KLinePoint
import redron.tradox.core.network.common.model.plain_object.KLineResponseData

fun KLineResponseData.toInstrumentDto() = InstrumentDto(
    code = code,
    points = points.map { it.toPointDto() },
)

private fun KLinePoint.toPointDto() = InstrumentPointDto(
    timestamp = Instant.fromEpochMilliseconds(timestamp.toLong()),
    openPrice = openPrice.toDouble(),
    closePrice = closePrice.toDouble(),
    highPrice = highPrice.toDouble(),
    lowPrice = lowPrice.toDouble(),
    volume = volume.toInt(),
    turnover = turnover.toDouble(),
)
