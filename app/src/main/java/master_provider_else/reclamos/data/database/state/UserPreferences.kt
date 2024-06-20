package master_provider_else.reclamos.data.database.state

import android.content.Context
import android.content.SharedPreferences
import master_provider_else.reclamos.domain.model.User
import javax.inject.Inject

class UserPreferences @Inject constructor(
  private val sharedPreferences: SharedPreferences) {

  companion object {
    private const val USERNAME_KEY = "username"
    private const val PASSWORD_KEY = "password"
    private const val CHECKED_KEY = "checked"
  }

  fun saveUserCredentials(username: String, password: String, checked: String) {
    with(sharedPreferences.edit()) {
      putString(USERNAME_KEY, username)
      putString(PASSWORD_KEY, password)
      putString(CHECKED_KEY, checked)
      apply()
    }
  }

  fun getUserCredentials(): User? {
    val username = sharedPreferences.getString(USERNAME_KEY, null)
    val password = sharedPreferences.getString(PASSWORD_KEY, null)
    val checked = sharedPreferences.getString(CHECKED_KEY, null)

    return if (username != null && password != null) checked?.let {
      User(
        usuario = username,
        pass = password,
        cuadrilla = it,
      )
    } else null
  }

  fun clearUserCredentials() {
    with(sharedPreferences.edit()) {
      remove(USERNAME_KEY)
      remove(PASSWORD_KEY)
      remove(CHECKED_KEY)
      apply()
    }
  }
}