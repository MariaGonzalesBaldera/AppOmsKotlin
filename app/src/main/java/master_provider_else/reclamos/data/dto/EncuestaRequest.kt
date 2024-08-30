package master_provider_else.reclamos.data.dto

data class EncuestaRequest(
  val CodigoOrigenOMS: String,
  val CodigoEncuesta: String,
  val CodigoPregunta: String,
  val CodigoTipoRespuesta: String,
  val CodigoAlternativa: String,
  val ValorAlternativa: String,
  val LoginUsuario: String,
)
