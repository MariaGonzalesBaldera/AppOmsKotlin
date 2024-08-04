package master_provider_else.reclamos.viewModel

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

  private var token: String? = null
  private var cuadrilla: String? = null
  private var usuario: String? = null

  fun saveToken(newToken: String) {
    token = newToken
  }

  fun getToken(): String? {
    return token
  }

  fun saveCuadrilla(newCuadrilla: String) {
    cuadrilla = newCuadrilla
  }

  fun getCuadrilla(): String? {
    return cuadrilla
  }
  fun saveUsuario(newUsuario: String) {
    usuario = newUsuario
  }
  fun getUsuario(): String? {
    return usuario
  }

}