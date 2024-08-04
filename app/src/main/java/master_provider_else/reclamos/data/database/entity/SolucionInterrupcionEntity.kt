package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SolucionInterrupcion")
data class SolucionInterrupcionEntity(
  @PrimaryKey
  @ColumnInfo("CodigoTipoSolucionInterrupcion") val CodigoTipoSolucionInterrupcion: String,
  @ColumnInfo("NombreTipoSolucionInterrupcion") val NombreTipoSolucionInterrupcion: String,
  @ColumnInfo("Simbolo") val Simbolo: String,
  @ColumnInfo("Observacion") val Observacion: String = "",
)
