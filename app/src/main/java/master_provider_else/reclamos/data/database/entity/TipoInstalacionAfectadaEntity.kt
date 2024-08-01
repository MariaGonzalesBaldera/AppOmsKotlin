package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TipoInstalacionAfectada")
data class TipoInstalacionAfectada(
  @PrimaryKey
  @ColumnInfo("CodigoTipoUbicacionElectrica") val CodigoTipoUbicacionElectrica: String,
  @ColumnInfo("NombreTipoUbicacionElectrica") val NombreTipoUbicacionElectrica: String,
  @ColumnInfo("Simbolo") val Simbolo: String,
  @ColumnInfo("CodigoNTCSE") val CodigoNTCSE: String
)