package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "APActividad")
data class APActividadEntity(
  @PrimaryKey(autoGenerate = false)
  @ColumnInfo(name = "codigoActividad") val codigoActividad: String,
  @ColumnInfo(name = "nombreActividad") val nombreActividad: String,
  @ColumnInfo(name = "realizada") val realizada: String
)
