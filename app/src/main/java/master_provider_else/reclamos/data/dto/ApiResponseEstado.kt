package master_provider_else.reclamos.data.dto

import com.google.gson.annotations.SerializedName

data class ApiResponseEstado(
  @SerializedName("Respuesta") val respuesta: RespuestaEstado
)

data class RespuestaEstado(
  @SerializedName("error") val error: Int,
  @SerializedName("message") val message: String,
  @SerializedName("body") val body: ResponseBodyEstado,
  @SerializedName("method") val method: String
)

data class ResponseBodyEstado(
  @SerializedName("CodigoEstado") val codigoEstado: String

)

