package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Lineas")
data class LineasEntity(
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name="id")val id:Int,
  @ColumnInfo(name="CodigoReclamo")val CodigoReclamo:String,
  @ColumnInfo(name="CodigoNTCSE")val CodigoNTCSE:String,
  @ColumnInfo(name="CodigoUbicacionElectrica")val CodigoUbicacionElectrica:String,
  @ColumnInfo(name="Latitud")val Latitud:String,
  @ColumnInfo(name="Longitud")val Longitud:String,
)
