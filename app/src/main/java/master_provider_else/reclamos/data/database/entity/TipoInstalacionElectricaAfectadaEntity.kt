package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TipoInstalacionElectricaAfectada")
data class TipoInstalacionElectricaAfectadaEntity(
  @PrimaryKey
  @ColumnInfo("CodigoTipoInstalacionElectricaAfectada") val CodigoTipoInstalacionElectricaAfectada: String,
  @ColumnInfo("NombreTipoInstalacionElectricaAfectada") val NombreTipoInstalacionElectricaAfectada: String,
  @ColumnInfo("Simbolo") val Simbolo: String,
)
