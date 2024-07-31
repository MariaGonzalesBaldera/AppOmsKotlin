package master_provider_else.reclamos.data.dto

import com.google.gson.annotations.SerializedName

data class ReclamoArray(
  @SerializedName("CodigoSuministro") var codigoSuministro: String,
  @SerializedName("CodigoSED") var codigoSED: String,
  @SerializedName("CodigoEstadoReclamo") var codigoEstadoReclamo: String,
  @SerializedName("DireccionElectrica") var direccionElectrica: String,
  @SerializedName("NombreSuministros") var nombreSuministros: String,
  @SerializedName("CodigoReclamo") var codigoReclamo: String,
  @SerializedName("NombreClaseReclamo") var nombreClaseReclamo: String,
  @SerializedName("CodigoRutaSuministro") var codigoRutaSuministro: String,
  @SerializedName("Celular") var celular: String,
  @SerializedName("Latitud") var latitud: String,
  @SerializedName("Longitud") var longitud: String,
  @SerializedName("DescripcionReclamo") var descripcionReclamo: String,
  @SerializedName("ReferenciaUbicacion") var referenciaUbicacion: String,
  @SerializedName("CodigoDireccionElectrica") var codigoDireccionElectrica: String,
  @SerializedName("FechaRegistro") var fechaRegistro: String,
  @SerializedName("SectorTipico") var sectorTipico: String,
  @SerializedName("PlazoDias") var plazoDias: String,
  @SerializedName("FechaLimiteAtencion") var fechaLimiteAtencion: String,
  @SerializedName("TipoReclamo") var tipoReclamo: String
)