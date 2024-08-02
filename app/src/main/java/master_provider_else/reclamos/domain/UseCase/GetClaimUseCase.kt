package master_provider_else.reclamos.domain.UseCase

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import master_provider_else.reclamos.data.QuoteRepository
import master_provider_else.reclamos.data.database.entity.EncuestaEntity
import master_provider_else.reclamos.data.database.entity.PreguntaEntity
import master_provider_else.reclamos.data.database.entity.ReclamoEntity
import master_provider_else.reclamos.data.database.entity.ReclamoInformeOMSEntity
import master_provider_else.reclamos.data.database.entity.toEntity
import master_provider_else.reclamos.utils.isConnected
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

class GetClaimUseCase @Inject constructor(
  private val repository: QuoteRepository
) {
  val arrayReclamo: ArrayList<ReclamoEntity> = ArrayList<ReclamoEntity>()
  var cuadrilla: String = ""
  private var contentTypeContext: String = ""
  private var authorizationContext: String = ""
  suspend fun fetchClaims(
    contentType: String,
    authorization: String,
    strCodigoCuadrilla: String,
    strCodigoEstadoReclamo: String,
    strAP: String,
    context: Context
  ): List<Any> {

    cuadrilla = strCodigoCuadrilla
    contentTypeContext = contentType
    authorizationContext = authorization
    return if (isConnected(context)) {
      val response = repository.getClaimFromApi(
        contentType = contentType,
        authorization = "Bearer $authorization",
        strCodigoCuadrilla = strCodigoCuadrilla,
        strCodigoEstadoReclamo = strCodigoEstadoReclamo,
        strAP = strAP
      )
      if (response.body()?.respuesta?.error != 0) {
        emptyList()
      } else {
        val reclamos = response.body()?.respuesta?.body?.reclamo
        if (reclamos != null && reclamos.isNotEmpty()) {
          insertarReclamos(reclamos.map { it.toEntity() }, strCodigoEstadoReclamo)
          reclamos
        } else {
          emptyList()
        }
      }
    } else {
      val estados: Array<String?> = when (strCodigoEstadoReclamo) {
        "2" -> arrayOf("2", "3", "4")
        else -> arrayOf("5")
      }

      withContext(Dispatchers.IO) {
        val reclamos = repository.getTipoReclamoInDataBase(estados, "RE")
        reclamos.filterNotNullTo(arrayReclamo)
        arrayReclamo
      }
    }
  }

 // private fun descargarFichaTecnica(): List<Any> {
  //  TODO("Not yet implemented")
  //}

  private val arrayListPregunta: java.util.ArrayList<PreguntaEntity> = java.util.ArrayList<PreguntaEntity>()
  private val arrayListEncuesta: java.util.ArrayList<EncuestaEntity> = java.util.ArrayList<EncuestaEntity>()

  private suspend fun getServicioEncuesta() {
    withContext(Dispatchers.IO) {
      val fichaResponse =
        repository.getEncuesta(
          contentTypeContext,
          "Bearer $authorizationContext"
        )
      val respuesta = fichaResponse.body()?.respuesta
      if (respuesta?.error != 0) {
        respuesta?.body?.let { body ->
          val jsonObject = JSONArray(body.encuesta)
          for (i in 0 until jsonObject.length()) {
            val jsonObjectChildEncuesta = jsonObject.getJSONObject(i)
            val joPregunta = JSONArray(jsonObjectChildEncuesta.getString("Pregunta"))

            arrayListEncuesta.add(EncuestaEntity(i, "", i.toString()))

            val arrayListPosition = ArrayList<Int>()

            for (j in 0 until joPregunta.length()) {
              val jsonObjectChild = joPregunta.getJSONObject(j)
              val CodigoEncuesta = jsonObjectChild.getString("CodigoEncuesta")
              val NombreEncuesta = jsonObjectChild.getString("NombreEncuesta")
              val CodigoPregunta = jsonObjectChild.getString("CodigoPregunta")
              val NombrePregunta = jsonObjectChild.getString("NombrePregunta")
              val CodigoTipoRespuesta = jsonObjectChild.getString("CodigoTipoRespuesta")
              val NombreTipoRespuesta = jsonObjectChild.getString("NombreTipoRespuesta")
              val CodigoAlternativa = jsonObjectChild.getString("CodigoAlternativa")
              val Valor = jsonObjectChild.getString("Valor")

              //arrayListPregunta.add(
              //  PreguntaEntity(
              //    joEncuesta.length(),
              //    CodigoEncuesta,
              //    NombreEncuesta,
              //    CodigoPregunta,
              //    NombrePregunta,
              //    CodigoTipoRespuesta,
              //    NombreTipoRespuesta,
              //    CodigoAlternativa,
              //    Valor
              //  )
              //)
              Log.e("TAG", "Codigo encuesta: $NombrePregunta")
              Log.e("TAG", "Valor: $Valor")
              arrayListPosition.add(arrayListPregunta.size - 1)
            }
          }
        }
      } else {
        Log.e("Mensaje", "Mensaje: "+respuesta.message)
      }
    }
  }


  private suspend fun insertarReclamos(reclamosList: List<ReclamoEntity>?, estadoReclamo: String) {
    if (reclamosList?.isNotEmpty() == true) {
      arrayReclamo.clear()
      reclamosList.forEach { reclamoArray ->
        val reclamo = ReclamoEntity().apply {
          CodigoEstadoReclamo = reclamoArray.CodigoEstadoReclamo
          NombreClaseReclamo = reclamoArray.NombreClaseReclamo
          CodigoReclamo = reclamoArray.CodigoReclamo
          NombreSuministro = reclamoArray.NombreSuministro
          DireccionElectrica = reclamoArray.DireccionElectrica
          CodigoSuministro = reclamoArray.CodigoSuministro
          CodigoRutaSuministro = reclamoArray.CodigoRutaSuministro
          CodigoSED = reclamoArray.CodigoSED
          Celular = reclamoArray.Celular
          latitud = reclamoArray.latitud
          longitud = reclamoArray.longitud
          DescripcionReclamo = reclamoArray.DescripcionReclamo
          ReferenciaUbicacion = reclamoArray.ReferenciaUbicacion
          CodigoDireccionElectrica = reclamoArray.CodigoDireccionElectrica
          FechaRegistro = reclamoArray.FechaRegistro
          tipoReclamo = estadoReclamo
          tipolistaReclamo = "RE"
        }
        arrayReclamo.add(reclamo)
      }
      withContext(Dispatchers.IO) {
        repository.insertMultipleClaimsInDataBase(arrayReclamo)
      }

      for (i in arrayReclamo.indices) {
        val item: ReclamoEntity = arrayReclamo[i]
        if (estadoReclamo == "2") {
          //obtenerPuntosGPS(item);
          descargarDatosFichaTecnica(item.CodigoReclamo)
          ///ObtenerMaterialReclamo(item.CodigoReclamo, cuadrilla)
          //insertarFotos(item.CodigoReclamo, arrayReclamo.size)
        }
      }
    }
  }


  private fun insertarFotos(codigoReclamo: String, size: Int) {
    TODO("Not yet implemented")
  }

  private fun ObtenerMaterialReclamo(codigoReclamo: String, cuadrilla: String) {
    TODO("Not yet implemented")
  }

  private suspend fun descargarDatosFichaTecnica(codigoReclamo: String) {
    withContext(Dispatchers.IO) {
      val fichaResponse =
        repository.getFichaApi(
          contentTypeContext,
          "Bearer $authorizationContext",
          codigoReclamo,
          cuadrilla
        )
      val respuesta = fichaResponse.body()?.respuesta
      if (respuesta?.error != 0) {
        respuesta?.body?.let { body ->
          val jsonObject = JSONObject(body.reclamoInformeOMS)
          val reclamoInformeOMSEntity = ReclamoInformeOMSEntity(
            CodigoReclamo = jsonObject.optString("CodigoReclamo"),
            CodigoTipoDenuncia = jsonObject.optString("CodigoTipoDenuncia"),
            ObservacionAccionSolucionAveria = jsonObject.optString("ObservacionAccionSolucionAveria"),
            FechaFinInterrupcion = jsonObject.optString("FechaFinInterrupcion"),
            CodigoTipoInstalacionElectricaAfectada = jsonObject.optString("CodigoTipoInstalacionElectricaAfectada"),
            CodigoTipoUbicacionAfectada = jsonObject.optString("CodigoTipoUbicacionAfectada"),
            CodigoTipoEquipoProteccionManiobra = jsonObject.optString("CodigoTipoEquipoProteccionManiobra"),
            CapacidadCarga = jsonObject.optString("CapacidadCarga"),
            CodigoEquipoProteccionManiobraNodoCircuito = jsonObject.optString("CodigoEquipoProteccionManiobraNodoCircuito"),
            CodigoEquipoProteccionManiobraSED = jsonObject.optString("CodigoEquipoProteccionManiobraSED"),
            CodigoCausaAveriaElectrica = jsonObject.optString("CodigoCausaAveriaElectrica"),
            CodigoAccionSolucionAveria = jsonObject.optString("CodigoAccionSolucionAveria"),
            CodigoTipoSolucionIntervencion = jsonObject.optString("CodigoTipoSolucionIntervencion"),
            CodigoTipoAreaIntervencion = jsonObject.optString("CodigoTipoAreaIntervencion"),
            Enviado = -1
          )
          repository.reclamoInformeOMS_New(reclamoInformeOMSEntity)
        }
      } else {
        Log.e("Mensaje", "Mensaje: "+respuesta.message)
      }
    }
  }
}