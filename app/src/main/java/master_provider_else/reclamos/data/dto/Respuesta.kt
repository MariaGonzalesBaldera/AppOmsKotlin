package master_provider_else.reclamos.data.dto

import com.google.gson.annotations.SerializedName

data class Respuesta(
  @SerializedName("error") val error: Int,
  @SerializedName("message") val message: String,
  @SerializedName("body") val body: ResponseBody,
  @SerializedName("method") val method: String
)

