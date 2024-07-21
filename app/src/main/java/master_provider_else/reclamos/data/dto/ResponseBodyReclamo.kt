package master_provider_else.reclamos.data.dto

import com.google.gson.annotations.SerializedName

data class ResponseBodyReclamo(
  @SerializedName("Reclamo") val reclamo: List<ReclamoArray>
)
