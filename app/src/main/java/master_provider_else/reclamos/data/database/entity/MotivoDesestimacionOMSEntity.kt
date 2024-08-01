package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "MotivoDesestimacionOMS", primaryKeys = ["CodigoMotivoDesestimacion"])
data class MotivoDesestimacionOMSEntity(
  @ColumnInfo(name = "CodigoMotivoDesestimacion") val CodigoMotivoDesestimacion: String,
  @ColumnInfo(name = "NombreMotivoDesestimacion") val NombreMotivoDesestimacion: String,
)
