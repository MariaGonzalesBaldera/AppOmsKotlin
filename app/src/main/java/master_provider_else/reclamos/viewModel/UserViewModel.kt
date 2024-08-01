package master_provider_else.reclamos.viewModel

import android.content.Context
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
import master_provider_else.reclamos.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
  var getLoginUseCase: GetLoginUseCase,
  var userPreferences: UserPreferences,

  ) : ViewModel() {

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

  fun onLoginView(usuario: String, pass: String, context: Context) {
    viewModelScope.launch {
        val result = getLoginUseCase(usuario, pass,context)
        if (result != null) {
          validModel.postValue(true)
        } else {
          validModel.postValue(false)
        }
    }
  }

  fun saveCredentials(username: String, password: String, checked: String) {
    viewModelScope.launch {
      try {
        userPreferences.saveUserCredentials(
          username = username,
          password = password,
          checked = checked
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