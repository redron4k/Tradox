package redron.tradox.domain.usecase

import redron.tradox.domain.repository.IPriceRepository

class ObservePricesUseCase(
    private val repository: IPriceRepository
) {
    operator fun invoke(symbols: List<String>) =
        repository.observePrices(symbols)
}
