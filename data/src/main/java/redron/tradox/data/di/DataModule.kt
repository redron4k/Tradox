package redron.tradox.data.di

import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import redron.tradox.core.network.rest.alltick.AllTickRestApi
import redron.tradox.core.network.websocket.alltick.AllTickWebSocketClient
import redron.tradox.core.network.datasource.AllTickDataSource
import redron.tradox.data.repository.PriceRepository
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
        json: Json,
    ): AllTickWebSocketClient {
        return AllTickWebSocketClient(
            client = client,
            apiKey = apiKey,
            json = json,
        )
    }

    @Provides
    @Singleton
    fun provideAllTickDataSource(
        client: AllTickWebSocketClient,
        restApi: AllTickRestApi,
    ): AllTickDataSource {
        return AllTickDataSource(
            client = client,
            restApi = restApi,
        )
    }

    @Provides
    @Singleton
    fun providePriceRepository(
        datasource: AllTickDataSource,
    ): IPriceRepository {
        return PriceRepository(
            datasource = datasource,
        )
    }

    @Provides
    @Singleton
    fun provideAllTickRestApi(
        client: HttpClient,
        @Named("apiKey") apiKey: String,
        json: Json,
    ): AllTickRestApi {
        return AllTickRestApi(
            client = client,
            apiKey = apiKey,
            json = json,
        )
    }
}
