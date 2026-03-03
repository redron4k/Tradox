package redron.tradox.domain.di

import dagger.Module
import dagger.Provides
import redron.tradox.domain.repository.IPinnedRepository
import redron.tradox.domain.repository.IPriceRepository
import redron.tradox.domain.usecase.GetInstrumentDetailsUseCase
import redron.tradox.domain.usecase.GetPinnedUseCase
import redron.tradox.domain.usecase.LoadInitialPricesUseCase
import redron.tradox.domain.usecase.ObservePriceUseCase
import redron.tradox.domain.usecase.ObservePricesUseCase
import javax.inject.Singleton

@Module
object DomainModule {

    @Provides
    @Singleton
    fun provideObservePriceUseCase(
        repository: IPriceRepository
    ): ObservePriceUseCase {
        return ObservePriceUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideObservePricesUseCase(
        repository: IPriceRepository
    ): ObservePricesUseCase {
        return ObservePricesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLoadInitialPricesUseCase(
        repository: IPriceRepository
    ): LoadInitialPricesUseCase {
        return LoadInitialPricesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetInstrumentDetailsUseCase(
        repository: IPriceRepository
    ): GetInstrumentDetailsUseCase {
        return GetInstrumentDetailsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetPinnedUseCase(
        repository: IPinnedRepository
    ): GetPinnedUseCase {
        return GetPinnedUseCase(repository)
    }
}
