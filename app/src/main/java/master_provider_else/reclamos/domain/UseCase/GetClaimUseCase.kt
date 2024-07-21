package master_provider_else.reclamos.domain.UseCase

import android.util.Log
import master_provider_else.reclamos.data.QuoteRepository
import javax.inject.Inject

class GetClaimUseCase @Inject constructor(
  private val repository: QuoteRepository
) {
  suspend fun fetchClaims(
    contentType: String,
    authorization: String,
    strCodigoCuadrilla: String,
    strCodigoEstadoReclamo: String,
    strAP: String
  ) {
    try {
      val response = repository.getClaimFromApi(
        contentType = contentType,
        authorization = "Bearer $authorization",
        strCodigoCuadrilla = strCodigoCuadrilla,
        strCodigoEstadoReclamo = strCodigoEstadoReclamo,
        strAP = strAP
      )
      if (response.isSuccessful) {
        val apiResponse = response.body()
        apiResponse?.let {
          val reclamos = it.respuesta.body.reclamo
          reclamos.forEach { reclamo ->
            Log.e(
              "data claim",
              "Reclamo ID: ${reclamo.codigoReclamo}, Descripción: ${reclamo.descripcionReclamo}"
            )
          }
        }
      } else {
        println("Error: ${response.errorBody()?.string()}")
      }
    } catch (e: Exception) {
      println("Exception: ${e.message}")
    }
  }
}