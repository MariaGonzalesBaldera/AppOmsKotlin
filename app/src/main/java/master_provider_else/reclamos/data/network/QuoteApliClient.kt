package master_provider_else.reclamos.data.network

import master_provider_else.reclamos.data.dto.LoginRequest
import master_provider_else.reclamos.data.dto.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface QuoteApliClient {
  @POST("api/Token")
  suspend fun loginUser(
    @Body request: LoginRequest
  ): Response<ApiResponse>

  @GET("/getReportClaim")
    suspend fun reportClaim():Response<ApiResponse>
}