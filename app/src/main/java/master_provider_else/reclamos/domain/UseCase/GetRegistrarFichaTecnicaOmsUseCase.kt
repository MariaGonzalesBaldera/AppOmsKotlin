package master_provider_else.reclamos.domain.UseCase

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import master_provider_else.reclamos.data.QuoteRepository
import master_provider_else.reclamos.data.database.entity.FotoEntity
import master_provider_else.reclamos.data.database.entity.MaterialEntity
import master_provider_else.reclamos.data.database.entity.ReclamoEntity
import master_provider_else.reclamos.data.database.entity.ReclamoInformeOMSEntity
import master_provider_else.reclamos.data.dto.FinTrabajoCompletoRequest
import master_provider_else.reclamos.data.dto.GuardarMaterialRequest
import master_provider_else.reclamos.data.dto.fotoRequest
import master_provider_else.reclamos.utils.isConnected
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class GetRegistrarFichaTecnicaOmsUseCase @Inject constructor(
  private val repository: QuoteRepository
) {
  //falta recuperar datos
  val cuadrillaContext: String = ""
  val loginUsuarioContext: String = ""
  val authorizationContext: String = ""
  suspend fun registrarFichaTecnica() {

  }

  suspend fun actualizarEstadoReclamo(
    reclamoInformeOMS: ReclamoInformeOMSEntity,
    context: Context
  ) {
    withContext(Dispatchers.IO) {
      val reclamo = repository.reclamo_Get(reclamoInformeOMS.CodigoReclamo)

      val currentTime = Calendar.getInstance().time
      reclamo.CodigoEstadoReclamo = "5"
      reclamo.FechaEjecucionFin = currentTime.time

      repository.reclamo_Update(reclamo)

      // solo si esta conectado a internet guardar el reclamo, materiales y fotos
      if (isConnected(context)) {
        finTrabajoCompleto(reclamoInformeOMS, reclamo, 0)
        // validar si el ReclamoInformeOMS.Enviado = 1, para regresar en el listado de materiales y reclamos a enviar
        obtenerMaterialesReclamo(reclamoInformeOMS.CodigoReclamo)
        obtenerFotosReclamo(reclamoInformeOMS.CodigoReclamo)
      } else {
        Log.e(
          "Mostrar como mensaje",
          "No se envió el reclamo ni las fotos porque no está conectado a internet",
        )
      }
    }

  }

  private suspend fun obtenerFotosReclamo(codigoReclamo: String) {
    withContext(Dispatchers.IO) {
      val _arrayl = repository.foto_Get_enviados("0", codigoReclamo)
      val _arraydelete = repository.foto_Get_enviados("1", codigoReclamo)

      try {
        for (i in _arrayl.indices) {
          val foto: FotoEntity = _arrayl.get(i)
          nuevaFoto(foto)
        }
        for (i in _arraydelete.indices) {
          val foto: FotoEntity = _arraydelete.get(i)
          eliminarFoto(foto)
        }
      } catch (e: java.lang.Exception) {
        e.printStackTrace()
      }
    }
  }

  private suspend fun eliminarFoto(foto: FotoEntity) {
    withContext(Dispatchers.IO) {
      repository.fotos_delete(foto)
    }
  }

  private suspend fun nuevaFoto(itemFoto: FotoEntity) {
    try {
      val params = fotoRequest(
        CodigoReclamoComercial = itemFoto.CodigoReclamo,
        Nombrearchivo = itemFoto.NombreArchivo,
        Extensionarchivo = "jpg",
        Itemimage = itemFoto.base64Source,
        ItemID = itemFoto.ItemID,
      )
      val response = repository.guardarArchivoComercialRepository(authorizationContext, params)
      if (response.isSuccessful) {
        if (response.body()?.respuesta?.error != 0) {
          Log.e("LOG", "Error de codigo")
        } else {

          //listarFotos(Codigo);
          // actualizarestadoFotos
          actualizarestadoFotosEnviado(itemFoto)
        }
      } else {
        Log.e("API Error", "HTTP error: ${response.code()} ${response.message()}")

      }

    } catch (e: Exception) {
      Log.e("API Exception nueva foto", "Exception: ${e.message}")

    }
  }

  private suspend fun actualizarestadoFotosEnviado(itemFoto: FotoEntity) {
    withContext(Dispatchers.IO) {
      itemFoto.Enviado = "1"
      repository.fotos_New(itemFoto)
    }
  }

  private suspend fun obtenerMaterialesReclamo(codigoReclamo: String) {
    withContext(Dispatchers.IO) {
      val _arrayl = repository.material_All_Send(codigoReclamo)
      val _arraydelete = repository.material_All_Send_delete(codigoReclamo)

      try {
        for (i in _arrayl.indices) {
          val material: MaterialEntity = _arrayl[i]
          enviarMaterial(material)
        }

        for (i in _arraydelete.indices) {
          val material: MaterialEntity = _arraydelete[i]
          eliminarMaterial(material)
        }
      } catch (e: java.lang.Exception) {
        e.printStackTrace()
      }

    }
  }

  private suspend fun eliminarMaterial(material: MaterialEntity) {
    try {
      val params = GuardarMaterialRequest(
        CodigoReclamo = material.CodigoReclamo,
        CodigoCuadrilla = material.CodigoCuadrilla,
        CodigoMaterial = material.CodigoMaterial,
        NombreMaterial = material.NombreMaterial,
        CantidadUtilizada = material.Cantidad,
      )
      val response = repository.eliminarInformeMaterialRepository(authorizationContext, params)
      if (response.isSuccessful) {
        if (response.body()?.respuesta?.error != 0) {
          borrarMaterial(material)
        } else {
          Log.e("LOG", "Error de codigo")
        }
      } else {
        Log.e("API Error", "HTTP error: ${response.code()} ${response.message()}")
      }
    } catch (e: Exception) {
      Log.e("API Exception SERV ENCUESTA", "Exception: ${e.message}")

    }
  }

  private suspend fun borrarMaterial(material: MaterialEntity) {
    withContext(Dispatchers.IO) {
      repository.material_delete(material)
    }
  }

  private suspend fun enviarMaterial(material: MaterialEntity) {
    val params = GuardarMaterialRequest(
      CodigoReclamo = material.CodigoReclamo,
      CodigoCuadrilla = material.CodigoCuadrilla,
      CodigoMaterial = material.CodigoMaterial,
      NombreMaterial = material.NombreMaterial,
      CantidadUtilizada = material.Cantidad,
    )

    try {
      val response = repository.guardarInformeMaterialRepository(authorizationContext, params)
      if (response.isSuccessful) {
        if (response.body()?.respuesta?.error != 0) {
          Log.e("LOG", "Error de codigo")
        } else {
          actualizarestadoMaterialEnviado(material)
        }

      } else {
        Log.e("API Error", "HTTP error: ${response.code()} ${response.message()}")
      }
    } catch (e: Exception) {
      Log.e("API Exception SERV ENCUESTA", "Exception: ${e.message}")

    }

  }

  private suspend fun actualizarestadoMaterialEnviado(material: MaterialEntity) {
    withContext(Dispatchers.IO) {
      material.Enviado = "0"
      repository.material_New(material)
    }
  }

  private suspend fun finTrabajoCompleto(
    reclamoInformeOMS: ReclamoInformeOMSEntity,
    reclamo: ReclamoEntity,
    tipo: Int
  ) {
    val params = HashMap<String, String>()

    val formato = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

    val fechaFinInterrupcion = if (reclamoInformeOMS.FechaFinInterrupcion.length <= 1) {
      reclamoInformeOMS.FechaFinInterrupcion.replace(" ", "null")
    } else {
      reclamoInformeOMS.FechaFinInterrupcion
    }

    val paramsRequest = FinTrabajoCompletoRequest(
      CodigoReclamo = reclamoInformeOMS.CodigoReclamo,
      CodigoCuadrilla = cuadrillaContext,
      LoginUsuario = loginUsuarioContext,
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

      FechaProgramacion = formato.format(Date(reclamo.FechaProgramacion)),
      FechaEnCamino = formato.format(Date(reclamo.FechaEnCamino)),
      FechaEjecucionInicio = formato.format(Date(reclamo.FechaEjecucionInicio)),
      FechaInicioTrabajo =
      formato.format(Date(reclamo.FechaEjecucionInicio)).toString(),
      FechaFinTrabajo = formato.format(Date(reclamo.FechaEjecucionFin)).toString()

    )

    try {
      val result = repository.finTrabajoCompletoRepository(
        authorizationContext, paramsRequest
      )
      if (result.isSuccessful) {
        Log.e("TAG", "Servicio guardadocon exito: ")
        if (result.body()?.respuesta?.error != 0) {
          Log.e("Error", "respuesta error")
        } else {
          Log.d("OK", "Envio Correcto")
          actualizarReclamoInformeOMSEnviado(reclamoInformeOMS)
          actualizarEstadoReclamoEnviado(reclamo)

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

  private suspend fun actualizarEstadoReclamoEnviado(reclamo: ReclamoEntity) {
    reclamo.enviado = 1
    repository.reclamo_Update(reclamo)
  }

  private suspend fun actualizarReclamoInformeOMSEnviado(reclamoInformeOMS: ReclamoInformeOMSEntity) {
    withContext(Dispatchers.IO) {
      reclamoInformeOMS.Enviado = 1
      repository.reclamoInformeOMS_New(reclamoInformeOMS)
    }
  }
}

















