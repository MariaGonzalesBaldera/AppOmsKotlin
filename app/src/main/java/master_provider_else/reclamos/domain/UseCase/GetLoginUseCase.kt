package master_provider_else.reclamos.domain.UseCase

import android.util.Log
import master_provider_else.reclamos.data.QuoteRepository
import master_provider_else.reclamos.data.database.entity.UserEntity
import master_provider_else.reclamos.data.dto.ApiResponse
import master_provider_else.reclamos.domain.model.User
import master_provider_else.reclamos.domain.model.toDomain
import retrofit2.Response
import javax.inject.Inject


class GetLoginUseCase @Inject constructor(
  private val repository: QuoteRepository
) {
  suspend operator fun invoke(usuario: String, pass: String): User?{
    val result: Response<ApiResponse> = repository.getLoginFromApi(usuario, pass)
    return if (result.isSuccessful) {
      if(result.body()?.Respuesta?.error==0){
        val userEntity = UserEntity(
          usuario = usuario,
          pass = pass,
          cuadrilla = result.body()?.Respuesta?.body?.codigoCuadrilla.toString(),
          nombreUsuario = result.body()?.Respuesta?.body?.nombreusuario.toString(),
          recordar = result.body()?.Respuesta?.body?.codigoCuadrilla.toString(),
          token = result.body()?.Respuesta?.body?.token.toString(),
          estadoDB = result.body()?.Respuesta?.body?.codigoCuadrilla.toString(),
          tiempoNotificacion = result.body()?.Respuesta?.body?.codigoCuadrilla.toString()
        )
        Log.e("UsuarioUseCase", result.body().toString())
        repository.insertUser(userEntity)
        userEntity.toDomain()
      }else{
        Log.e("UsuarioUseCase", "busqueda en la db")
        return repository.getLoginFromDataBase(usuario=usuario,pass=pass)
      }
    } else {
      null
    }
  }
}