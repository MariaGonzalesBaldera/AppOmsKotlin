package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Reclamo")
data class ReclamoEntity(
  @ColumnInfo("codigoEstadoReclamo") val codigoEstadoReclamo: String = "",
  @ColumnInfo("nombreClaseReclamo") val nombreClaseReclamo: String = "",
  @PrimaryKey(autoGenerate = false)
  @ColumnInfo("codigoReclamo") val codigoReclamo: String = "",
  @ColumnInfo("nombreSuministro") val nombreSuministro: String = "",
  @ColumnInfo("direccionElectrica") val direccionElectrica: String = "",
  @ColumnInfo("codigoSuministro") val codigoSuministro: String = "",
  @ColumnInfo("descripcionReclamo") val descripcionReclamo: String = "",
  @ColumnInfo("referenciaUbicacion") val referenciaUbicacion: String = "",
  @ColumnInfo("codigoRutaSuministro") val codigoRutaSuministro: String = "",
  @ColumnInfo("codigoSED") val codigoSED: String = "",
  @ColumnInfo("datoMCo") val datoMCo: String = "",
  @ColumnInfo("celular") val celular: String = "",
  @ColumnInfo("codigoDireccionElectrica") val codigoDireccionElectrica: String = "",
  @ColumnInfo("fechaRegistro") val fechaRegistro: String = "",
  @ColumnInfo("plazo") val plazo: String = "",
  @ColumnInfo("sectorTipico") val sectorTipico: String = "",
  @ColumnInfo("fechaLimiteAtencion") val fechaLimiteAtencion: String = "",
  @ColumnInfo("latitud") val latitud: String = "",
  @ColumnInfo("longitud") val longitud: String = "",
  @ColumnInfo("fechaProgramacion") val fechaProgramacion: Long = 0,
  @ColumnInfo("fechaEnCamino") val fechaEnCamino: Long = 0,
  @ColumnInfo("fechaEjecucionInicio") val fechaEjecucionInicio: Long = 0,
  @ColumnInfo("fechaEjecucionFin") val fechaEjecucionFin: Long = 0,
  @ColumnInfo("tipoReclamo") val tipoReclamo: String = "",
  @ColumnInfo("enviado") val enviado: Int = 0,
  @ColumnInfo("tipolistaReclamo") val tipolistaReclamo: String = "",
)
