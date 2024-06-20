package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ReclamoInformeOMS")
data class ReclamoInformeEntity(
  @PrimaryKey(autoGenerate = false)
  @ColumnInfo(name = "codigoReclamo") val codigoReclamo: String = "",
  @ColumnInfo(name = "codigoCuadrilla") val codigoCuadrilla: String = "",
  @ColumnInfo(name = "codigoTipoDenuncia") val codigoTipoDenuncia: String = "",
  @ColumnInfo(name = "codigoTipoInstalacionElectricaAfectada") val codigoTipoInstalacionElectricaAfectada: String = "",
  @ColumnInfo(name = "codigoTipoUbicacionAfectada") val codigoTipoUbicacionAfectada: String = "",
  @ColumnInfo(name = "codigoTipoEquipoProteccionManiobra") val codigoTipoEquipoProteccionManiobra: String = "",
  @ColumnInfo(name = "capacidadCarga") val capacidadCarga: String = "",
  @ColumnInfo(name = "codigoEquipoProteccionManiobraNodoCircuito") val codigoEquipoProteccionManiobraNodoCircuito: String = "",
  @ColumnInfo(name = "codigoEquipoProteccionManiobraSED") val codigoEquipoProteccionManiobraSED: String = "",
  @ColumnInfo(name = "codigoCausaAveriaElectrica") val codigoCausaAveriaElectrica: String = "",
  @ColumnInfo(name = "observacionCausaAveriaElectrica") val observacionCausaAveriaElectrica: String = "",
  @ColumnInfo(name = "codigoAccionSolucionAveria") val codigoAccionSolucionAveria: String = "",
  @ColumnInfo(name = "observacionAccionSolucionAveria") val observacionAccionSolucionAveria: String = "",
  @ColumnInfo(name = "codigoTipoSolucionIntervencion") val codigoTipoSolucionIntervencion: String = "",
  @ColumnInfo(name = "codigoTipoAreaIntervencion") val codigoTipoAreaIntervencion: String = "",
  @ColumnInfo(name = "fechaFinInterrupcion") val fechaFinInterrupcion: String = "",
  @ColumnInfo(name = "enviado") val enviado: String = "",
)
