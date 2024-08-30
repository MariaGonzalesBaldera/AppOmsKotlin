package master_provider_else.reclamos.domain.shared

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import master_provider_else.reclamos.data.QuoteRepository
import master_provider_else.reclamos.data.database.entity.FotoEntity
import master_provider_else.reclamos.data.database.entity.ReclamoEntity
import master_provider_else.reclamos.data.database.entity.ReclamoInformeOMSEntity
import master_provider_else.reclamos.data.dto.fotoRequest
import javax.inject.Inject

class SharedFunctions @Inject constructor(
  private val repository: QuoteRepository
) {

  //faltaria una funcion para eliminar ususrio y proceso de reclamo en curso
  suspend fun actualizarReclamoInformeOMSEnviado(reclamoInformeOMS: ReclamoInformeOMSEntity) {
    withContext(Dispatchers.IO) {
      reclamoInformeOMS.Enviado = 1
      repository.reclamoInformeOMS_New(reclamoInformeOMS)
    }
  }

  suspend fun actualizarEstadoReclamoEnviado(reclamo: ReclamoEntity) {
    reclamo.enviado = 1
    repository.reclamo_Update(reclamo)
  }

  suspend fun nuevaFoto(itemFoto: FotoEntity, authorization: String) {
    try {
      val params = fotoRequest(
        CodigoReclamoComercial = itemFoto.CodigoReclamo,
        Nombrearchivo = itemFoto.NombreArchivo,
        Extensionarchivo = "jpg",
        Itemimage = itemFoto.base64Source,
        ItemID = itemFoto.ItemID,
      )
      val response = repository.guardarArchivoComercialRepository(authorization, params)
      if (response.isSuccessful) {
        if (response.body()?.respuesta?.error != 0) {
          Log.e("LOG", "Error de codigo")
        } else {
          actualizarestadoFotosEnviado(itemFoto)
        }
      } else {
        Log.e("API Error", "HTTP error: ${response.code()} ${response.message()}")

      }

    } catch (e: Exception) {
      Log.e("API Exception nueva foto", "Exception: ${e.message}")

    }
  }

  suspend fun actualizarestadoFotosEnviado(itemFoto: FotoEntity) {
    withContext(Dispatchers.IO) {
      itemFoto.Enviado = "1"
      repository.fotos_New(itemFoto)
    }
  }

}