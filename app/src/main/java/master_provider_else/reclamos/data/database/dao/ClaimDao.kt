package master_provider_else.reclamos.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import master_provider_else.reclamos.data.database.entity.ReclamoEntity

@Dao
interface ClaimDao {
  @Query("SELECT * FROM Reclamo WHERE codigoReclamo  == :codigoReclamo")
  fun getReclamo(codigoReclamo: String?): ReclamoEntity?

  @Query("SELECT * FROM Reclamo WHERE codigoEstadoReclamo IN(:tipo) AND tipolistaReclamo like :tiporeclamo")
  fun getTipoReclamo(tipo: Array<String?>?, tiporeclamo: String?): List<ReclamoEntity?>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertMultipleClaims(reclamo: List<ReclamoEntity>)
}