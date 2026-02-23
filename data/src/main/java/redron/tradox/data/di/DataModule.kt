package redron.tradox.data.di

import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import redron.tradox.core.network.websocket.alltick.AllTickWebSocketClient
import redron.tradox.core.network.websocket.datasource.AllTickDataSource
import redron.tradox.data.repository.PriceRepository
import redron.tradox.data.stream.PriceStreamManager
import redron.tradox.domain.repository.IPriceRepository
import javax.inject.Named
import javax.inject.Singleton


@Module
object DataModule {

    @Provides
    @Singleton
    fun provideAllTickWebSocketClient(
        client: HttpClient,
        @Named("apiKey") apiKey: String
    ): AllTickWebSocketClient {
        return AllTickWebSocketClient(client = client, apiKey = apiKey)
    }

    @Provides
    @Singleton
    fun provideAllTickDataSource(client: AllTickWebSocketClient): AllTickDataSource {
        return AllTickDataSource(client)
    }

    @Provides
    @Singleton
    fun providePriceStreamManager(dataSource: AllTickDataSource): PriceStreamManager {
        return PriceStreamManager(dataSource)
    }

    @Provides
    @Singleton
    fun providePriceRepository(manager: PriceStreamManager): IPriceRepository {
        return PriceRepository(manager)
    }
}
