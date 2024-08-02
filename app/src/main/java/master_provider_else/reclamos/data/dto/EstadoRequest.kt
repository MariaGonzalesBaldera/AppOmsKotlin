package master_provider_else.reclamos.data.dto

import com.google.gson.annotations.SerializedName

data class EstadoRequest(
  @SerializedName("CodigoOrigenOMS") val codigoOrigenOMS: String,
  @SerializedName("CodigoEstado") val codigoEstado: String,
  @SerializedName("Siguiente") val siguiente: String
)
