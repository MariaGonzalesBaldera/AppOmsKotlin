package master_provider_else.reclamos.di

import android.util.Log
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import master_provider_else.reclamos.data.network.QuoteApliClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Singleton
  @Provides
  fun providerRetrofit(): Retrofit {
    val gson = GsonBuilder().create()
    //val url = "http://192.168.1.9:5000/";
    val API_SERVER = "https://appsrvtest.else.com.pe/wAPIOMSEmergenciasV2/"
    return Retrofit.Builder()
      .baseUrl(API_SERVER)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build()
  }

  @Singleton
  @Provides
  fun provideQuoteApiClient(retrofit: Retrofit): QuoteApliClient {
    return retrofit.create(QuoteApliClient::class.java)
  }
}