package master_provider_else.reclamos.data.dto

import com.google.gson.annotations.SerializedName

data class ApiResponseReclamo(
  @SerializedName("Respuesta") val respuesta: RespuestaReclamo
)