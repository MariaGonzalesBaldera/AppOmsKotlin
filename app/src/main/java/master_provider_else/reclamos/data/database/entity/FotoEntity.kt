package master_provider_else.reclamos.data.database.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Foto")
data class FotoEntity(
  @PrimaryKey
  @ColumnInfo(name = "ItemID") var ItemID: String = "",
  @ColumnInfo(name = "base64Source") var base64Source: String = "",
  @ColumnInfo(name = "NombreArchivo") var NombreArchivo: String = "",
  @ColumnInfo(name = "Extension") var Extension: String = "",
  @ColumnInfo(name = "date") var date: String = "",
  @ColumnInfo(name = "CodigoReclamo") var CodigoReclamo: String = "",
  @ColumnInfo(name = "Enviado") var Enviado: String = "",
)