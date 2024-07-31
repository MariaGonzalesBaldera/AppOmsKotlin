package master_provider_else.reclamos.data.database.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "gnMaterial", primaryKeys = ["CodigoMaterial"])
data class gnMaterialEntity(
  @NonNull
  @ColumnInfo(name = "CodigoMaterial") val CodigoMaterial: String,
  @ColumnInfo(name = "NombreMaterial") val NombreMaterial: String,
  @ColumnInfo(name = "CodigoMaterialSAP") val CodigoMaterialSAP: String,
  @ColumnInfo(name = "TipoReclamo") val TipoReclamo: String,
)
