package master_provider_else.reclamos.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import master_provider_else.reclamos.data.database.dao.ClaimDao
import master_provider_else.reclamos.data.database.dao.UserDao
import master_provider_else.reclamos.data.database.entity.CausaAveriaEntity
import master_provider_else.reclamos.data.database.entity.EncuestaEntity
import master_provider_else.reclamos.data.database.entity.FotoEntity
import master_provider_else.reclamos.data.database.entity.MaterialEntity
import master_provider_else.reclamos.data.database.entity.PreguntaEntity
import master_provider_else.reclamos.data.database.entity.ReclamoEntity
import master_provider_else.reclamos.data.database.entity.ReclamoInformeOMSAPEntity
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
import master_provider_else.reclamos.data.dto.ApiResponse
import master_provider_else.reclamos.data.dto.ApiResponseArchivoMovil
import master_provider_else.reclamos.data.dto.ApiResponseEncuesta
import master_provider_else.reclamos.data.dto.ApiResponseEstado
import master_provider_else.reclamos.data.dto.ApiResponseFicha
import master_provider_else.reclamos.data.dto.ApiResponseFichaTecnica
import master_provider_else.reclamos.data.dto.ApiResponseMaterial
import master_provider_else.reclamos.data.dto.ApiResponseReclamo
import master_provider_else.reclamos.data.dto.EstadoRequest
import master_provider_else.reclamos.data.network.QuoteService
import master_provider_else.reclamos.domain.model.Claim
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
    Log.e("getLoginFromApi", response.message())
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
    Log.e("getClaimFromApi", response.message())
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
    Log.e("getFicha", response.message())
    return response
  }

  suspend fun getEncuesta(
    authorization: String,
  ): Response<ApiResponseEncuesta> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseEncuesta> = api.getEncuesta(
        authorization
      );
      Log.e("Log Encuesta", "Encuesta" + response.message().toString())
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
    Log.e("cambio estado", response.message())
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
      Log.e("Log Material", "Informe Material" + response.message().toString())
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
      Log.e("Log Archivo", "Informe Archivo" + response.message().toString())
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
      Log.e("Log Ficha Tec", "Ficha tecnica" + response.message().toString())
      response
    }
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

  suspend fun insertMultipleTipoInstalacionElectricaAfectada(list: List<TipoInstalacionElectricaAfectadaEntity>) {
    userDao.insertMultipleTipoInstalacionElectricaAfectada(list)
  }

  suspend fun insertMultipleTipoInstalacionAfectada(list: List<TipoInstalacionAfectadaEntity>) {
    userDao.insertMultipleTipoInstalacionAfectada(list)
  }

  suspend fun insertMultipleTipoEquipoProteccionManiobra(list: List<TipoEquipoProteccionManiobraEntity>) {
    userDao.insertMultipleTipoEquipoProteccionManiobra(list)
  }

  suspend fun insertMultipleTipoManiobraCapacidad(list: List<TipoManiobraCapacidadEntity>) {
    userDao.insertMultipleTipoManiobraCapacidad(list)
  }

  suspend fun insertMultipleCausaAveria(list: List<CausaAveriaEntity>) {
    userDao.insertMultipleCausaAveria(list)
  }

  suspend fun insertMultipleSolucionAveria(list: List<SolucionAveriaEntity>) {
    userDao.insertMultipleSolucionAveria(list)
  }

  suspend fun insertMultipleSolucionInterrupcion(list: List<SolucionInterrupcionEntity>) {
    userDao.insertMultipleSolucionInterrupcion(list)
  }

  suspend fun insertMultipleTipoAreaIntervencion(list: List<TipoAreaIntervencionEntity>) {
    userDao.insertMultipleTipoAreaIntervencion(list)
  }

  suspend fun insertMultipleMaterialesGeneral(list: List<gnMaterialEntity>) {
    userDao.insertMultipleMaterialesGeneral(list)
  }


}