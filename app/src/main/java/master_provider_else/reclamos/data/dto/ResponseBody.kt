package master_provider_else.reclamos.data.dto

import com.google.gson.annotations.SerializedName

data class ResponseBody(
  @SerializedName("token") val token: String,
  @SerializedName("codigoCuadrilla") val codigoCuadrilla: String,
  @SerializedName("nombreusuario") val nombreUsuario: String,
  @SerializedName("TiempoNotificacion") val tiempoNotificacion: String
)
