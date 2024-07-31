package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "DaoCoordenadas")

data class DaoCoordenadasEntity(
  @PrimaryKey(autoGenerate = true)
  @Nonnull
  @ColumnInfo(name = "id") val id: Int,
  @ColumnInfo(name = "CodigoReclamo") val CodigoReclamo: String,
  @ColumnInfo(name = "CodigoNTCSE") val CodigoNTCSE: String,
  @ColumnInfo(name = "CodigoUbicacionElectrica") val CodigoUbicacionElectrica: Int,
  @ColumnInfo(name = "Latitud") val Latitud: String,
  @ColumnInfo(name = "Longitud") val Longitud: String,
  @ColumnInfo(name = "codigoTipoUbicacion") val codigoTipoUbicacion: String,
  @ColumnInfo(name = "tipoLista") val tipoLista: String
)
