package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import master_provider_else.reclamos.domain.model.User

@Entity(tableName = "Usuario")
data class UserEntity(
  @PrimaryKey(autoGenerate = false)
  @ColumnInfo(name = "Usuario") @SerializedName("usuario") val usuario: String = "",
  @ColumnInfo(name = "Pass") @SerializedName("pass") val pass: String = "",
  @ColumnInfo(name = "Cuadrilla") @SerializedName("codigoCuadrilla") val cuadrilla: String = "",
  @ColumnInfo(name = "NombreUsuario") @SerializedName("nombreusuario") val nombreUsuario: String = "",
  @ColumnInfo(name = "Token") @SerializedName("token") val token: String = "",
  @ColumnInfo(name = "TiempoNotificacion") @SerializedName("TiempoNotificacion") val tiempoNotificacion: String = ""
)

fun User.toDatabase() = UserEntity(
  usuario = usuario,
  pass = pass,
  cuadrilla = cuadrilla,
  nombreUsuario = nombreUsuario,
  token = token,
  tiempoNotificacion = tiempoNotificacion
)