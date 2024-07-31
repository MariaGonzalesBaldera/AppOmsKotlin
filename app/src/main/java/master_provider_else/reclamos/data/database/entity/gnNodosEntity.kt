package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "gnNodos", primaryKeys = ["CodigoUbicacionElectrica"])
data class gnNodosEntity(
  @ColumnInfo(name = "CodigoUbicacionElectrica") val CodigoUbicacionElectrica: String,
  @ColumnInfo(name = "DireccionElectrica") val DireccionElectrica: String,
  @ColumnInfo(name = "CodigoNTCSE") val CodigoNTCSE: String,
  @ColumnInfo(name = "Geom") val Geom: String,
  @ColumnInfo(name = "CodigoTipoUbicacionElectrica") val CodigoTipoUbicacionElectrica: String,
  @ColumnInfo(name = "TipoCoordenada") val TipoCoordenada: String,
  @ColumnInfo(name = "SED") val SED: String,
  @ColumnInfo(name = "Latitud") val Latitud: String,
  @ColumnInfo(name = "Longitud") val Longitud: String,
)
