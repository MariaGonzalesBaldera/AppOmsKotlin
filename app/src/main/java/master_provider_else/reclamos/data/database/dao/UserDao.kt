package master_provider_else.reclamos.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import master_provider_else.reclamos.data.database.entity.ReclamoEntity
import master_provider_else.reclamos.data.database.entity.ReclamoInformeOMSEntity
import master_provider_else.reclamos.data.database.entity.UserEntity


@Dao
interface UserDao {
  @Query("SELECT * FROM Usuario WHERE usuario=:usuario and pass=:pass")
  suspend fun getLoginDataBase(usuario: String, pass: String): UserEntity

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertUserInDatabase(user: UserEntity)

  @Update
  suspend fun updateUser(item: UserEntity)

  @Delete
  suspend fun deleteUser(item: UserEntity)

  //@Query("SELECT * from Usuario WHERE id = :id")
  //fun getUserWithId(id: Int): Flow<UserEntity>

  // @Query("SELECT * from Usuario ORDER BY id ASC")
  // fun getAllUsers(): List<UserEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMultipleUsers(usuario: List<UserEntity>)

  //--------------Reclamo-----------

  @Query("SELECT * FROM Reclamo WHERE codigoReclamo  == :codigoReclamo")
  fun getReclamo(codigoReclamo: String?): ReclamoEntity?

  @Query("SELECT * FROM Reclamo WHERE codigoEstadoReclamo IN(:tipo) AND tipolistaReclamo like :tiporeclamo")
  fun getTipoReclamo(tipo: Array<String?>?, tiporeclamo: String?): List<ReclamoEntity?>
  //fun getTipoReclamo(tipo: Array<String?>?, tiporeclamo: String?): List<ReclamoEntity?>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertMultipleClaims(reclamo: List<ReclamoEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun reclamoInformeOMS_New(item: ReclamoInformeOMSEntity): Long

  @Update(onConflict = OnConflictStrategy.IGNORE)
  fun Reclamo_Update(reclamo: ReclamoEntity)
  //MATERIAL
  //@Insert(onConflict = OnConflictStrategy.REPLACE)
  //fun insertMultipleMateriales(list: List<MaterialEntity?>?)

  //@Insert(onConflict = OnConflictStrategy.REPLACE)
  //fun insertMultipleFotos(list: List<FotoEntity?>?)
}