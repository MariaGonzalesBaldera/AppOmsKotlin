package master_provider_else.reclamos.data.dto

import com.google.gson.annotations.SerializedName

data class ApiResponseFinTrabajoCompleto(
  @SerializedName("Respuesta") val respuesta: FinTrabajo
  )
data class FinTrabajo(
  @SerializedName("error") val error: Int,
  @SerializedName("message") val message: String,
  @SerializedName("body") val body: ResponseBodyFinTrabajo,
  @SerializedName("method") val method: String
)

data class ResponseBodyFinTrabajo(
  @SerializedName("Ubicacion") val Ubicacion: List<Fin>
)

//falta verififcar respuesta
data class Fin(
  @SerializedName("Inicio") val Inicio: String

)