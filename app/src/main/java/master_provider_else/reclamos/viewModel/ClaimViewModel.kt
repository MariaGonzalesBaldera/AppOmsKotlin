package master_provider_else.reclamos.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import master_provider_else.reclamos.data.dto.ReclamoArray
import master_provider_else.reclamos.domain.UseCase.GetClaimUseCase
import master_provider_else.reclamos.domain.model.Claim
import javax.inject.Inject

@HiltViewModel
class ClaimViewModel @Inject constructor(
  var getClaimUseCase: GetClaimUseCase,
  var sessionManager: SessionManager
) : ViewModel() {
  private val _reclamoModel = MutableLiveData<List<ReclamoArray>>()
  val reclamoModel: LiveData<List<ReclamoArray>> get() = _reclamoModel

  fun onClaimView(strAP: String, context: Context, estado:String) {
    viewModelScope.launch {
      val result = getClaimUseCase.fetchClaims(
        contentType = "application/json; charset=UTF-8",
        authorization = sessionManager.getToken().toString(),
        strCodigoCuadrilla = sessionManager.getCuadrilla().toString(),
        strCodigoEstadoReclamo = estado,  //programado
        strAP = strAP, //oms
        context = context
      )
      if (result.isNotEmpty()) {
        _reclamoModel.postValue(result as List<ReclamoArray>)
        Log.e("valores reclamo", result.toString());
      }
    }
  }
}