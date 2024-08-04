package master_provider_else.reclamos.data.network

import master_provider_else.reclamos.data.dto.LoginRequest
import master_provider_else.reclamos.data.dto.ApiResponse
import master_provider_else.reclamos.data.dto.ApiResponseArchivoMovil
import master_provider_else.reclamos.data.dto.ApiResponseEncuesta
import master_provider_else.reclamos.data.dto.ApiResponseEstado
import master_provider_else.reclamos.data.dto.ApiResponseFicha
import master_provider_else.reclamos.data.dto.ApiResponseFichaMapa
import master_provider_else.reclamos.data.dto.ApiResponseFichaTecnica
import master_provider_else.reclamos.data.dto.ApiResponseInicioTrabajo
import master_provider_else.reclamos.data.dto.ApiResponseMaterial
import master_provider_else.reclamos.data.dto.ApiResponseReclamo
import master_provider_else.reclamos.data.dto.EstadoRequest
import master_provider_else.reclamos.data.dto.InicioTrabajoRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface QuoteApliClient {

  @POST("api/Token")
  suspend fun loginUser(
    @Body request: LoginRequest
  ): Response<ApiResponse>

  @GET("api/Reclamo/ObtenerReclamos/{strCodigoCuadrilla}/{strCodigoEstadoReclamo}/{strAP}")
  suspend fun reportClaim(
    @Header("Content-Type") contentType: String = "application/json; charset=UTF-8",
    @Header("Authorization") authorization: String,
    @Path("strCodigoCuadrilla") strCodigoCuadrilla: String,
    @Path("strCodigoEstadoReclamo") strCodigoEstadoReclamo: String,
    @Path("strAP") strAP: String
  ): Response<ApiResponseReclamo>

  @GET("api/Reclamo/RecuperarFicha/{strCodigoReclamo}/{strCodigoCuadrilla}")
  suspend fun recuperarFicha(
    @Header("Content-Type") contentType: String = "application/json; charset=UTF-8",
    @Header("Authorization") authorization: String,
    @Path("strCodigoReclamo") strCodigoReclamo: String,
    @Path("strCodigoCuadrilla") strCodigoCuadrilla: String,
  ): Response<ApiResponseFicha>

  @GET("api/ReclamoInformeMaterial/Listar?strCodigoReclamo={strCodigoReclamo}&strCodigoCuadrilla={strCodigoCuadrilla}")
  suspend fun reclamoInformeMaterial(
    @Header("Content-Type") contentType: String = "application/json; charset=UTF-8",
    @Header("Authorization") authorization: String,
    @Path("strCodigoReclamo") strCodigoReclamo: String,
    @Path("strCodigoCuadrilla") strCodigoCuadrilla: String,
  ): Response<ApiResponseMaterial>

  @GET("api/Reclamo/ReclamoComercialArchivoMovilLeer/{strCodigoReclamo}")
  suspend fun reclamoComercialArchivoMovilLeer(
    @Header("Content-Type") contentType: String = "application/json; charset=UTF-8",
    @Header("Authorization") authorization: String,
    @Path("strCodigoReclamo") strCodigoReclamo: String,
  ): Response<ApiResponseArchivoMovil>

  @GET("api/Reclamo/Encuesta")
  suspend fun reclamoEncuesta(
    @Header("Content-Type") contentType: String = "application/json; charset=UTF-8",
    @Header("Authorization") authorization: String,
  ): Response<ApiResponseEncuesta>

  @POST("api/Reclamo/CambiarEstado")
  suspend fun cambiarEstado(
    @Header("Content-Type") contentType: String = "application/json; charset=UTF-8",
    @Header("Authorization") authorization: String,
    @Body request: EstadoRequest
  ): Response<ApiResponseEstado>

  @GET("api/FichaTecnica")
  suspend fun getFichaTecnica(
    @Header("Content-Type") contentType: String = "application/json; charset=UTF-8",
    @Header("Authorization") authorization: String,
  ): Response<ApiResponseFichaTecnica>

  @GET("api/Reclamo/ObtenerMapa/{strCodigoCuadrilla}/{strAMT}")
  suspend fun getMapa(
    @Header("Content-Type") contentType: String = "application/json; charset=UTF-8",
    @Header("Authorization") authorization: String,
    @Path("strCodigoCuadrilla") strCodigoCuadrilla: String,
    @Path("strAMT") strAMT: String
  ): Response<ApiResponseFichaMapa>

  @POST("api/Reclamo/InicioTrabajo")
  suspend fun iniciarTrabajo(
    @Header("Content-Type") contentType: String = "application/json; charset=UTF-8",
    @Header("Authorization") authorization: String,
    @Body request: InicioTrabajoRequest
  ): Response<ApiResponseInicioTrabajo>

}









