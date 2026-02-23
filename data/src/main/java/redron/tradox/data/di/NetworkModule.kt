package redron.tradox.data.di

import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import javax.inject.Named
import javax.inject.Singleton

@Module
object NetworkModule {

    private const val API_KEY = "dummy_api_key"

    @Provides
    @Singleton
    fun provideJson(): Json =
        Json { ignoreUnknownKeys = true }

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient =
        HttpClient()

    @Provides
    @Singleton
    @Named("apiKey")
    fun provideApiKey(): String = API_KEY
}
