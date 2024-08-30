package master_provider_else.reclamos.domain.UseCase

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import master_provider_else.reclamos.data.QuoteRepository
import master_provider_else.reclamos.domain.shared.SharedFunctions
import master_provider_else.reclamos.utils.isConnected
import master_provider_else.reclamos.view.ui.theme.toast
import javax.inject.Inject

class SincronizarApUseCase @Inject constructor(
  private val repository: QuoteRepository,
  private val sharedFunctions: SharedFunctions
) {
  //cuando presiona el boton de sincronizar AP
  suspend fun sincronizarDatofsAp(context:Context){
    if(isConnected(context)){
      obtenerReclamoInformeOMSAP()
      obtenerFotosEnviar()
      obtenerFotosEliminar()
      obtenerMaterialesReclamo()
      obtenerInformeOMSAPNodo()
      verificarParaenviarNodos()
    }else{
      context.toast("Para enviar los datos debe activar su conexión a internet")
    }
  }

  private suspend fun obtenerReclamoInformeOMSAP() {
    withContext(Dispatchers.IO){
      val _arrayl = repository.reclamoInformeOMSAP_Get_enviados(1,"guardar")
    }
  }

}