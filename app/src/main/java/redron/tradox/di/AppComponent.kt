package redron.tradox.di

import dagger.Component
import redron.tradox.data.di.DataModule
import redron.tradox.data.di.NetworkModule
import redron.tradox.domain.di.DomainModule
import redron.tradox.domain.usecase.ObservePriceUseCase
import redron.tradox.domain.usecase.ObservePricesUseCase
import redron.tradox.feature.prices.PricesComponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        DataModule::class,
        DomainModule::class,
    ]
)
interface AppComponent {

    fun observePriceUseCase(): ObservePriceUseCase

    fun observePricesUseCase(): ObservePricesUseCase

    fun pricesComponent(): PricesComponent.Factory
}
