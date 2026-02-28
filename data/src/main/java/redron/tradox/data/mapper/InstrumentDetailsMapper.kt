package redron.tradox.data.mapper

import redron.tradox.core.network.common.model.InstrumentDetailsDto
import redron.tradox.domain.model.instrument.Dividend
import redron.tradox.domain.model.instrument.InstrumentDetails

fun InstrumentDetailsDto.toDomain(): InstrumentDetails {

    return InstrumentDetails(
        code = code,
        name = name.orEmpty(),
        description = description,
        currency = currency,
        dividends = dividends.map {
            Dividend(
                date = it.date,
                amount = it.amount,
            )
        },
    )
}
