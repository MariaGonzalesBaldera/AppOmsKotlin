package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CausaAveria")
data class CausaAveriaEntity(
  @PrimaryKey(autoGenerate = false)
  @ColumnInfo(name = "codigoCausaAveria") val codigoCausaAveria: String,
  @ColumnInfo(name = "nombreCausaAveriaElectrica") val nombreCausaAveriaElectrica: String,
  @ColumnInfo(name = "codigoTipoInstalacionElectricaAfectada") val codigoTipoInstalacionElectricaAfectada: String,
  @ColumnInfo(name = "codigoTipoDenuncia") val codigoTipoDenuncia: String,
  @ColumnInfo(name = "codigoTipoInstalacionAfectada") val codigoTipoInstalacionAfectada: String,
)