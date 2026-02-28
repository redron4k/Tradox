package redron.tradox.feature.instrument.di

import dagger.Subcomponent
import redron.tradox.feature.instrument.mvi.InstrumentViewModel

@Subcomponent
interface InstrumentComponent {

    val viewModel: InstrumentViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(): InstrumentComponent
    }
}
