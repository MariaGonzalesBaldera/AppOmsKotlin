package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ReclamoInformeOMS")
data class ReclamoInformeOMSEntity(
  @PrimaryKey(autoGenerate = false)
  @ColumnInfo(name = "CodigoReclamo") val CodigoReclamo: String = "",
  @ColumnInfo(name = "CodigoCuadrilla") val CodigoCuadrilla: String = "",
  @ColumnInfo(name = "CodigoTipoDenuncia") val CodigoTipoDenuncia: String = "",
  @ColumnInfo(name = "CodigoTipoInstalacionElectricaAfectada") val CodigoTipoInstalacionElectricaAfectada: String = "",
  @ColumnInfo(name = "CodigoTipoUbicacionAfectada") val CodigoTipoUbicacionAfectada: String = "",
  @ColumnInfo(name = "CodigoTipoEquipoProteccionManiobra") val CodigoTipoEquipoProteccionManiobra: String = "",
  @ColumnInfo(name = "CapacidadCarga") val CapacidadCarga: String = "",
  @ColumnInfo(name = "CodigoEquipoProteccionManiobraNodoCircuito") val CodigoEquipoProteccionManiobraNodoCircuito: String = "",
  @ColumnInfo(name = "CodigoEquipoProteccionManiobraSED") val CodigoEquipoProteccionManiobraSED: String = "",
  @ColumnInfo(name = "CodigoCausaAveriaElectrica") val CodigoCausaAveriaElectrica: String = "",
  @ColumnInfo(name = "ObservacionCausaAveriaElectrica") val ObservacionCausaAveriaElectrica: String = "",
  @ColumnInfo(name = "CodigoAccionSolucionAveria") val CodigoAccionSolucionAveria: String = "",
  @ColumnInfo(name = "ObservacionAccionSolucionAveria") val ObservacionAccionSolucionAveria: String = "",
  @ColumnInfo(name = "CodigoTipoSolucionIntervencion") val CodigoTipoSolucionIntervencion: String = "",
  @ColumnInfo(name = "CodigoTipoAreaIntervencion") val CodigoTipoAreaIntervencion: String = "",
  @ColumnInfo(name = "FechaFinInterrupcion") val FechaFinInterrupcion: String = "",
  @ColumnInfo(name = "Enviado") val Enviado: Int = 0,
)
