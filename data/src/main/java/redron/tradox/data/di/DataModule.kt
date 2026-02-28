package redron.tradox.data.di

import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import redron.tradox.core.network.rest.alltick.AllTickRestApi
import redron.tradox.core.network.websocket.alltick.AllTickWebSocketClient
import redron.tradox.core.network.websocket.datasource.AllTickDataSource
import redron.tradox.data.repository.InstrumentRepository
import redron.tradox.data.repository.PriceRepository
import redron.tradox.data.stream.PriceStreamManager
import redron.tradox.domain.repository.IInstrumentRepository
import redron.tradox.domain.repository.IPriceRepository
import javax.inject.Named
import javax.inject.Singleton


@Module
object DataModule {

    @Provides
    @Singleton
    fun provideAllTickWebSocketClient(
        client: HttpClient,
        @Named("apiKey") apiKey: String,
        json: Json
    ): AllTickWebSocketClient {
        return AllTickWebSocketClient(
            client = client,
            apiKey = apiKey,
            json = json,
        )
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

    @Provides
    @Singleton
    fun provideAllTickRestApi(client: HttpClient): AllTickRestApi {
        return AllTickRestApi(client)
    }

    @Provides
    @Singleton
    fun provideInstrumentRepository(api: AllTickRestApi): IInstrumentRepository {
        return InstrumentRepository(api)
    }
}
