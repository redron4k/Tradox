package redron.tradox.feature.prices.di

import dagger.Module
import dagger.Provides
import redron.tradox.domain.usecase.LoadInitialPricesUseCase
import redron.tradox.domain.usecase.ObservePricesUseCase
import redron.tradox.feature.prices.mvi.PriceViewModel

@Module
class PricesModule {
    @Provides
    fun providePricesViewModel(
        observePrices: ObservePricesUseCase,
        loadInitialPrices: LoadInitialPricesUseCase
    ): PriceViewModel = PriceViewModel(
        observePricesUseCase = observePrices,
        loadInitialPricesUseCase = loadInitialPrices,
    )

    @Provides
    fun providePriceViewModelFactory(
        viewModel: PriceViewModel
    ): PriceViewModelFactory = PriceViewModelFactory { viewModel }
}