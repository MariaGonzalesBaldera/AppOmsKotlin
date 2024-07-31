package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TipoManiobraCapacidad")
data class TipoManiobraCapacidadEntity(
  @PrimaryKey
  @ColumnInfo(name="CodigoTipoManiobraCapacidad")val CodigoTipoManiobraCapacidad:String,
  @ColumnInfo(name="CodigoTipoEquipoProteccionManiobra")val CodigoTipoEquipoProteccionManiobra:String,
  @ColumnInfo(name="NombreTipoManiobraCapacidad")val NombreTipoManiobraCapacidad:String,
)