package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "ReclamoInformeOMSAP", primaryKeys = ["CodigoReclamo", "CodigoCuadrilla"])
data class ReclamoInformeOMSAPEntity(
  @ColumnInfo(name = "CodigoReclamo") val CodigoReclamo: String,
  @ColumnInfo(name = "CodigoCuadrilla") val CodigoCuadrilla: String,
  @ColumnInfo(name = "CodigoTipoDenuncia") val CodigoTipoDenuncia: String,
  @ColumnInfo(name = "CodigoTipoDeficienciaRegistrada") val CodigoTipoDeficienciaRegistrada: String,
  @ColumnInfo(name = "CodigoTipoDeficienciaVerificada") val CodigoTipoDeficienciaVerificada: String,
  @ColumnInfo(name = "Nodo") val Nodo: String,
  @ColumnInfo(name = "FechaVerificacion") val FechaVerificacion: Long,
  @ColumnInfo(name = "FechaSubsanacion") val FechaSubsanacion: Long,
  @ColumnInfo(name = "OrdenTrabajo") val OrdenTrabajo: String,
  @ColumnInfo(name = "Tipo") val Tipo: String,
  @ColumnInfo(name = "Enviado") val Enviado: Int
)
