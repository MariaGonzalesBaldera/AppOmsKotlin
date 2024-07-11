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
    //val url = "http://192.168.1.110:5000/";
    val url = "http://192.168.1.110:5000/";
    /*public static final String SERVER = "192.168.0.6:36611";
    public static final String API_FOLDER = "";*/
    val HTTPS_SCHEME ="https://"
    val SERVER = "appsrvtest.else.com.pe/"
    val API_FOLDER= "wAPIOMSEmergencias/"
    return Retrofit.Builder()
      .baseUrl("https://appsrvtest.else.com.pe/wAPIOMSEmergencias/")
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build()
    Log.e("url",HTTPS_SCHEME+SERVER+API_FOLDER);
  }

  @Singleton
  @Provides
  fun provideQuoteApiClient(retrofit: Retrofit): QuoteApliClient {
    return retrofit.create(QuoteApliClient::class.java)
  }
}