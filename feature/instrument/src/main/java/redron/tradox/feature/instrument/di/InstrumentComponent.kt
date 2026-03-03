package redron.tradox.feature.instrument.di

import dagger.Subcomponent

@Subcomponent(modules = [InstrumentModule::class])
interface InstrumentComponent {

    val viewModelFactory: InstrumentViewModelFactory

    @Subcomponent.Factory
    interface Factory {
        fun create(): InstrumentComponent
    }
}
