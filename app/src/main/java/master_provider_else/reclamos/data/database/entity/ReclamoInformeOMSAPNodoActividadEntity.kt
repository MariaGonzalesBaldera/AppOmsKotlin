package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
  tableName = "ReclamoInformeOMSAPNodoActividad",
  primaryKeys = ["CodigoReclamo", "CodigoActividad", "CodigoUbicacionElectrica"]
)
data class ReclamoInformeOMSAPNodoActividadEntity(
  @ColumnInfo(name = "CodigoReclamo") val CodigoReclamo: String,
  @ColumnInfo(name = "CodigoCuadrilla") val CodigoCuadrilla: String,
  @ColumnInfo(name = "CodigoUbicacionElectrica") val CodigoUbicacionElectrica: String,
  @ColumnInfo(name = "CodigoActividad") val CodigoActividad: String,
  @ColumnInfo(name = "NombreActividad") val NombreActividad: String,
  @ColumnInfo(name = "Realizada") val Realizada: String,
  @ColumnInfo(name = "Enviado") val Enviado: String="-1",
)
