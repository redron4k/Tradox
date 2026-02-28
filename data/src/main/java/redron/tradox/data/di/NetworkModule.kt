package redron.tradox.data.di

import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import redon.tradox.data.BuildConfig
import redron.tradox.core.network.common.client.HttpClientFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
        }

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient =
        HttpClientFactory.create()

    @Provides
    @Singleton
    @Named("apiKey")
    fun provideApiKey(): String = BuildConfig.ALLTICK_API_KEY
}
