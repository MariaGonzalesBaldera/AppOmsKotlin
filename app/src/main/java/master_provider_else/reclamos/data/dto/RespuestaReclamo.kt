package master_provider_else.reclamos.data.dto

import com.google.gson.annotations.SerializedName

data class RespuestaReclamo(
  @SerializedName("error") val error: Int,
  @SerializedName("message") val message: String,
  @SerializedName("body") val body: ResponseBodyReclamo,
  @SerializedName("method") val method: String
)
