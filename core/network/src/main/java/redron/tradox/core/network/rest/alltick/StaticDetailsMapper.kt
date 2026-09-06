package redron.tradox.core.network.rest.alltick

import redron.tradox.core.network.common.model.dto.InstrumentStaticDetailsDto
import redron.tradox.core.network.common.model.plain_object.StaticDetailsResponse

fun StaticDetailsResponse.toStaticDetailsDto() : InstrumentStaticDetailsDto {
    val details = this.data.staticInfoList.firstOrNull()
    return InstrumentStaticDetailsDto(
        sector = details?.sector,
        bps = details?.bps?.toDouble(),
        circulatingShares = details?.circulatingShares?.toLong(),
        currency = details?.currency,
        dividend = details?.dividend?.toDouble(),
        earningsPerShare = details?.earningsPerShare?.toDouble(),
        earningsPerShareTTM = details?.earningsPerShareTTM?.toDouble(),
        exchange = details?.exchange,
        lotSize = details?.lotSize?.toInt(),
        totalShares = details?.totalShares?.toLong(),
    )
}
