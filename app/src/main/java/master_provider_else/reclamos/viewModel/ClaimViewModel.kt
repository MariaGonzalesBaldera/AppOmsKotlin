package master_provider_else.reclamos.viewModel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import master_provider_else.reclamos.data.database.entity.ReclamoEntity
import master_provider_else.reclamos.data.dto.ReclamoArray
import master_provider_else.reclamos.domain.UseCase.GetActividadesUseCase
import master_provider_else.reclamos.domain.UseCase.GetAlumbradoUseCase
import master_provider_else.reclamos.domain.UseCase.GetClaimUseCase
import master_provider_else.reclamos.domain.UseCase.GetFotoUseCase
import master_provider_else.reclamos.domain.UseCase.GetMaterialUseCase
import master_provider_else.reclamos.domain.UseCase.GetStatusUseCase
import javax.inject.Inject

@HiltViewModel
class ClaimViewModel @Inject constructor(
  var getClaimUseCase: GetClaimUseCase,
  var sessionManager: SessionManager,
  var getStatusUseCase: GetStatusUseCase,
  var getMaterialUseCase: GetMaterialUseCase,
  var getAlumbradoUseCase: GetAlumbradoUseCase,
  var getActividadesUseCase: GetActividadesUseCase,
) : ViewModel() {
  private val _programadoReclamos = MutableLiveData<List<ReclamoArray>>()
  val programadoReclamos: LiveData<List<ReclamoArray>> get() = _programadoReclamos

  private val _ejecutadoReclamos = MutableLiveData<List<ReclamoArray>>()
  val ejecutadoReclamos: LiveData<List<ReclamoArray>> get() = _ejecutadoReclamos

  private val _tipoDenunciaList = mutableStateOf<List<Pair<String, String>>>(emptyList())
  val tipoDenunciaList: State<List<Pair<String, String>>> = _tipoDenunciaList

  private val _tipoInstalacionElectricaAfectadaList =
    mutableStateOf<List<Pair<String, String>>>(emptyList())
  val tipoInstalacionElectricaAfectadaList: State<List<Pair<String, String>>> =
    _tipoInstalacionElectricaAfectadaList

  private val _tipoInstalacionAfectadaList = mutableStateOf<List<Pair<String, String>>>(emptyList())
  val tipoInstalacionAfectadaList: State<List<Pair<String, String>>> = _tipoInstalacionAfectadaList

  private val _tipoEquipoProteccionManiobraList =
    mutableStateOf<List<Pair<String, String>>>(emptyList())
  val tipoEquipoProteccionManiobraList: State<List<Pair<String, String>>> =
    _tipoEquipoProteccionManiobraList

  private val _tipoManiobraCapacidadList = mutableStateOf<List<Pair<String, String>>>(emptyList())
  val tipoManiobraCapacidadList: State<List<Pair<String, String>>> = _tipoManiobraCapacidadList

  private val _causaAveriaList = mutableStateOf<List<Pair<String, String>>>(emptyList())
  val causaAveriaList: State<List<Pair<String, String>>> = _causaAveriaList

  private val _solucionAveriaList = mutableStateOf<List<Pair<String, String>>>(emptyList())
  val solucionAveriaList: State<List<Pair<String, String>>> = _solucionAveriaList

  private val _solucionInterrupcionList = mutableStateOf<List<Pair<String, String>>>(emptyList())
  val solucionInterrupcionList: State<List<Pair<String, String>>> = _solucionInterrupcionList

  private val _tipoAreaIntervencionList = mutableStateOf<List<Pair<String, String>>>(emptyList())
  val tipoAreaIntervencionList: State<List<Pair<String, String>>> = _tipoAreaIntervencionList

  private val _materialesList = mutableStateOf<List<Pair<String, String>>>(emptyList())
  val materialesList: State<List<Pair<String, String>>> = _materialesList

  fun onClaimView(strAP: String, context: Context, estado: String) {
    viewModelScope.launch {
      Log.e("token view",sessionManager.getToken().toString())
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

  fun datosTipoDenuncia() {
    viewModelScope.launch {
      val tipoDenuncia = getClaimUseCase.getTipoDenuncia()
      _tipoDenunciaList.value =
        tipoDenuncia.map { Pair(it.CodigoTipoDenuncia, it.NombreTipoDenuncia) }
    }
  }

  fun datosTipoInstalacionElectricaAfectada() {
    viewModelScope.launch {
      val tipoInstalacionElectricaAfectada = getClaimUseCase.getInstalacionElectricaAfectada()
      _tipoInstalacionElectricaAfectadaList.value =
        tipoInstalacionElectricaAfectada.map {
          Pair(
            it.CodigoTipoInstalacionElectricaAfectada,
            it.NombreTipoInstalacionElectricaAfectada
          )
        }
    }
  }

  fun datosTipoInstalacionAfectada() {
    viewModelScope.launch {
      val tipoInstalacionAfectada = getClaimUseCase.getTipoInstalacionAfectada()
      _tipoInstalacionAfectadaList.value =
        tipoInstalacionAfectada.map {
          Pair(
            it.CodigoTipoUbicacionElectrica,
            it.NombreTipoUbicacionElectrica
          )
        }
    }
  }

  fun datosTipoEquipoProteccionManiobra() {
    viewModelScope.launch {
      val tipoEquipoProteccionManiobra = getClaimUseCase.getTipoEquipoProteccionManiobra()
      _tipoEquipoProteccionManiobraList.value =
        tipoEquipoProteccionManiobra.map {
          Pair(
            it.codigoTipoEquipoProteccionManiobra,
            it.nombreTipoEquipoProteccionManiobra
          )
        }
    }
  }

  fun datosTipoManiobraCapacidad() {
    viewModelScope.launch {
      val tipoManiobraCapacidad = getClaimUseCase.getTipoManiobraCapacidad()
      _tipoManiobraCapacidadList.value =
        tipoManiobraCapacidad.map {
          Pair(
            it.CodigoTipoManiobraCapacidad,
            it.NombreTipoManiobraCapacidad
          )
        }
    }
  }

  fun datosCausaAveria() {
    viewModelScope.launch {
      val causaAveria = getClaimUseCase.getCausaAveria()
      _causaAveriaList.value =
        causaAveria.map { Pair(it.codigoCausaAveria, it.nombreCausaAveriaElectrica) }
    }
  }

  fun datosSolucionAveria() {
    viewModelScope.launch {
      val solucionAveria = getClaimUseCase.getSolucionAveria()
      _solucionAveriaList.value =
        solucionAveria.map { Pair(it.CodigoAccionSolucionAveria, it.NombreAccionSolucionAveria) }
    }
  }

  fun datosSolucionInterrupcion() {
    viewModelScope.launch {
      val solucionInterrupcion = getClaimUseCase.getSolucionInterrupcion()
      _solucionInterrupcionList.value =
        solucionInterrupcion.map {
          Pair(
            it.CodigoTipoSolucionInterrupcion,
            it.NombreTipoSolucionInterrupcion
          )
        }
    }
  }

  fun datosTipoAreaIntervencion() {
    viewModelScope.launch {
      val tipoAreaIntervencion = getClaimUseCase.getTipoAreaIntervencion()
      _tipoAreaIntervencionList.value =
        tipoAreaIntervencion.map {
          Pair(
            it.CodigoTipoAreaIntervencion,
            it.NombreTipoAreaIntervencion
          )
        }
    }
  }

  fun datosMaterialesList(codigoReclamo: String, tipo: Array<String>) {
    viewModelScope.launch {
      val materiales = getMaterialUseCase.getMateriales(
        cuadrilla = sessionManager.getCuadrilla().toString(),
        codigoReclamo
      )
      _materialesList.value =
        materiales.map {
          Pair(
            it.CodigoMaterial,
            it.NombreMaterial
          )
        }
    }
  }

  fun listarFotos(codigoReclamo: String): String {
    var nodoValue = ""
    viewModelScope.launch {
      nodoValue = getAlumbradoUseCase.obtenerNodo()
    }
    return nodoValue
  }

  fun recuperarDataFichaTenica() {
    viewModelScope.launch {
      getAlumbradoUseCase.recuperarDataFichaTecnica()
    }
  }
  fun cargarlistanodos() {
    viewModelScope.launch {
      getActividadesUseCase.cargarlistanodos()
    }
  }
}













