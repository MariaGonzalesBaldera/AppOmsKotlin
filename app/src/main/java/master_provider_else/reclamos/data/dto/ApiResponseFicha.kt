package master_provider_else.reclamos.data.dto

import com.google.gson.annotations.SerializedName

//api/Reclamo/RecuperarFicha

data class ApiResponseFicha(
  @SerializedName("Respuesta") val respuesta: RespuestaFicha
)

data class RespuestaFicha(
  @SerializedName("error") val error: Int,
  @SerializedName("message") val message: String,
  @SerializedName("body") val body: ResponseBodyFicha,
  @SerializedName("method") val method: String
)

data class ResponseBodyFicha(
  @SerializedName("ReclamoInformeOMS") val reclamoInformeOMS: String
)