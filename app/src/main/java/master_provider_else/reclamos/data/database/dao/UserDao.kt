package master_provider_else.reclamos.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import master_provider_else.reclamos.data.database.entity.APActividadEntity
import master_provider_else.reclamos.data.database.entity.CausaAveriaEntity
import master_provider_else.reclamos.data.database.entity.DaoCoordenadasEntity
import master_provider_else.reclamos.data.database.entity.EncuestaEntity
import master_provider_else.reclamos.data.database.entity.EncuestaEnviarEntity
import master_provider_else.reclamos.data.database.entity.FotoEntity
import master_provider_else.reclamos.data.database.entity.InformeOMSAPNodoEntity
import master_provider_else.reclamos.data.database.entity.LineasEntity
import master_provider_else.reclamos.data.database.entity.MaterialEntity
import master_provider_else.reclamos.data.database.entity.PreguntaEntity
import master_provider_else.reclamos.data.database.entity.ReclamoEntity
import master_provider_else.reclamos.data.database.entity.ReclamoInformeOMSAPEntity
import master_provider_else.reclamos.data.database.entity.ReclamoInformeOMSAPNodoActividadEntity
import master_provider_else.reclamos.data.database.entity.ReclamoInformeOMSEntity
import master_provider_else.reclamos.data.database.entity.SolucionAveriaEntity
import master_provider_else.reclamos.data.database.entity.SolucionInterrupcionEntity
import master_provider_else.reclamos.data.database.entity.TipoAreaIntervencionEntity
import master_provider_else.reclamos.data.database.entity.TipoDeficienciaEntity
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

  @Query("SELECT * FROM gnMaterial where TipoReclamo == :tipoReclamo")
  fun material_Get_General(tipoReclamo: String): List<gnMaterialEntity>

  @Query("SELECT * FROM Material  WHERE CodigoReclamo  == :codigoReclamo AND Enviado IN (:tipo)")
  fun material_Get(codigoReclamo: String, tipo: Array<String>): List<MaterialEntity>

  @Query("SELECT * FROM Material  WHERE CodigoReclamo  == :codigoReclamo AND CodigoMaterial == :codigoMaterial")
  fun material_Get_CodigoReclamo_CodigoMaterial(
    codigoReclamo: String,
    codigoMaterial: String
  ): MaterialEntity

  @Update(onConflict = OnConflictStrategy.IGNORE)
  fun material_Update(item: MaterialEntity)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun material_New(item: MaterialEntity): Long

  @Query("SELECT * FROM Foto WHERE CodigoReclamo  == :codigoReclamo AND Enviado IN(:enviado)")
  fun foto_Get_CodigoReclamo(codigoReclamo: String?, enviado: Array<String?>): List<FotoEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun fotos_New(item: FotoEntity)

  @Query("SELECT * FROM InformeOMSAPNodo WHERE CodigoReclamo == :codigoreclamo AND estado like '1'")
  fun informeOMSAPNodo_Get_Codigo_Reclamo(codigoreclamo: String): List<InformeOMSAPNodoEntity>

  @Query("SELECT * FROM TipoDeficiencia")
  fun tipoDeficiencia_All(): List<TipoDeficienciaEntity>

  @Query("SELECT * FROM ReclamoInformeOMSAP WHERE CodigoReclamo  == :codigoReclamo AND CodigoCuadrilla ==:codigoCuadrilla")
  fun reclamoInformeOMSAP_Get_codigoReclamo(
    codigoReclamo: String,
    codigoCuadrilla: String
  ): ReclamoInformeOMSAPEntity

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun reclamoInformeOMSAP_New(item: ReclamoInformeOMSAPEntity): Long

  @Query("SELECT * FROM InformeOMSAPNodo WHERE CodigoReclamo == :codigoreclamo AND Nodo == :nodo AND estado like '1'")
  fun informeOMSAPNodo_Get_Nodo(codigoreclamo: String, nodo: String): InformeOMSAPNodoEntity

  @Query("SELECT * FROM APActividad ORDER BY CAST(CodigoActividad as Int)  asc")
  fun apactividad_All(): List<APActividadEntity>

  @Query("SELECT *  FROM ReclamoInformeOMSAPNodoActividad  WHERE CodigoReclamo == :codigoReclamo AND CodigoActividad == :codigoActividad AND CodigoUbicacionElectrica ==:codigoUbicacionElectrica")
  fun reclamoInformeOMSAPNodoActividad_GET(
    codigoReclamo: String,
    codigoActividad: String,
    codigoUbicacionElectrica: String
  ): ReclamoInformeOMSAPNodoActividadEntity

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun reclamoInformeOMSAPNodoActividad_New(item: ReclamoInformeOMSAPNodoActividadEntity): Long

  @Query("SELECT Material.* FROM Material INNER JOIN ReclamoInformeOMS ON ReclamoInformeOMS.CodigoReclamo = Material.CodigoReclamo AND ReclamoInformeOMS.Enviado = 1  WHERE Material.Enviado IN ('1')")
  fun material_All_Send(): List<MaterialEntity>

  @Query("SELECT Material.* FROM Material INNER JOIN ReclamoInformeOMS ON ReclamoInformeOMS.CodigoReclamo = Material.CodigoReclamo AND ReclamoInformeOMS.Enviado = 1  WHERE Material.Enviado IN ('-1')")
  fun material_All_Send_delete(): List<MaterialEntity>

  @Query("SELECT Material.* FROM Material INNER JOIN ReclamoInformeOMS ON ReclamoInformeOMS.CodigoReclamo = Material.CodigoReclamo  WHERE Material.Enviado IN ('1') and Material.CodigoReclamo = :codigoReclamo")
  fun material_All_Send(codigoReclamo: String): List<MaterialEntity>

  @Query("SELECT Material.* FROM Material INNER JOIN ReclamoInformeOMS ON ReclamoInformeOMS.CodigoReclamo = Material.CodigoReclamo  WHERE Material.Enviado IN ('-1') and Material.CodigoReclamo = :codigoReclamo")
  fun material_All_Send_delete(codigoReclamo: String): List<MaterialEntity>

  @Delete
  fun Material_Delete(item: MaterialEntity)

  @Query("SELECT Foto.* FROM Foto INNER JOIN ReclamoInformeOMS ON ReclamoInformeOMS.CodigoReclamo = Foto.CodigoReclamo  WHERE Foto.Enviado  == :enviado and Foto.CodigoReclamo = :codigoReclamo")
  fun foto_Get_enviados(enviado: String, codigoReclamo: String): List<FotoEntity>

  @Query("SELECT Foto.* FROM Foto INNER JOIN ReclamoInformeOMS ON ReclamoInformeOMS.CodigoReclamo = Foto.CodigoReclamo  WHERE Foto.Enviado  == :enviado and Foto.CodigoReclamo = :codigoReclamo")
  fun foto_Get_enviados(enviado: String): List<FotoEntity>

  @Delete
  fun fotos_Delete(item: FotoEntity)

  @Query("SELECT * FROM ReclamoInformeOMS WHERE Enviado  == :enviado ")
  fun reclamoInformeOMS_Get_enviados(enviado: Int): List<ReclamoInformeOMSEntity>

  @Query("SELECT * FROM EncuestaEnviar WHERE Enviado  == :enviado AND CodigoOrigenOMS == :codigoOrigenOMS ")
  fun encuestaEnviar_Get_Enviado(enviado: Int, codigoOrigenOMS: String): List<EncuestaEnviarEntity>

}





















