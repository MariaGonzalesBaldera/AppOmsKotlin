package master_provider_else.reclamos.data.database.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Foto")
data class FotoEntity(
  @PrimaryKey
  @NonNull
  @ColumnInfo(name = "ItemID") val ItemID: String="",
  @ColumnInfo(name = "base64Source") val base64Source: String="",
  @ColumnInfo(name = "NombreArchivo") val NombreArchivo: String="",
  @ColumnInfo(name = "Extension") val Extension: String="",
  @ColumnInfo(name = "date") val date: String="",
  @ColumnInfo(name = "CodigoReclamo") val CodigoReclamo: String="",
  @ColumnInfo(name = "Enviado") val Enviado: String="",
)