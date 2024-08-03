package master_provider_else.reclamos.domain.UseCase

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import master_provider_else.reclamos.data.QuoteRepository
import master_provider_else.reclamos.data.database.entity.ReclamoEntity
import master_provider_else.reclamos.data.dto.EstadoRequest
import master_provider_else.reclamos.view.ui.theme.toast
import java.util.Calendar
import javax.inject.Inject

class GetStatusUseCase @Inject constructor(
  private val repository: QuoteRepository
) {
  suspend fun fetchChangeStatus(
    authorization: String,
    context: Context,
    item: ReclamoEntity,
    codigoEstado: String,
    siguiente: String
  ): Boolean {
    val estadoRequest = EstadoRequest(
      codigoOrigenOMS = item.CodigoReclamo,
      codigoEstado = codigoEstado,
      siguiente = siguiente
    )

    return try {
      val response = repository.getCambioEstado(
         "Bearer $authorization", estadoRequest
      )
      if (response.isSuccessful) {
        val responseBody = response.body()
        responseBody?.respuesta?.error != 0
        actualizarEstadoReclamo(item, codigoEstado, context)
        true
      } else {
        Log.e("API Error", "HTTP error: ${response.code()} ${response.message()}")
        false
      }
    } catch (e: Exception) {
      Log.e("API Exception", "Exception: ${e.message}")
      false
    }
  }

  private suspend fun actualizarEstadoReclamo(
    reclamo: ReclamoEntity,
    codigoEstado: String,
    context: Context
  ) {
    val currentTime = Calendar.getInstance().time
    val time = currentTime.time
    reclamo.CodigoEstadoReclamo = codigoEstado
    reclamo.FechaEnCamino = time

    if ((reclamo.FechaProgramacion == null) || (reclamo.FechaProgramacion == 0.toLong())) {
      reclamo.FechaProgramacion = time
    }
    context.toast("time: $time")
    withContext(Dispatchers.IO) {
      repository.Reclamo_Update(reclamo)
    }
  }
}




















