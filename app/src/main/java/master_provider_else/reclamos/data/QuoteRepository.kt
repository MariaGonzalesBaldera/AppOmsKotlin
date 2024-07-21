package master_provider_else.reclamos.data

import android.util.Log
import master_provider_else.reclamos.data.database.dao.ClaimDao
import master_provider_else.reclamos.data.database.dao.UserDao
import master_provider_else.reclamos.data.database.entity.ReclamoEntity
import master_provider_else.reclamos.data.database.entity.UserEntity
import master_provider_else.reclamos.data.dto.ApiResponse
import master_provider_else.reclamos.data.dto.ApiResponseReclamo
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

  suspend fun getReclamoInDataBase(codigoReclamo: String): Claim? {
    val response: ReclamoEntity? = userDao.getReclamo(codigoReclamo)
    return if (response != null) {
      response.toDomain()
    } else {
      null
    }
  }

  suspend fun getTipoReclamoInDataBase(tipo: Array<String?>?, tiporeclamo: String?): List<Claim?> {
    val response: List<ReclamoEntity?> = userDao.getTipoReclamo(tipo, tiporeclamo)
    return response.map { it?.toDomain() }
  }

  suspend fun insertMultipleClaimsInDataBase(claim: List<ReclamoEntity>) {
    userDao.insertMultipleClaims(claim)
  }
}