package redron.tradox.feature.prices.di

import dagger.Subcomponent

@Subcomponent(modules = [PricesModule::class])
interface PricesComponent {

    val viewModelFactory: PriceViewModelFactory

    @Subcomponent.Factory
    interface Factory {
        fun create(): PricesComponent
    }
}