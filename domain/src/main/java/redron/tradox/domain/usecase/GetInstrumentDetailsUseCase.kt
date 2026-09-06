package redron.tradox.domain.usecase

import redron.tradox.domain.repository.IPriceRepository

class GetInstrumentDetailsUseCase(
    private val repository: IPriceRepository,
) {
    suspend operator fun invoke(
        code: String,
    ) = repository.getInstrumentDetails(code)
}
