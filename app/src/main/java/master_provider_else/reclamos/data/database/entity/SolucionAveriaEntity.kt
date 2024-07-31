package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SolucionAveria")
data class SolucionAveriaEntity(
  @PrimaryKey
  @ColumnInfo(name = "CodigoAccionSolucionAveria") val CodigoAccionSolucionAveria: String,
  @ColumnInfo(name = "NombreAccionSolucionAveria") val NombreAccionSolucionAveria: String,
  @ColumnInfo(name = "Activo") val Activo: String,
  @ColumnInfo(name = "CodigoCausaAveria") val CodigoCausaAveria: String,
)
