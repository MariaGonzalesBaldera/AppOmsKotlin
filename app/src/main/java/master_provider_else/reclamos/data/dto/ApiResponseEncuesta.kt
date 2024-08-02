package master_provider_else.reclamos.data.dto

import com.google.gson.annotations.SerializedName

data class ApiResponseEncuesta(
  @SerializedName("Respuesta") val respuesta: RespuestaEncuesta
)

data class RespuestaEncuesta(
  @SerializedName("error") val error: Int,
  @SerializedName("message") val message: String,
  @SerializedName("body") val body: ResponseBodyEncuesta,
  @SerializedName("method") val method: String
)

data class ResponseBodyEncuesta(
  @SerializedName("Encuesta") val encuesta: List<Encuesta>
)

data class Encuesta(
  @SerializedName("Pregunta") val pregunta: List<Pregunta>
)

data class Pregunta(
  @SerializedName("CodigoEncuesta") val codigoEncuesta: String,
  @SerializedName("NombreEncuesta") val nombreEncuesta: String,
  @SerializedName("CodigoPregunta") val codigoPregunta: String,
  @SerializedName("NombrePregunta") val nombrePregunta: String,
  @SerializedName("CodigoTipoRespuesta") val codigoTipoRespuesta: String,
  @SerializedName("NombreTipoRespuesta") val nombreTipoRespuesta: String,
  @SerializedName("CodigoAlternativa") val codigoAlternativa: String,
  @SerializedName("Valor") val valor: String
)