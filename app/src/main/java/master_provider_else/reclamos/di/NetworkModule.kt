package master_provider_else.reclamos.di

import master_provider_else.reclamos.data.network.QuoteApliClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Singleton
  @Provides
  fun providerRetrofit(): Retrofit {
    val url = "http://192.168.1.110:5000/";
    return Retrofit.Builder()
      .baseUrl(url)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Singleton
  @Provides
  fun provideQuoteApiClient(retrofit: Retrofit): QuoteApliClient {
    return retrofit.create(QuoteApliClient::class.java)
  }
}