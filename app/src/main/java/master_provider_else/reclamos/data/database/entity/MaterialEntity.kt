package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "Material", primaryKeys = ["CodigoReclamo", "CodigoMaterial"])
data class MaterialEntity(
  @ColumnInfo(name = "CodigoReclamo") val CodigoReclamo: String,
  @ColumnInfo(name = "CodigoCuadrilla") val CodigoCuadrilla: String,
  @ColumnInfo(name = "CodigoMaterial") val CodigoMaterial: String,
  @ColumnInfo(name = "NombreMaterial") val NombreMaterial: String,
  @ColumnInfo(name = "Unidad") val Unidad: String,
  @ColumnInfo(name = "Cantidad") val Cantidad: String,
  @ColumnInfo(name = "CodigoMaterialSAP") val CodigoMaterialSAP: String,
  @ColumnInfo(name = "Tipo") val Tipo: String,
  @ColumnInfo(name = "Enviado") val Enviado: String,
)
