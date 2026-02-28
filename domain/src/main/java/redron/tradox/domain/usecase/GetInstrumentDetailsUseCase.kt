package redron.tradox.domain.usecase

import redron.tradox.domain.model.instrument.InstrumentDetails
import redron.tradox.domain.repository.IInstrumentRepository

class GetInstrumentDetailsUseCase(
    private val repository: IInstrumentRepository,
) {

    suspend operator fun invoke(
        code: String,
    ): InstrumentDetails =
        repository.getDetails(code)
}
