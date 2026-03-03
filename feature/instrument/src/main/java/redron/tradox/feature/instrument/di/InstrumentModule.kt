package redron.tradox.feature.instrument.di

import dagger.Module
import dagger.Provides
import redron.tradox.domain.usecase.GetInstrumentDetailsUseCase
import redron.tradox.feature.instrument.mvi.InstrumentViewModel

@Module
class InstrumentModule {
    @Provides
    fun provideInstrumentViewModel(
        getDetails: GetInstrumentDetailsUseCase
    ): InstrumentViewModel = InstrumentViewModel(getDetails)

    @Provides
    fun provideInstrumentViewModelFactory(
        viewModel: InstrumentViewModel
    ): InstrumentViewModelFactory = InstrumentViewModelFactory { viewModel }
}
