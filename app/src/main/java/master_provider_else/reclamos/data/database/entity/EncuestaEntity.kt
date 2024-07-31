package master_provider_else.reclamos.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "Encuesta")
data class EncuestaEntity(
  @PrimaryKey(autoGenerate = true)
  @Nonnull
  @ColumnInfo(name = "id") val id: Int,
  @ColumnInfo(name = "Item") val Item: String,
  @ColumnInfo(name = "CodigoEncuesta") val CodigoEncuesta: String,

  )
