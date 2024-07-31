package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
  tableName = "ReclamoInformeOMSDesestimado",
  primaryKeys = ["CodigoReclamo", "CodigoCuadrilla", "CodigoMotivoDesestimacion"]
)
data class ReclamoInformeOMSDesestimadoEntity(
  @ColumnInfo("CodigoReclamo") val CodigoReclamo: String,
  @ColumnInfo("CodigoCuadrilla") val CodigoCuadrilla: String,
  @ColumnInfo("CodigoMotivoDesestimacion") val CodigoMotivoDesestimacion: String,
  @ColumnInfo("enviado") val enviado: String,
)