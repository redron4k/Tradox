package redron.tradox.domain.di

import dagger.Module
import dagger.Provides
import redron.tradox.domain.repository.IPriceRepository
import redron.tradox.domain.usecase.ObservePriceUseCase
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
}
