package master_provider_else.reclamos.data.dto

data class ResponseBody(
  val codigoCuadrilla: Int,
  val nombreusuario: String,
  val tiempoNotificacion: String?,
  val token: String?
)
