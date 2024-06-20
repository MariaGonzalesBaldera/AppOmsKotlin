package master_provider_else.reclamos.domain.model

import master_provider_else.reclamos.data.database.entity.UserEntity
import master_provider_else.reclamos.data.model.UserModel

data class User(
  val usuario: String,
  val pass: String,
  val cuadrilla: String = "",
  val nombreUsuario: String = "",
  val recordar: String = "",
  val token: String = "",
  val estadoDB: String = "",
  val tiempoNotificacion: String = ""
)

fun UserEntity.toDomain() = User(
  usuario,
  pass,
  cuadrilla,
  nombreUsuario,
  recordar,
  token,
  estadoDB,
  tiempoNotificacion
)
