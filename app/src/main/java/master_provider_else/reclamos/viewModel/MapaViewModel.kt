package master_provider_else.reclamos.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import master_provider_else.reclamos.data.database.entity.ReclamoEntity
import master_provider_else.reclamos.domain.UseCase.GetMapUseCase
import master_provider_else.reclamos.domain.model.ParamMap
import javax.inject.Inject

@HiltViewModel
class MapaViewModel @Inject constructor(
  var sessionManager: SessionManager,
  var getMapUseCase: GetMapUseCase
) : ViewModel() {
  fun obtenerPuntosGPSServicio(
    item: ParamMap,
    context: Context,
  ): Boolean {
    var result = false
    viewModelScope.launch {
      result = getMapUseCase.fetchGetPoint(
        authorization = sessionManager.getToken().toString(),
        strCodigoCuadrilla = sessionManager.getCuadrilla().toString(),
        context = context,
        item
      )
    }
    return result
  }

  fun inciarTrabajo(
    context: Context,
  ) {
    viewModelScope.launch {
      val result = getMapUseCase.fetchIniciarTrabajo(
        context = context,
        usuario = sessionManager.getUsuario().toString()
      )
    }
  }
  fun inciarTrabajoBoton() {
    viewModelScope.launch {
      val result = getMapUseCase.InicioTrabajo()
    }
  }
}