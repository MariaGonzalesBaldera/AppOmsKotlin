package master_provider_else.reclamos.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import master_provider_else.reclamos.data.database.dao.ClaimDao
import master_provider_else.reclamos.data.database.dao.UserDao
import master_provider_else.reclamos.data.database.entity.FotoEntity
import master_provider_else.reclamos.data.database.entity.MaterialEntity
import master_provider_else.reclamos.data.database.entity.ReclamoEntity
import master_provider_else.reclamos.data.database.entity.ReclamoInformeOMSAPEntity
import master_provider_else.reclamos.data.database.entity.ReclamoInformeOMSEntity
import master_provider_else.reclamos.data.database.entity.UserEntity
import master_provider_else.reclamos.data.dto.ApiResponse
import master_provider_else.reclamos.data.dto.ApiResponseEncuesta
import master_provider_else.reclamos.data.dto.ApiResponseEstado
import master_provider_else.reclamos.data.dto.ApiResponseFicha
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
  //private val claimDao: ClaimDao
) {
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
    contentType: String,
    authorization: String,
    strCodigoCuadrilla: String,
    strCodigoEstadoReclamo: String,
    strAP: String
  ): Response<ApiResponseReclamo> {
    val response: Response<ApiResponseReclamo> = api.getReclamo(
      contentType,
      authorization,
      strCodigoCuadrilla,
      strCodigoEstadoReclamo,
      strAP
    )
    Log.e("getClaimFromApi", response.message())
    return response
  }

  suspend fun getFichaApi(
    contentType: String,
    authorization: String,
    strCodigoReclamo: String,
    strCodigoCuadrilla: String,
  ): Response<ApiResponseFicha> {
    val response: Response<ApiResponseFicha> = api.getFichaTecnica(
      contentType,
      authorization,
      strCodigoReclamo,
      strCodigoCuadrilla,
    )
    Log.e("getFicha", response.message())
    return response
  }

  suspend fun getEncuesta(
    contentType: String,
    authorization: String,
  ): Response<ApiResponseEncuesta> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseEncuesta> = api.getEncuesta(
        contentType,
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
    contentType: String,
    authorization: String,
    request: EstadoRequest
  ): Response<ApiResponseEstado> {
    val response: Response<ApiResponseEstado> = api.getCambioEstado(
      contentType,
      authorization,
      request
    )
    Log.e("cambio estado", response.message())
    return response
  }

  suspend fun Reclamo_Update(reclamo: ReclamoEntity) {
    userDao.Reclamo_Update(reclamo)
  }
  //suspend fun insertMultipleMateriales(item :List<MaterialEntity?>?){
  //  userDao.insertMultipleMateriales(item)
  //}

  //suspend fun insertMultipleFotos(item :List<FotoEntity?>?){
  //  userDao.insertMultipleFotos(item)
  //}

}