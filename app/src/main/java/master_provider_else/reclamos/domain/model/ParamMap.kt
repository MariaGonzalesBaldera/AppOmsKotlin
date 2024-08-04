package master_provider_else.reclamos.domain.model

data class ParamMap(
  val latitud: Double,
  val longitud: Double,
  val codigoReclamo: String,
  val codigoEstadoReclamo: String,
  val nombreClaseReclamo: String,
  val codigoSED: String,
  val codigoRutaSuministro: String,
  val celular: String,
  val codigoDireccionElectrica: String,
)