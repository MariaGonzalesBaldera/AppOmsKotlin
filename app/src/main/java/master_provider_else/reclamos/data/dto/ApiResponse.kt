package master_provider_else.reclamos.data.dto

import com.google.gson.annotations.SerializedName

data class ApiResponse(
  @SerializedName("Respuesta") val respuesta: Respuesta
)
