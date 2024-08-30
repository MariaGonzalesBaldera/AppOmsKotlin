package master_provider_else.reclamos.data.dto

data class EliminarFotoRequest(
  val CodigoReclamo: String,
  val ListaItemID: ArrayList<String>,
)
