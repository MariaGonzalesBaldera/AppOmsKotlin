package master_provider_else.reclamos.data.dto

import com.google.gson.annotations.SerializedName

data class ApiResponseInicioTrabajo(
  @SerializedName("Respuesta") val respuesta: RespuestaInicio

)

data class RespuestaInicio(
  @SerializedName("error") val error: Int,
  @SerializedName("message") val message: String,
  @SerializedName("body") val body: ResponseBodyInicio,
  @SerializedName("method") val method: String
)

data class ResponseBodyInicio(
  @SerializedName("Ubicacion") val Ubicacion: List<Inicio>
)

//falta verififcar respuesta
data class Inicio(
  @SerializedName("Inicio") val Inicio: String

)