package master_provider_else.reclamos.data

import android.util.Log
import androidx.room.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import master_provider_else.reclamos.data.database.dao.UserDao
import master_provider_else.reclamos.data.database.entity.APActividadEntity
import master_provider_else.reclamos.data.database.entity.CausaAveriaEntity
import master_provider_else.reclamos.data.database.entity.DaoCoordenadasEntity
import master_provider_else.reclamos.data.database.entity.EncuestaEntity
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
import master_provider_else.reclamos.data.dto.ApiResponse
import master_provider_else.reclamos.data.dto.ApiResponseArchivoMovil
import master_provider_else.reclamos.data.dto.ApiResponseEncuesta
import master_provider_else.reclamos.data.dto.ApiResponseEstado
import master_provider_else.reclamos.data.dto.ApiResponseFicha
import master_provider_else.reclamos.data.dto.ApiResponseFichaMapa
import master_provider_else.reclamos.data.dto.ApiResponseFichaTecnica
import master_provider_else.reclamos.data.dto.ApiResponseFinTrabajoCompleto
import master_provider_else.reclamos.data.dto.ApiResponseGeneral
import master_provider_else.reclamos.data.dto.ApiResponseGuardarMaterial
import master_provider_else.reclamos.data.dto.ApiResponseInicioTrabajo
import master_provider_else.reclamos.data.dto.ApiResponseMaterial
import master_provider_else.reclamos.data.dto.ApiResponseReclamo
import master_provider_else.reclamos.data.dto.EstadoRequest
import master_provider_else.reclamos.data.dto.FinTrabajoCompletoRequest
import master_provider_else.reclamos.data.dto.GuardarMaterialRequest
import master_provider_else.reclamos.data.dto.InicioTrabajoRequest
import master_provider_else.reclamos.data.dto.fotoRequest
import master_provider_else.reclamos.data.network.QuoteService
import master_provider_else.reclamos.domain.model.User
import master_provider_else.reclamos.domain.model.toDomain
import retrofit2.Response
import javax.inject.Inject

