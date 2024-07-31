package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TipoDenuncia")
data class TipoDenunciaEntity(
  @PrimaryKey
  @ColumnInfo(name = "CodigoTipoDenuncia") val CodigoTipoDenuncia: String,
  @ColumnInfo(name = "NombreTipoDenuncia") val NombreTipoDenuncia: String,
  @ColumnInfo(name = "Simbolo") val Simbolo: String,
)
