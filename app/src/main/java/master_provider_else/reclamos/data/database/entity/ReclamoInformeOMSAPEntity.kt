package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "ReclamoInformeOMSAP", primaryKeys = ["CodigoReclamo", "CodigoCuadrilla"])
data class ReclamoInformeOMSAPEntity(
  @ColumnInfo(name = "CodigoReclamo") var CodigoReclamo: String = "",
  @ColumnInfo(name = "CodigoCuadrilla") var CodigoCuadrilla: String = "",
  @ColumnInfo(name = "CodigoTipoDenuncia") val CodigoTipoDenuncia: String = "",
  @ColumnInfo(name = "CodigoTipoDeficienciaRegistrada") val CodigoTipoDeficienciaRegistrada: String = "",
  @ColumnInfo(name = "CodigoTipoDeficienciaVerificada") val CodigoTipoDeficienciaVerificada: String = "",
  @ColumnInfo(name = "Nodo") val Nodo: String = "",
  @ColumnInfo(name = "FechaVerificacion") val FechaVerificacion: Long = 0,
  @ColumnInfo(name = "FechaSubsanacion") val FechaSubsanacion: Long = 0,
  @ColumnInfo(name = "OrdenTrabajo") val OrdenTrabajo: String = "",
  @ColumnInfo(name = "Tipo") val Tipo: String = "",
  @ColumnInfo(name = "Enviado") val Enviado: Int = 0,
)
