package redron.tradox.di

import dagger.Component
import redron.tradox.data.di.DataModule
import redron.tradox.data.di.NetworkModule
import redron.tradox.domain.di.DomainModule
import redron.tradox.domain.usecase.ObservePriceUseCase
import redron.tradox.domain.usecase.ObservePricesUseCase
import redron.tradox.feature.instrument.di.InstrumentComponent
import redron.tradox.feature.instrument.di.InstrumentModule
import redron.tradox.feature.prices.di.PricesComponent
import redron.tradox.feature.prices.di.PricesModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        DataModule::class,
        DomainModule::class,
        PricesModule::class,
        InstrumentModule::class,
    ]
)
interface AppComponent {

    fun observePriceUseCase(): ObservePriceUseCase

    fun observePricesUseCase(): ObservePricesUseCase

    fun instrumentComponent(): InstrumentComponent.Factory

    fun pricesComponent(): PricesComponent.Factory
}
