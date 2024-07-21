package master_provider_else.reclamos.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import master_provider_else.reclamos.data.database.state.UserPreferences
import master_provider_else.reclamos.domain.UseCase.GetLoginUseCase
import master_provider_else.reclamos.domain.model.User
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
  var getLoginUseCase: GetLoginUseCase,
  var userPreferences: UserPreferences
) : ViewModel() {
  var getToken: String = ""
  val validModel = MutableLiveData<Boolean>()
  private val _errorMessage = MutableLiveData<String?>()
  val errorMessage: LiveData<String?> get() = _errorMessage

  //verificar si existe usuario guardado en memoria
  private val _userCredentials = MutableStateFlow<User?>(null)
  val userCredentials: StateFlow<User?> get() = _userCredentials

  init {
    viewModelScope.launch {
      val user = userPreferences.getUserCredentials()
      _userCredentials.value = user
    }
  }

  fun onLoginView(usuario: String, pass: String) {

    viewModelScope.launch {
      val result = getLoginUseCase(usuario, pass)
      if (result != null) {
        getToken = result.token

      }
      Log.e("onLoginView", result.toString())
      Log.e("token de login", getToken)
      if (result != null) {
        validModel.postValue(true)
        Log.e("Agregado al viewModel", result.toString())
      } else {
        validModel.postValue(false)
        Log.e("UsuarioDBViewModel", "Error: No se pudo obtener el usuario")
      }
    }
  }

  fun saveCredentials(username: String, password: String, checked: String) {
    viewModelScope.launch {
      try {
        userPreferences.saveUserCredentials(
          username = username,
          password = password,
          checked = checked,
          token = getToken
        )
      } catch (e: Exception) {
        _errorMessage.value = e.message
      }
    }
  }

  fun clearCredentials() {
    viewModelScope.launch {
      userPreferences.clearUserCredentials()
    }
  }

}