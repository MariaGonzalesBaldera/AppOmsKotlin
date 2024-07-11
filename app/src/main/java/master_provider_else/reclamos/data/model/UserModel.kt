package master_provider_else.reclamos.data.model

data class UserModel(
  var usuario: String,
  var pass: String,
  var cuadrilla: String = "",
  var nombreUsuario: String = "",
  var token: String = "",
  var tiempoNotificacion: String = ""
)
