package master_provider_else.reclamos.data.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import master_provider_else.reclamos.data.dto.LoginRequest
import master_provider_else.reclamos.data.dto.ApiResponse
import retrofit2.Response
import javax.inject.Inject

class QuoteService @Inject constructor(private val api: QuoteApliClient){

  suspend fun getLogin(usuario:String,pass:String):Response<ApiResponse>{
    return withContext(Dispatchers.IO){
      val loginRequest = LoginRequest(usuario,pass);
      val response:Response<ApiResponse> = api.loginUser(loginRequest);
      Log.e("Log QuoteService", response.message().toString())
      response
    }
  }
}