class QuoteRepository @Inject constructor(
  private val api: QuoteService,
  private val userDao: UserDao
) {
  //del api y de la db
  // *********** User ***********
  suspend fun getLoginFromApi(usuario: String, pass: String): Response<ApiResponse> {
    val response: Response<ApiResponse> = api.getLogin(usuario, pass)
    Log.e("getLoginFromApi", "repository " + response.message())
    return response
  }

  suspend fun insertUser(user: UserEntity) {
    userDao.insertUserInDatabase(user)
  }

  suspend fun getLoginFromDataBase(usuario: String, pass: String): User? {
    val response: UserEntity? = userDao.getLoginDataBase(usuario, pass)

    return if (response != null) {
      Log.e("Repository", "$response.usuario")
      response.toDomain()
    } else {
      Log.e("Repository", "Usuario no encontrado")
      null
    }
  }
  // *********** ReportClaim ***********

  suspend fun getClaimFromApi(
    authorization: String,
    strCodigoCuadrilla: String,
    strCodigoEstadoReclamo: String,
    strAP: String
  ): Response<ApiResponseReclamo> {
    val response: Response<ApiResponseReclamo> = api.getReclamo(
      authorization,
      strCodigoCuadrilla,
      strCodigoEstadoReclamo,
      strAP
    )
    Log.e("getClaimFromApi", "repository " + response.message())
    return response
  }

  suspend fun getFichaApi(
    authorization: String,
    strCodigoReclamo: String,
    strCodigoCuadrilla: String,
  ): Response<ApiResponseFicha> {
    val response: Response<ApiResponseFicha> = api.getFichaTecnica(
      authorization,
      strCodigoReclamo,
      strCodigoCuadrilla,
    )
    Log.e("getFicha", "repository " + response.message())
    return response
  }

  suspend fun getEncuesta(
    authorization: String,
  ): Response<ApiResponseEncuesta> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseEncuesta> = api.getEncuesta(
        authorization
      );
      Log.e("Log Encuesta", "Encuesta repository " + response.message().toString())
      response
    }
  }

  suspend fun getTipoReclamoInDataBase(
    tipo: Array<String?>?,
    tiporeclamo: String?
  ): List<ReclamoEntity?> {
    return userDao.getTipoReclamo(tipo, tiporeclamo)
  }


  suspend fun insertMultipleClaimsInDataBase(claim: List<ReclamoEntity>) {
    userDao.insertMultipleClaims(claim)
  }

  suspend fun reclamoInformeOMS_New(item: ReclamoInformeOMSEntity) {
    userDao.reclamoInformeOMS_New(item)
  }

  suspend fun getCambioEstado(
    authorization: String,
    request: EstadoRequest
  ): Response<ApiResponseEstado> {
    val response: Response<ApiResponseEstado> = api.getCambioEstado(
      authorization,
      request
    )
    Log.e("cambio estado", "repository " + response.message())
    return response
  }

  suspend fun getReclamoInformeMaterial(
    authorization: String,
    strCodigoReclamo: String,
    strCodigoCuadrilla: String,
  ): Response<ApiResponseMaterial> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseMaterial> = api.getReclamoInformeMaterial(
        authorization,
        strCodigoReclamo,
        strCodigoCuadrilla
      );
      Log.e("Log Material", "Informe Material repository " + response.message().toString())
      response
    }
  }

  suspend fun getReclamoComercialArchivoMovilLeer(
    authorization: String,
    strCodigoReclamo: String,
  ): Response<ApiResponseArchivoMovil> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseArchivoMovil> = api.getReclamoComercialArchivoMovilLeer(
        authorization,
        strCodigoReclamo
      );
      Log.e("Log Archivo", "Informe Archivo repository " + response.message().toString())
      response
    }
  }

  suspend fun getFichaTecnica(
    authorization: String
  ): Response<ApiResponseFichaTecnica> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseFichaTecnica> = api.getFichaTecnica(
        authorization,
      );
      Log.e("Log Ficha Tec", "Ficha tecnica repository " + response.message().toString())
      response
    }
  }

  suspend fun getMapa(
    authorization: String,
    strCodigoCuadrilla: String,
    strAMT: String
  ): Response<ApiResponseFichaMapa> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseFichaMapa> = api.getMapa(
        authorization,
        strCodigoCuadrilla,
        strAMT
      );
      Log.e("Log getMapa ", "repository " + response.message().toString())
      response
    }
  }


  suspend fun inicioTrabajoRepository(
    authorization: String,
    request: InicioTrabajoRequest
  ): Response<ApiResponseInicioTrabajo> {
    val response: Response<ApiResponseInicioTrabajo> = api.inicioTrabajo(
      authorization,
      request
    )
    Log.e("Inicio Trabajo", "repository " + response.message())
    return response
  }

  suspend fun finTrabajoCompletoRepository(
    authorization: String,
    request: FinTrabajoCompletoRequest
  ): Response<ApiResponseFinTrabajoCompleto> {
    val response: Response<ApiResponseFinTrabajoCompleto> = api.finTrabajoCompletoService(
      authorization,
      request
    )
    Log.e("Fin trabajo ", "Fin trabajo Repository" + response.message())
    return response
  }

  suspend fun eliminarInformeMaterialRepository(
    authorization: String,
    request: GuardarMaterialRequest
  ): Response<ApiResponseGuardarMaterial> {
    val response: Response<ApiResponseGuardarMaterial> = api.eliminarInformeMaterialService(
      authorization,
      request
    )
    Log.e("Eliminar InformeMaterial", "Repository " + response.message())
    return response
  }

  suspend fun guardarInformeMaterialRepository(
    authorization: String,
    request: GuardarMaterialRequest
  ): Response<ApiResponseGuardarMaterial> {
    val response: Response<ApiResponseGuardarMaterial> = api.guardarInformeMaterialService(
      authorization,
      request
    )
    Log.e("Guardar InformeMaterial", "Service " + response.message())
    return response
  }

  suspend fun guardarArchivoComercialRepository(
    authorization: String,
    request: fotoRequest
  ): Response<ApiResponseGeneral> {
    val response: Response<ApiResponseGeneral> = api.guardarArchivoComercialService(
      authorization,
      request
    )
    Log.e("Guardar InformeMaterial", "Service " + response.message())
    return response
  }

  suspend fun Reclamo_Update(reclamo: ReclamoEntity) {
    userDao.Reclamo_Update(reclamo)
  }

  suspend fun insertMultipleMateriales(item: List<MaterialEntity>) {
    userDao.insertMultipleMateriales(item)
  }

  suspend fun insertMultipleFotos(item: List<FotoEntity>) {
    userDao.insertMultipleFotos(item)
  }

  suspend fun delete_All_Encuesta() {
    userDao.delete_All_Encuesta()
  }

  suspend fun delete_All_Pregunta() {
    userDao.delete_All_Pregunta()
  }

  suspend fun insertMultipleEncuesta(item: List<EncuestaEntity>) {
    userDao.insertMultipleEncuesta(item)
  }

  suspend fun insertMultiplePregunta(item: List<PreguntaEntity>) {
    userDao.insertMultiplePregunta(item)
  }

  suspend fun insertMultipleTipoDenuncia(list: List<TipoDenunciaEntity>) {
    userDao.insertMultipleTipoDenuncia(list)
  }

  suspend fun tipoDenuncia_All(): List<TipoDenunciaEntity> {
    return userDao.tipoDenuncia_All()
  }

  suspend fun insertMultipleTipoInstalacionElectricaAfectada(list: List<TipoInstalacionElectricaAfectadaEntity>) {
    userDao.insertMultipleTipoInstalacionElectricaAfectada(list)
  }

  suspend fun tipoInstalacionElectricaAfectada_All(): List<TipoInstalacionElectricaAfectadaEntity> {
    return userDao.tipoInstalacionElectricaAfectada_All()
  }

  suspend fun insertMultipleTipoInstalacionAfectada(list: List<TipoInstalacionAfectadaEntity>) {
    userDao.insertMultipleTipoInstalacionAfectada(list)
  }

  suspend fun tipoInstalacionAfectada_All(): List<TipoInstalacionAfectadaEntity> {
    return userDao.tipoInstalacionAfectada_All()
  }

  suspend fun insertMultipleTipoEquipoProteccionManiobra(list: List<TipoEquipoProteccionManiobraEntity>) {
    userDao.insertMultipleTipoEquipoProteccionManiobra(list)
  }

  suspend fun tipoEquipoProteccionManiobra_All(): List<TipoEquipoProteccionManiobraEntity> {
    return userDao.tipoEquipoProteccionManiobra_All()
  }

  suspend fun insertMultipleTipoManiobraCapacidad(list: List<TipoManiobraCapacidadEntity>) {
    userDao.insertMultipleTipoManiobraCapacidad(list)
  }

  suspend fun tipoManiobraCapacidad_All(): List<TipoManiobraCapacidadEntity> {
    return userDao.tipoManiobraCapacidad_All()
  }

  suspend fun insertMultipleCausaAveria(list: List<CausaAveriaEntity>) {
    userDao.insertMultipleCausaAveria(list)
  }

  suspend fun causaAveria_All(): List<CausaAveriaEntity> {
    return userDao.causaAveria_All()
  }

  suspend fun insertMultipleSolucionAveria(list: List<SolucionAveriaEntity>) {
    userDao.insertMultipleSolucionAveria(list)
  }

  suspend fun solucionAveria_All(): List<SolucionAveriaEntity> {
    return userDao.solucionAveria_All()
  }


  suspend fun solucionInterrupcion_All(): List<SolucionInterrupcionEntity> {
    return userDao.solucionInterrupcion_All()
  }

  suspend fun insertMultipleSolucionInterrupcion(list: List<SolucionInterrupcionEntity>) {
    userDao.insertMultipleSolucionInterrupcion(list)
  }

  suspend fun tipoAreaIntervencion_All(): List<TipoAreaIntervencionEntity> {
    return userDao.tipoAreaIntervencion_All()
  }

  suspend fun insertMultipleTipoAreaIntervencion(list: List<TipoAreaIntervencionEntity>) {
    userDao.insertMultipleTipoAreaIntervencion(list)
  }

  suspend fun insertMultipleMaterialesGeneral(list: List<gnMaterialEntity>) {
    userDao.insertMultipleMaterialesGeneral(list)
  }

  suspend fun insertMultipleDaoCoordenadas(list: List<DaoCoordenadasEntity>) {
    userDao.insertMultipleDaoCoordenadas(list)
  }

  suspend fun insertMultipleLineas(list: List<LineasEntity>) {
    userDao.insertMultipleLineas(list)
  }

  suspend fun reclamo_Get(codigoReclamo: String): ReclamoEntity {
    return userDao.reclamo_Get(codigoReclamo)
  }

  suspend fun reclamo_Update(entity: ReclamoEntity) {
    userDao.reclamo_Update(entity)
  }

  suspend fun material_Get_General(codigoReclamo: String): List<gnMaterialEntity> {
    return userDao.material_Get_General(codigoReclamo)
  }

  suspend fun material_Get(codigoReclamo: String, tipo: Array<String>): List<MaterialEntity> {
    return userDao.material_Get(codigoReclamo, tipo)
  }

  suspend fun material_Get_CodigoReclamo_CodigoMaterial(
    codigoReclamo: String,
    codigoMaterial: String
  ): MaterialEntity {
    return userDao.material_Get_CodigoReclamo_CodigoMaterial(
      codigoReclamo, codigoMaterial
    )
  }

  suspend fun material_Update(item: MaterialEntity) {
    return userDao.material_Update(item)
  }

  suspend fun material_New(item: MaterialEntity): Long {
    return userDao.material_New(item)
  }

  suspend fun foto_Get_CodigoReclamo(
    codigoReclamo: String,
    enviado: Array<String?>
  ): List<FotoEntity> {
    return userDao.foto_Get_CodigoReclamo(codigoReclamo, enviado)
  }

  suspend fun fotos_New(item: FotoEntity) {
    return userDao.fotos_New(item)
  }

  suspend fun informeOMSAPNodo_Get_Codigo_Reclamo(codigoreclamo: String): List<InformeOMSAPNodoEntity> {
    return userDao.informeOMSAPNodo_Get_Codigo_Reclamo(codigoreclamo)
  }

  suspend fun tipoDeficiencia_All(): List<TipoDeficienciaEntity> {
    return userDao.tipoDeficiencia_All()
  }

  suspend fun reclamoInformeOMSAP_Get_codigoReclamo(
    codigoReclamo: String,
    codigoCuadrilla: String
  ): ReclamoInformeOMSAPEntity {
    return userDao.reclamoInformeOMSAP_Get_codigoReclamo(
      codigoReclamo,
      codigoCuadrilla
    )
  }

  suspend fun reclamoInformeOMSAP_New(
    item: ReclamoInformeOMSAPEntity
  ): Long {
    return userDao.reclamoInformeOMSAP_New(item)
  }

  suspend fun informeOMSAPNodo_Get_Nodo(
    codigoreclamo: String, nodo: String
  ): InformeOMSAPNodoEntity {
    return userDao.informeOMSAPNodo_Get_Nodo(codigoreclamo, nodo)
  }

  suspend fun apactividad_All(): List<APActividadEntity> {
    return userDao.apactividad_All()
  }

  suspend fun reclamoInformeOMSAPNodoActividad_GET(
    codigoReclamo: String,
    codigoActividad: String,
    codigoUbicacionElectrica: String
  ): ReclamoInformeOMSAPNodoActividadEntity {
    return userDao.reclamoInformeOMSAPNodoActividad_GET(
      codigoReclamo,
      codigoActividad,
      codigoUbicacionElectrica
    )
  }

  suspend fun reclamoInformeOMSAPNodoActividad_New(
    item: ReclamoInformeOMSAPNodoActividadEntity
  ): Long {
    return userDao.reclamoInformeOMSAPNodoActividad_New(item)
  }

  suspend fun material_All_Send(): List<MaterialEntity> {
    return userDao.material_All_Send()
  }

  suspend fun material_All_Send_delete(): List<MaterialEntity> {
    return userDao.material_All_Send_delete()
  }

  suspend fun material_All_Send(
    codigoReclamo: String
  ): List<MaterialEntity> {
    return userDao.material_All_Send(codigoReclamo)
  }

  suspend fun material_All_Send_delete(
    codigoReclamo: String
  ): List<MaterialEntity> {
    return userDao.material_All_Send_delete(codigoReclamo)
  }

  suspend fun material_delete(
    item: MaterialEntity
  ) {
    return userDao.Material_Delete(item)
  }

  suspend fun foto_Get_enviados(
    enviado: String, codigoReclamo: String
  ): List<FotoEntity> {
    return userDao.foto_Get_enviados(enviado, codigoReclamo)
  }

  suspend fun fotos_delete(
    item: FotoEntity
  ) {
    return userDao.fotos_Delete(item)
  }
}
















