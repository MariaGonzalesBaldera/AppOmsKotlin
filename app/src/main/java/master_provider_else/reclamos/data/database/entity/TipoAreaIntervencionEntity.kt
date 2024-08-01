package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TipoAreaIntervencion")
data class TipoAreaIntervencionEntity(
  @PrimaryKey
  @ColumnInfo("CodigoTipoAreaIntervencion") val CodigoTipoAreaIntervencion: String,
  @ColumnInfo("NombreTipoAreaIntervencion") val NombreTipoAreaIntervencion: String,
)