package master_provider_else.reclamos.data.dto

import com.google.gson.annotations.SerializedName

data class ApiResponseGeneral(
  @SerializedName("Respuesta") val respuesta: RespuestaFoto

)

data class RespuestaFoto(
  @SerializedName("error") val error: Int,
  @SerializedName("message") val message: String,
  @SerializedName("body") val body: ResponseBodyFinTrabajo,
  @SerializedName("method") val method: String
)
