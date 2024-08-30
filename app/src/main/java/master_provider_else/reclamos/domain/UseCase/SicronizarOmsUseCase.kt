package master_provider_else.reclamos.domain.UseCase

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import master_provider_else.reclamos.data.QuoteRepository
import master_provider_else.reclamos.data.database.entity.EncuestaEnviarEntity
import master_provider_else.reclamos.data.database.entity.FotoEntity
import master_provider_else.reclamos.data.database.entity.ReclamoEntity
import master_provider_else.reclamos.data.database.entity.ReclamoInformeOMSEntity
import master_provider_else.reclamos.data.dto.EliminarFotoRequest
import master_provider_else.reclamos.data.dto.EncuestaRequest
import master_provider_else.reclamos.data.dto.FinTrabajoCompletoRequest
import master_provider_else.reclamos.domain.shared.SharedFunctions
import master_provider_else.reclamos.utils.isConnected
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

//se ejecuta al presionar el boton sincronizar de oms
class SicronizarOmsUseCase @Inject constructor(
  private val repository: QuoteRepository,
  private val sharedFunctions: SharedFunctions
) {
  //falta obtener estos datos
  val _authorizationContext: String = ""
  val _loginUsuarioContext: String = ""
  val _cuadrillaContext: String = ""

  //****************
  private suspend fun siscronizarDatosOms(context: Context) {
    if (isConnected(context)) {
      obtenerReclamoInformeOMS()
      //obtenerMaterialesReclamo();
      obtenerFotosEnviar()
      obtenerFotosEliminar()
    } else {

    }
  }

  private suspend fun obtenerFotosEliminar() {
    var arrayl = repository.foto_Get_enviados("-1")
    for (i in arrayl.indices) {
      val item: FotoEntity = arrayl[i]
      eliminarFotos(item, item.ItemID)
    }
  }

  private suspend fun eliminarFotos(item: FotoEntity, itemID: String) {
    try {
      val listaFotosEliminar = java.util.ArrayList<String>()
      listaFotosEliminar.clear()
      listaFotosEliminar.add(itemID)
      val params = EliminarFotoRequest(
        CodigoReclamo = item.CodigoReclamo,
        ListaItemID = listaFotosEliminar,
      )
      val response = repository.archivoMovilEliminarRepository(_authorizationContext, params)
      if (response.isSuccessful) {
        if (response.body()?.respuesta?.error != 0) {
          Log.e("Error", "Error de codigo")
        } else {
          eliminarFotoDB(item)
        }
      } else {
        Log.e("API Error", "HTTP error: ${response.code()} ${response.message()}")
      }
    } catch (e: Exception) {
      Log.e("API Exception Eliminar Foto", "Exception: ${e.message}")
    }
  }

  private suspend fun eliminarFotoDB(item: FotoEntity) {
    withContext(Dispatchers.IO) {
      repository.fotos_delete(item)
    }
  }

  private suspend fun obtenerFotosEnviar() {
    withContext(Dispatchers.IO) {
      val arrayl = repository.foto_Get_enviados("0")
      try {
        for (i in arrayl.indices) {
          val item: FotoEntity = arrayl[i]
          sharedFunctions.nuevaFoto(item, _authorizationContext)
        }
      } catch (e: java.lang.Exception) {
        e.printStackTrace()
      }
    }
  }

  private suspend fun obtenerReclamoInformeOMS() {
    withContext(Dispatchers.IO) {
      try {
        val arrayl = repository.reclamoInformeOMS_Get_enviados(0)

        for (i in arrayl.indices) {
          val item: ReclamoInformeOMSEntity = arrayl[i]
          val itemreclamo = repository.reclamo_Get(item.CodigoReclamo)

          enviarInformeOMS(item, itemreclamo, 0)

          var arrayEncuesta: List<EncuestaEnviarEntity> = ArrayList<EncuestaEnviarEntity>()
          arrayEncuesta = repository.encuestaEnviar_Get_Enviado(0, item.CodigoReclamo)
          for (j in arrayEncuesta.indices) {
            val encuestaenviar = arrayEncuesta[j]
            guardarEncuesta(encuestaenviar, item.CodigoReclamo)
          }
        }
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }

  private suspend fun guardarEncuesta(encuestaenviar: EncuestaEnviarEntity, codigoReclamo: String) {
    val params = EncuestaRequest(
      CodigoOrigenOMS = codigoReclamo,
      CodigoEncuesta = encuestaenviar.CodigoEncuesta,
      CodigoPregunta = encuestaenviar.CodigoPregunta,
      CodigoTipoRespuesta = encuestaenviar.CodigoTipoRespuesta,
      CodigoAlternativa = encuestaenviar.CodigoAlternativa,
      ValorAlternativa = encuestaenviar.Valor,
      LoginUsuario = _loginUsuarioContext,
    )

    try {
      val response = repository.guardarEncuestaRepository(_authorizationContext, params)
      if (response.isSuccessful) {
        if (response.body()?.respuesta?.error != 0) {
          Log.e("Error", "Error de codigo")
        } else {
          actualizarestadoEncuesta(encuestaenviar)
        }
      } else {
        Log.e("API Error", "HTTP error: ${response.code()} ${response.message()}")
      }
    } catch (e: Exception) {
      Log.e("API Exception SERV ENCUESTA", "Exception: ${e.message}")

    }
  }

  private suspend fun actualizarestadoEncuesta(encuestaenviar: EncuestaEnviarEntity) {
    withContext(Dispatchers.IO) {
      encuestaenviar.enviado = 1

    }
  }

  private suspend fun enviarInformeOMS(
    reclamoInformeOMS: ReclamoInformeOMSEntity,
    itemreclamo: ReclamoEntity,
    tipo: Int
  ) {

    val formato = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

    val fechaFinInterrupcion = if (reclamoInformeOMS.FechaFinInterrupcion.length <= 1) {
      reclamoInformeOMS.FechaFinInterrupcion.replace(" ", "null")
    } else {
      reclamoInformeOMS.FechaFinInterrupcion
    }

    val paramsRequest = FinTrabajoCompletoRequest(
      CodigoReclamo = reclamoInformeOMS.CodigoReclamo,
      CodigoCuadrilla = _cuadrillaContext,
      LoginUsuario = _loginUsuarioContext,
      CodigoTipoDenuncia = reclamoInformeOMS.CodigoTipoDenuncia.replace("", "0"),
      CodigoTipoUbicacionAfectada =
      reclamoInformeOMS.CodigoTipoUbicacionAfectada.replace("", "0"),
      CodigoTipoInstalacionElectricaAfectada =
      reclamoInformeOMS.CodigoTipoInstalacionElectricaAfectada.replace("", "0"),
      CodigoTipoEquipoProteccionManiobra =
      reclamoInformeOMS.CodigoTipoEquipoProteccionManiobra.replace("", "0"),
      CapacidadCarga = reclamoInformeOMS.CapacidadCarga.replace("", "0"),
      CodigoEquipoProteccionManiobraNodoCircuito =
      reclamoInformeOMS.CodigoEquipoProteccionManiobraNodoCircuito.replace("", "0"),
      CodigoEquipoProteccionManiobraSED =
      reclamoInformeOMS.CodigoEquipoProteccionManiobraSED.replace("", "0"),
      CodigoCausaAveriaElectrica =
      reclamoInformeOMS.CodigoCausaAveriaElectrica.replace("", "0"),
      CodigoAccionSolucionAveria =
      reclamoInformeOMS.CodigoAccionSolucionAveria.replace("", "0"),
      CodigoTipoSolucionIntervencion =
      reclamoInformeOMS.CodigoTipoSolucionIntervencion.replace("", "0"),
      CodigoTipoAreaIntervencion =
      reclamoInformeOMS.CodigoTipoAreaIntervencion.replace("", "0"),
      FechaFinInterrupcion = fechaFinInterrupcion,
      ObservacionAccionSolucionAveria =
      reclamoInformeOMS.ObservacionAccionSolucionAveria.replace("null", " "),

      FechaProgramacion = formato.format(Date(itemreclamo.FechaProgramacion)),
      FechaEnCamino = formato.format(Date(itemreclamo.FechaEnCamino)),
      FechaEjecucionInicio = formato.format(Date(itemreclamo.FechaEjecucionInicio)),
      FechaInicioTrabajo =
      formato.format(Date(itemreclamo.FechaEjecucionInicio)).toString(),
      FechaFinTrabajo = formato.format(Date(itemreclamo.FechaEjecucionFin)).toString()

    )

    try {
      val result = repository.finTrabajoCompletoRepository(
        _authorizationContext, paramsRequest
      )
      if (result.isSuccessful) {
        Log.e("TAG", "Servicio guardadocon exito: ")
        if (result.body()?.respuesta?.error != 0) {
          Log.e("Error", "respuesta error")
        } else {
          Log.d("OK", "Envio Correcto")

          sharedFunctions.actualizarReclamoInformeOMSEnviado(reclamoInformeOMS)

          sharedFunctions.actualizarEstadoReclamoEnviado(itemreclamo)

          if (tipo == 0) {
            Log.d("OK", "Envio Correcto")//MENSAJE PPARA EL USUSARIO
          }
        }
      } else {
        Log.e("API Error", "HTTP error: ${result.code()} ${result.message()}")

      }

    } catch (e: Exception) {
      Log.e("API Exception SERV ENCUESTA", "Exception: ${e.message}")

    }

  }
}