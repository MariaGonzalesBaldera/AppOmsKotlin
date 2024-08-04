package master_provider_else.reclamos.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import master_provider_else.reclamos.data.database.entity.ReclamoEntity
import master_provider_else.reclamos.data.dto.ReclamoArray
import master_provider_else.reclamos.domain.UseCase.GetClaimUseCase
import master_provider_else.reclamos.domain.UseCase.GetStatusUseCase
import javax.inject.Inject

@HiltViewModel
class ClaimViewModel @Inject constructor(
  var getClaimUseCase: GetClaimUseCase,
  var sessionManager: SessionManager,
  var getStatusUseCase: GetStatusUseCase
) : ViewModel() {
  private val _programadoReclamos = MutableLiveData<List<ReclamoArray>>()
  val programadoReclamos: LiveData<List<ReclamoArray>> get() = _programadoReclamos

  private val _ejecutadoReclamos = MutableLiveData<List<ReclamoArray>>()
  val ejecutadoReclamos: LiveData<List<ReclamoArray>> get() = _ejecutadoReclamos

  fun onClaimView(strAP: String, context: Context, estado: String) {
    viewModelScope.launch {
      val result = getClaimUseCase.fetchClaims(
        authorization = sessionManager.getToken().toString(),
        strCodigoCuadrilla = sessionManager.getCuadrilla().toString(),
        strCodigoEstadoReclamo = estado,
        strAP = strAP,
        context = context
      )
      if (result.isNotEmpty()) {
        when (estado) {
          "2" -> _programadoReclamos.postValue(result as List<ReclamoArray>)
          "5" -> _ejecutadoReclamos.postValue(result as List<ReclamoArray>)
        }
        Log.e("valores reclamo", result.toString())
      }
    }
  }

  fun cambioEstadoViewModel(
    item: ReclamoEntity,
    context: Context,
    codigoEstado: String,
    siguiente: String
  ): Boolean {
    var result = false
    viewModelScope.launch {
      result = getStatusUseCase.fetchChangeStatus(
        authorization = sessionManager.getToken().toString(),
        context = context,
        item = item,
        codigoEstado = codigoEstado,
        siguiente = siguiente
      )
    }
    return result
  }
}