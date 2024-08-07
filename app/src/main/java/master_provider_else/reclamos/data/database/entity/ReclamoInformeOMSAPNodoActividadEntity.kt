package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
  tableName = "ReclamoInformeOMSAPNodoActividad",
  primaryKeys = ["CodigoReclamo", "CodigoActividad", "CodigoUbicacionElectrica"]
)
data class ReclamoInformeOMSAPNodoActividadEntity(
  @ColumnInfo(name = "CodigoReclamo") var CodigoReclamo: String = "",
  @ColumnInfo(name = "CodigoCuadrilla") var CodigoCuadrilla: String = "",
  @ColumnInfo(name = "CodigoUbicacionElectrica") var CodigoUbicacionElectrica: String = "",
  @ColumnInfo(name = "CodigoActividad") var CodigoActividad: String = "",
  @ColumnInfo(name = "NombreActividad") var NombreActividad: String = "",
  @ColumnInfo(name = "Realizada") var Realizada: String = "",
  @ColumnInfo(name = "Enviado") var Enviado: String = "-1",
)
