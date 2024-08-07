package master_provider_else.reclamos.domain.UseCase

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import master_provider_else.reclamos.data.QuoteRepository
import master_provider_else.reclamos.data.database.entity.UserEntity
import master_provider_else.reclamos.data.dto.ApiResponse
import master_provider_else.reclamos.domain.model.User
import master_provider_else.reclamos.domain.model.toDomain
import master_provider_else.reclamos.utils.isConnected
import master_provider_else.reclamos.viewModel.SessionManager
import retrofit2.Response
import javax.inject.Inject


class GetLoginUseCase @Inject constructor(
  private val repository: QuoteRepository,
  private val sessionManager: SessionManager,
) {
  suspend operator fun invoke(usuario: String, pass: String, context: Context): User? {
    if (isConnected(context)) {
      val result: Response<ApiResponse> = repository.getLoginFromApi(usuario, pass)
      Log.e("invoke", result.body().toString() + usuario + " - " + pass)
      return if (result.isSuccessful) {
        if (result.body()?.respuesta?.error == 0) {
          val userEntity = UserEntity(
            usuario = usuario,
            pass = pass,
            cuadrilla = result.body()?.respuesta?.body?.codigoCuadrilla.toString(),
            nombreUsuario = result.body()?.respuesta?.body?.nombreUsuario.toString(),
            token = result.body()?.respuesta?.body?.token.toString(),
            tiempoNotificacion = result.body()?.respuesta?.body?.codigoCuadrilla.toString()
          )
          Log.e("UsuarioUseCase", result.body().toString())
          Log.e("token use case login", userEntity.token)
          sessionManager.saveToken(userEntity.token)
          sessionManager.saveCuadrilla(result.body()?.respuesta?.body?.codigoCuadrilla ?: "")
          sessionManager.saveUsuario(usuario)
          repository.insertUser(userEntity)
          userEntity.toDomain()

        } else {
          null
        }
      } else {
        null
      }
    } else {
      return repository.getLoginFromDataBase(usuario = usuario, pass = pass)
    }
  }
}