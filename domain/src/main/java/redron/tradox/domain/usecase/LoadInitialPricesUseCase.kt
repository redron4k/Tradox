package redron.tradox.domain.usecase

import redron.tradox.domain.repository.IPriceRepository

class LoadInitialPricesUseCase(
    private val repository: IPriceRepository
) {
    suspend operator fun invoke(
        symbols: List<String>
    ) = repository.loadInitialPrices(symbols)
}
