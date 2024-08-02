package master_provider_else.reclamos.data.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import master_provider_else.reclamos.data.dto.LoginRequest
import master_provider_else.reclamos.data.dto.ApiResponse
import master_provider_else.reclamos.data.dto.ApiResponseArchivoMovil
import master_provider_else.reclamos.data.dto.ApiResponseEncuesta
import master_provider_else.reclamos.data.dto.ApiResponseEstado
import master_provider_else.reclamos.data.dto.ApiResponseFicha
import master_provider_else.reclamos.data.dto.ApiResponseMaterial
import master_provider_else.reclamos.data.dto.ApiResponseReclamo
import master_provider_else.reclamos.data.dto.EstadoRequest
import retrofit2.Response
import javax.inject.Inject

class QuoteService @Inject constructor(private val api: QuoteApliClient) {

  suspend fun getLogin(usuario: String, pass: String): Response<ApiResponse> {
    return withContext(Dispatchers.IO) {
      val loginRequest = LoginRequest(usuario, pass);
      val response: Response<ApiResponse> = api.loginUser(loginRequest);
      Log.e("Log QuoteService", response.message().toString())
      response
    }
  }

  suspend fun getReclamo(
    contentType: String,
    authorization: String,
    strCodigoCuadrilla: String,
    strCodigoEstadoReclamo: String,
    strAP: String
  ): Response<ApiResponseReclamo> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseReclamo> =
        api.reportClaim(
          contentType,
          authorization,
          strCodigoCuadrilla,
          strCodigoEstadoReclamo,
          strAP
        );
      Log.e("Log QuoteService reclamo", response.message().toString())
      response
    }
  }

  suspend fun getFichaTecnica(
    contentType: String,
    authorization: String,
    strCodigoReclamo: String,
    strCodigoCuadrilla: String,
  ): Response<ApiResponseFicha> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseFicha> =
        api.recuperarFicha(
          contentType,
          authorization,
          strCodigoReclamo,
          strCodigoCuadrilla,
        );
      Log.e("Recup.Ficha", "RecuperarFicha" + response.message().toString())
      response
    }
  }

  suspend fun getReclamoInformeMaterial(
    contentType: String,
    authorization: String,
    strCodigoReclamo: String,
    strCodigoCuadrilla: String,
  ): Response<ApiResponseMaterial> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseMaterial> =
        api.reclamoInformeMaterial(
          contentType,
          authorization,
          strCodigoReclamo,
          strCodigoCuadrilla,
        );
      Log.e("ReclamoInformeMaterial", "ReclamoInformeMaterial" + response.message().toString())
      response
    }
  }

  suspend fun getReclamoComercialArchivoMovilLeer(
    contentType: String,
    authorization: String,
    strCodigoReclamo: String,
  ): Response<ApiResponseArchivoMovil> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseArchivoMovil> =
        api.reclamoComercialArchivoMovilLeer(
          contentType,
          authorization,
          strCodigoReclamo,
        );
      Log.e(
        "ReclamoComercialArchivoMovilLeer",
        "ReclamoComercialArchivoMovilLeer" + response.message().toString()
      )
      response
    }
  }

  suspend fun getEncuesta(
    contentType: String,
    authorization: String,
  ): Response<ApiResponseEncuesta> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseEncuesta> = api.reclamoEncuesta(
        contentType,
        authorization
      );
      Log.e("Log Encuesta", "Encuesta" + response.message().toString())
      response
    }
  }

  suspend fun getCambioEstado(
    contentType: String,
    authorization: String,
    request: EstadoRequest
  ): Response<ApiResponseEstado> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseEstado> = api.cambiarEstado(
        contentType,
        authorization,
        request
      );
      Log.e("Log QuoteService", response.message().toString())
      response
    }
  }
}