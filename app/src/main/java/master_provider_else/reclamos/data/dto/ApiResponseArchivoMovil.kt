package master_provider_else.reclamos.data.dto

import com.google.gson.annotations.SerializedName

data class ApiResponseArchivoMovil(
  @SerializedName("Respuesta") val respuesta: RespuestaArchivo
)

data class RespuestaArchivo(
  @SerializedName("error") val error: Int,
  @SerializedName("message") val message: String,
  @SerializedName("body") val body: ResponseBodyArchivo,
  @SerializedName("method") val method: String
)

data class ResponseBodyArchivo(
  @SerializedName("FotosReclamo") val fotosReclamo: String
)
