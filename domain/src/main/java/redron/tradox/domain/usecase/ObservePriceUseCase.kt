package redron.tradox.domain.usecase

import redron.tradox.domain.repository.IPriceRepository

class ObservePriceUseCase(
    private val repository: IPriceRepository
) {
    operator fun invoke(symbol: String) =
        repository.observePrice(symbol)
}
