package master_provider_else.reclamos.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import master_provider_else.reclamos.data.database.entity.CausaAveriaEntity
import master_provider_else.reclamos.data.database.entity.DaoCoordenadasEntity
import master_provider_else.reclamos.data.database.entity.EncuestaEntity
import master_provider_else.reclamos.data.database.entity.FotoEntity
import master_provider_else.reclamos.data.database.entity.LineasEntity
import master_provider_else.reclamos.data.database.entity.MaterialEntity
import master_provider_else.reclamos.data.database.entity.PreguntaEntity
import master_provider_else.reclamos.data.database.entity.ReclamoEntity
import master_provider_else.reclamos.data.database.entity.ReclamoInformeOMSEntity
import master_provider_else.reclamos.data.database.entity.SolucionAveriaEntity
import master_provider_else.reclamos.data.database.entity.SolucionInterrupcionEntity
import master_provider_else.reclamos.data.database.entity.TipoAreaIntervencionEntity
import master_provider_else.reclamos.data.database.entity.TipoDenunciaEntity
import master_provider_else.reclamos.data.database.entity.TipoEquipoProteccionManiobraEntity
import master_provider_else.reclamos.data.database.entity.TipoInstalacionAfectadaEntity
import master_provider_else.reclamos.data.database.entity.TipoInstalacionElectricaAfectadaEntity
import master_provider_else.reclamos.data.database.entity.TipoManiobraCapacidadEntity
import master_provider_else.reclamos.data.database.entity.UserEntity
import master_provider_else.reclamos.data.database.entity.gnMaterialEntity


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

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertMultipleClaims(reclamo: List<ReclamoEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun reclamoInformeOMS_New(item: ReclamoInformeOMSEntity): Long

  @Update(onConflict = OnConflictStrategy.IGNORE)
  fun Reclamo_Update(reclamo: ReclamoEntity)

  //MATERIAL
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMultipleMateriales(list: List<MaterialEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMultipleFotos(list: List<FotoEntity>)

  @Query("DELETE FROM Encuesta")
  fun delete_All_Encuesta()

  @Query("DELETE FROM Pregunta")
  fun delete_All_Pregunta()

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMultipleEncuesta(list: List<EncuestaEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMultiplePregunta(list: List<PreguntaEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMultipleTipoDenuncia(list: List<TipoDenunciaEntity>)

  @Query("SELECT * FROM TipoDenuncia")
  fun tipoDenuncia_All(): List<TipoDenunciaEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMultipleTipoInstalacionElectricaAfectada(list: List<TipoInstalacionElectricaAfectadaEntity>)

  @Query("SELECT * FROM TipoInstalacionElectricaAfectada")
  fun tipoInstalacionElectricaAfectada_All(): List<TipoInstalacionElectricaAfectadaEntity>


  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMultipleTipoInstalacionAfectada(list: List<TipoInstalacionAfectadaEntity>)

  @Query("SELECT * FROM TipoInstalacionAfectada")
  fun tipoInstalacionAfectada_All(): List<TipoInstalacionAfectadaEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMultipleTipoEquipoProteccionManiobra(list: List<TipoEquipoProteccionManiobraEntity>)

  @Query("SELECT * FROM TipoEquipoProteccionManiobra")
  fun tipoEquipoProteccionManiobra_All(): List<TipoEquipoProteccionManiobraEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMultipleTipoManiobraCapacidad(list: List<TipoManiobraCapacidadEntity>)

  @Query("SELECT * FROM TipoManiobraCapacidad")
  fun tipoManiobraCapacidad_All(): List<TipoManiobraCapacidadEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMultipleCausaAveria(list: List<CausaAveriaEntity>)

  @Query("SELECT * FROM CausaAveria")
  fun causaAveria_All(): List<CausaAveriaEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMultipleSolucionAveria(list: List<SolucionAveriaEntity>)

  @Query("SELECT * FROM SolucionAveria")
  fun solucionAveria_All(): List<SolucionAveriaEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMultipleSolucionInterrupcion(list: List<SolucionInterrupcionEntity>)

  @Query("SELECT * FROM SolucionInterrupcion")
  fun solucionInterrupcion_All(): List<SolucionInterrupcionEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMultipleTipoAreaIntervencion(list: List<TipoAreaIntervencionEntity>)

  @Query("SELECT * FROM TipoAreaIntervencion")
  fun tipoAreaIntervencion_All(): List<TipoAreaIntervencionEntity>



  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMultipleMaterialesGeneral(list: List<gnMaterialEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMultipleDaoCoordenadas(list: List<DaoCoordenadasEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMultipleLineas(list: List<LineasEntity>)

  @Query("SELECT * FROM Reclamo  WHERE CodigoReclamo  == :codigoReclamo")
  fun reclamo_Get(codigoReclamo: String): ReclamoEntity

  @Update(onConflict = OnConflictStrategy.IGNORE)
  fun reclamo_Update(entity: ReclamoEntity)

}