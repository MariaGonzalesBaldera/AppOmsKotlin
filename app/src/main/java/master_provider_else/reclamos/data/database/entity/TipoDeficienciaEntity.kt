package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TipoDeficiencia")
data class TipoDeficienciaEntity(
  @PrimaryKey
  @ColumnInfo("CodigoTipoDeficienciaAP") val CodigoTipoDeficienciaAP: String,
  @ColumnInfo("NombreTipoDeficienciaAP") val NombreTipoDeficienciaAP: String,
  @ColumnInfo("SimboloNTCSE") val SimboloNTCSE: String,
)
