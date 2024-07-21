package master_provider_else.reclamos.data.network

import master_provider_else.reclamos.data.dto.LoginRequest
import master_provider_else.reclamos.data.dto.ApiResponse
import master_provider_else.reclamos.data.dto.ApiResponseReclamo
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
    ):Response<ApiResponseReclamo>
}