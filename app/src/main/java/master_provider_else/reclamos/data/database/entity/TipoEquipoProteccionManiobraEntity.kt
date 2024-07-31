package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TipoEquipoProteccionManiobra")
data class TipoEquipoProteccionManiobraEntity(
  @PrimaryKey
  @ColumnInfo(name="codigoTipoEquipoProteccionManiobra")val codigoTipoEquipoProteccionManiobra:String,
  @ColumnInfo(name="nombreTipoEquipoProteccionManiobra")val nombreTipoEquipoProteccionManiobra:String,
  @ColumnInfo(name="codigoTipoInstalacionElectricaAfectada")val codigoTipoInstalacionElectricaAfectada:String,
  @ColumnInfo(name="simbolo")val simbolo:String,
)
