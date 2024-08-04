package master_provider_else.reclamos.data.dto

import com.google.gson.annotations.SerializedName

data class ApiResponseFichaMapa(
  @SerializedName("Respuesta") val respuesta: RespuestaMapa

)

data class RespuestaMapa(
  @SerializedName("error") val error: Int,
  @SerializedName("message") val message: String,
  @SerializedName("body") val body: ResponseBodyMapa,
  @SerializedName("method") val method: String
)

data class ResponseBodyMapa(
  @SerializedName("Ubicacion") val Ubicacion: List<Ubicacion>
)

//falta ver respuesta
data class Ubicacion(
  @SerializedName("method") val method: String
)