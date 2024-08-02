package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import master_provider_else.reclamos.data.dto.ReclamoArray

@Entity(tableName = "Reclamo")
data class ReclamoEntity(
  @ColumnInfo("CodigoEstadoReclamo") var CodigoEstadoReclamo: String = "",
  @ColumnInfo("NombreClaseReclamo") var NombreClaseReclamo: String = "",
  @PrimaryKey
  @ColumnInfo("CodigoReclamo") var CodigoReclamo: String = "",
  @ColumnInfo("NombreSuministro") var NombreSuministro: String = "",
  @ColumnInfo("DireccionElectrica") var DireccionElectrica: String = "",
  @ColumnInfo("CodigoSuministro") var CodigoSuministro: String = "",
  @ColumnInfo("DescripcionReclamo") var DescripcionReclamo: String = "",
  @ColumnInfo("ReferenciaUbicacion") var ReferenciaUbicacion: String = "",
  @ColumnInfo("CodigoRutaSuministro") var CodigoRutaSuministro: String = "",
  @ColumnInfo("CodigoSED") var CodigoSED: String = "",
  @ColumnInfo("DatoMCo") var DatoMCo: String = "",
  @ColumnInfo("Celular") var Celular: String = "",
  @ColumnInfo("CodigoDireccionElectrica") var CodigoDireccionElectrica: String = "",
  @ColumnInfo("FechaRegistro") var FechaRegistro: String = "",
  @ColumnInfo("Plazo") var Plazo: String = "",
  @ColumnInfo("SectorTipico") var SectorTipico: String = "",
  @ColumnInfo("FechaLimiteAtencion") var FechaLimiteAtencion: String = "",
  @ColumnInfo("latitud") var latitud: String = "",
  @ColumnInfo("longitud") var longitud: String = "",
  @ColumnInfo("FechaProgramacion") var FechaProgramacion: Long = 0,
  @ColumnInfo("FechaEnCamino") var FechaEnCamino: Long = 0,
  @ColumnInfo("FechaEjecucionInicio") var FechaEjecucionInicio: Long = 0,
  @ColumnInfo("FechaEjecucionFin") var FechaEjecucionFin: Long = 0,
  @ColumnInfo("tipoReclamo") var tipoReclamo: String = "",
  @ColumnInfo("enviado") var enviado: Int = 0,
  @ColumnInfo("tipolistaReclamo") var tipolistaReclamo: String = "",
)

fun ReclamoArray.toEntity() = ReclamoEntity(  //26
  CodigoEstadoReclamo = this.codigoEstadoReclamo,
  NombreClaseReclamo = this.nombreClaseReclamo,
  CodigoReclamo = this.codigoReclamo,
  NombreSuministro = this.nombreSuministros,
  DireccionElectrica = this.direccionElectrica,
  CodigoSuministro = this.codigoSuministro,
  DescripcionReclamo = this.descripcionReclamo,
  ReferenciaUbicacion = this.referenciaUbicacion,
  CodigoRutaSuministro = this.codigoRutaSuministro,
  CodigoSED = this.codigoSED,
  DatoMCo = "",
  Celular = this.celular,
  CodigoDireccionElectrica = this.codigoDireccionElectrica,
  FechaRegistro = this.fechaRegistro,
  Plazo = this.plazoDias,
  SectorTipico = this.sectorTipico,
  FechaLimiteAtencion = this.fechaLimiteAtencion,
  latitud = this.latitud,
  longitud = this.longitud,
  FechaProgramacion = 0,
  FechaEnCamino = 0,
  FechaEjecucionInicio = 0,
  FechaEjecucionFin = 0,
  tipoReclamo = this.tipoReclamo,
  enviado = 0,
  tipolistaReclamo = ""
)
