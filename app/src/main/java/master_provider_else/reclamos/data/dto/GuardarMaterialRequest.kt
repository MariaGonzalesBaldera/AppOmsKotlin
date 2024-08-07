package master_provider_else.reclamos.data.dto

data class GuardarMaterialRequest(
  val CodigoReclamo: String,
  val CodigoCuadrilla: String,
  val CodigoMaterial: String,
  val NombreMaterial: String,
  val CantidadUtilizada: String,
)