package master_provider_else.reclamos.data.database.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "InformeOMSAPNodo", primaryKeys = ["CodigoReclamo", "CodigoUbicacionElectrica"])
data class InformeOMSAPNodoEntity(
  @NonNull
  @ColumnInfo(name = "CodigoUbicacionElectrica") val CodigoUbicacionElectrica: String,
  @NonNull
  @ColumnInfo(name = "CodigoReclamo") val CodigoReclamo: String,
  @ColumnInfo(name = "CodigoCuadrilla") val CodigoCuadrilla: String,
  @ColumnInfo(name = "Nodo") val Nodo: String,
  @ColumnInfo(name = "estado") val estado: String,
  @ColumnInfo(name = "enviado") val enviado: String,
)
