package master_provider_else.reclamos.data.dto

import com.google.gson.annotations.SerializedName

data class ReclamoArray(
  @SerializedName("CodigoSuministro") val codigoSuministro: String,
  @SerializedName("CodigoSED") val codigoSED: String,
  @SerializedName("CodigoEstadoReclamo") val codigoEstadoReclamo: String,
  @SerializedName("DireccionElectrica") val direccionElectrica: String,
  @SerializedName("NombreSuministros") val nombreSuministros: String,
  @SerializedName("CodigoReclamo") val codigoReclamo: String,
  @SerializedName("NombreClaseReclamo") val nombreClaseReclamo: String,
  @SerializedName("CodigoRutaSuministro") val codigoRutaSuministro: String,
  @SerializedName("Celular") val celular: String,
  @SerializedName("Latitud") val latitud: String,
  @SerializedName("Longitud") val longitud: String,
  @SerializedName("DescripcionReclamo") val descripcionReclamo: String,
  @SerializedName("ReferenciaUbicacion") val referenciaUbicacion: String,
  @SerializedName("CodigoDireccionElectrica") val codigoDireccionElectrica: String,
  @SerializedName("FechaRegistro") val fechaRegistro: String,
  @SerializedName("SectorTipico") val sectorTipico: String,
  @SerializedName("PlazoDias") val plazoDias: String,
  @SerializedName("FechaLimiteAtencion") val fechaLimiteAtencion: String,
  @SerializedName("TipoReclamo") val tipoReclamo: String
)
