package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "Material", primaryKeys = ["CodigoReclamo", "CodigoMaterial"])
data class MaterialEntity(
  @ColumnInfo(name = "CodigoReclamo") var CodigoReclamo: String = "",
  @ColumnInfo(name = "CodigoCuadrilla") var CodigoCuadrilla: String = "",
  @ColumnInfo(name = "CodigoMaterial") var CodigoMaterial: String = "",
  @ColumnInfo(name = "NombreMaterial") var NombreMaterial: String = "",
  @ColumnInfo(name = "Unidad") var Unidad: String = "",
  @ColumnInfo(name = "Cantidad") var Cantidad: String = "",
  @ColumnInfo(name = "CodigoMaterialSAP") var CodigoMaterialSAP: String = "",
  @ColumnInfo(name = "Tipo") var Tipo: String = "",
  @ColumnInfo(name = "Enviado") var Enviado: String = "",
)
