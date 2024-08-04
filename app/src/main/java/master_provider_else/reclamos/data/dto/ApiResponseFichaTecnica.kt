package master_provider_else.reclamos.data.dto

import com.google.gson.annotations.SerializedName

data class ApiResponseFichaTecnica(
  @SerializedName("Respuesta") val respuesta: RespuestaFichaTecnica
)

data class RespuestaFichaTecnica(
  @SerializedName("error") val error: Int,
  @SerializedName("message") val message: String,
  @SerializedName("body") val body: ResponseFichaTecnica,
  @SerializedName("method") val method: String
)

data class ResponseFichaTecnica(
  @SerializedName("FichaTecnica") val fichaTecnica: String
)
