package master_provider_else.reclamos.data.dto

import com.google.gson.annotations.SerializedName

data class InicioTrabajoRequest(
  @SerializedName("codigoReclamo") val codigoReclamo: String,
  @SerializedName("codigoCuadrilla") val codigoCuadrilla: String,
  @SerializedName("loginUsuario") val loginUsuario: String

  )
