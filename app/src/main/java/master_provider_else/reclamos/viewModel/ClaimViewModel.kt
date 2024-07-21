package master_provider_else.reclamos.viewModel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import master_provider_else.reclamos.data.database.entity.UserEntity
import master_provider_else.reclamos.data.database.state.UserPreferences
import master_provider_else.reclamos.domain.UseCase.GetClaimUseCase
import javax.inject.Inject

@HiltViewModel
class ClaimViewModel @Inject constructor(
  var getClaimUseCase: GetClaimUseCase,
  var userPreferences: UserPreferences,
) : ViewModel() {
  fun onClaimView() {
    Log.e("token", userPreferences.getUserCredentials()?.token ?: "")
    viewModelScope.launch {
      val result = getClaimUseCase.fetchClaims(
        contentType = "application/json; charset=UTF-8",
        authorization = userPreferences.getUserCredentials()?.let { user ->
          user.token
        } ?: "",
        strCodigoCuadrilla = "1",
        strCodigoEstadoReclamo = "2",
        strAP = "0"
      )
      Log.e("onClaimView", result.toString())
      if (result != null) {
        Log.e("correcto", result.toString())
      } else {
        Log.e("error", "Error")
      }
    }
  }
}