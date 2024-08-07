package master_provider_else.reclamos.data.dto

import com.google.gson.annotations.SerializedName

data class ApiResponseGuardarMaterial(
  @SerializedName("Respuesta") val respuesta: GuardarMaterialRespuesta
)

data class GuardarMaterialRespuesta(
  @SerializedName("error") val error: Int,
  @SerializedName("message") val message: String,
  @SerializedName("body") val body: BodyMaterialRespuesta,
  @SerializedName("method") val method: String
)

data class BodyMaterialRespuesta(
  @SerializedName("Ubicacion") val Ubicacion: List<MaterialRes>
)

//falta verififcar respuesta
data class MaterialRes(
  @SerializedName("Inicio") val Inicio: String

)
