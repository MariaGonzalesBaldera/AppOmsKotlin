package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import master_provider_else.reclamos.domain.model.User

@Entity(tableName = "Usuario")
data class UserEntity(
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id") val id: Int = 0,
  @ColumnInfo(name = "usuario") val usuario: String,
  @ColumnInfo(name = "pass") val pass: String,
  @ColumnInfo(name = "cuadrilla") val cuadrilla: String = "",
  @ColumnInfo(name = "nombreUsuario") val nombreUsuario: String = "",
  @ColumnInfo(name = "recordar") val recordar: String = "",
  @ColumnInfo(name = "token") val token: String = "",
  @ColumnInfo(name = "estadoDB") val estadoDB: String = "",
  @ColumnInfo(name = "tiempoNotificacion") val tiempoNotificacion: String = ""
)

fun User.toDatabase() = UserEntity(
  usuario = usuario,
  pass = pass,
  cuadrilla = cuadrilla,
  nombreUsuario = nombreUsuario,
  recordar = recordar,
  token = token,
  estadoDB = estadoDB,
  tiempoNotificacion = tiempoNotificacion
)