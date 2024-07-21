package master_provider_else.reclamos.data.database.state

import android.content.Context
import android.content.SharedPreferences
import master_provider_else.reclamos.domain.model.User
import javax.inject.Inject

class UserPreferences @Inject constructor(
  private val sharedPreferences: SharedPreferences
) {

  companion object {
    private const val USERNAME_KEY = "username"
    private const val PASSWORD_KEY = "password"
    private const val CHECKED_KEY = "checked"
    private const val TOKEN_KEY = "token"
  }

  fun saveUserCredentials(username: String, password: String, checked: String, token: String) {
    with(sharedPreferences.edit()) {
      putString(USERNAME_KEY, username)
      putString(PASSWORD_KEY, password)
      putString(CHECKED_KEY, checked)
      putString(TOKEN_KEY, token)
      apply()
    }
  }

  fun getUserCredentials(): User? {
    val username = sharedPreferences.getString(USERNAME_KEY, null)
    val password = sharedPreferences.getString(PASSWORD_KEY, null)
    val checked = sharedPreferences.getString(CHECKED_KEY, null)
    val token = sharedPreferences.getString(TOKEN_KEY, null)

    return if (username != null && password != null && token != null) checked?.let {
      User(
        usuario = username,
        pass = password,
        cuadrilla = it,
        token = token
      )
    } else null
  }

  fun clearUserCredentials() {
    with(sharedPreferences.edit()) {
      remove(USERNAME_KEY)
      remove(PASSWORD_KEY)
      remove(CHECKED_KEY)
      remove(TOKEN_KEY)
      apply()
    }
  }
}