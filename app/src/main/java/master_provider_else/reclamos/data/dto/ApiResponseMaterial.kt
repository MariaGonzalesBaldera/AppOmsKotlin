package master_provider_else.reclamos.data.dto

import com.google.gson.annotations.SerializedName

data class ApiResponseMaterial (
  @SerializedName("Respuesta") val respuesta: RespuestaMaterial
)

data class RespuestaMaterial(
  @SerializedName("error") val error: Int,
  @SerializedName("message") val message: String,
  @SerializedName("body") val body: ResponseBodyMaterial,
  @SerializedName("method") val method: String
)
data class ResponseBodyMaterial(
  @SerializedName("smMaterial") val smMaterial: String

)
