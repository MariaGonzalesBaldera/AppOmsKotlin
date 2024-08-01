package master_provider_else.reclamos.data.database.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EncuestaEnviar")
data class EncuestaEnviarEntity(
  @PrimaryKey(autoGenerate = true)
  @NonNull
  @ColumnInfo(name = "id") val id: Int,
  @ColumnInfo(name = "CodigoEncuesta") val CodigoEncuesta: String,
  @ColumnInfo(name = "NombreEncuesta") val NombreEncuesta: String,
  @ColumnInfo(name = "CodigoPregunta") val CodigoPregunta: String,
  @ColumnInfo(name = "NombrePregunta") val NombrePregunta: String,
  @ColumnInfo(name = "CodigoTipoRespuesta") val CodigoTipoRespuesta: String,
  @ColumnInfo(name = "NombreTipoRespuesta") val NombreTipoRespuesta: String,
  @ColumnInfo(name = "CodigoAlternativa") val CodigoAlternativa: String,
  @ColumnInfo(name = "Valor") val Valor: String,

  @ColumnInfo(name = "CodigoOrigenOMS") val CodigoOrigenOMS: String,
  @ColumnInfo(name = "enviado") val enviado: Int = -1,
)
