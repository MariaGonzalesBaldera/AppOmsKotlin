package master_provider_else.reclamos.domain.UseCase

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import master_provider_else.reclamos.data.QuoteRepository
import master_provider_else.reclamos.data.database.entity.CausaAveriaEntity
import master_provider_else.reclamos.data.database.entity.EncuestaEntity
import master_provider_else.reclamos.data.database.entity.FotoEntity
import master_provider_else.reclamos.data.database.entity.MaterialEntity
import master_provider_else.reclamos.data.database.entity.PreguntaEntity
import master_provider_else.reclamos.data.database.entity.ReclamoEntity
import master_provider_else.reclamos.data.database.entity.ReclamoInformeOMSEntity
import master_provider_else.reclamos.data.database.entity.SolucionAveriaEntity
import master_provider_else.reclamos.data.database.entity.SolucionInterrupcionEntity
import master_provider_else.reclamos.data.database.entity.TipoAreaIntervencionEntity
import master_provider_else.reclamos.data.database.entity.TipoDenunciaEntity
import master_provider_else.reclamos.data.database.entity.TipoEquipoProteccionManiobraEntity
import master_provider_else.reclamos.data.database.entity.TipoInstalacionAfectadaEntity
import master_provider_else.reclamos.data.database.entity.TipoInstalacionElectricaAfectadaEntity
import master_provider_else.reclamos.data.database.entity.TipoManiobraCapacidadEntity
import master_provider_else.reclamos.data.database.entity.gnMaterialEntity
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
  private var authorizationContext: String = ""
  suspend fun fetchClaims(
    authorization: String,
    strCodigoCuadrilla: String,
    strCodigoEstadoReclamo: String,
    strAP: String,
    context: Context
  ): List<Any> {

    cuadrilla = strCodigoCuadrilla
    authorizationContext = authorization
    return if (isConnected(context)) {
      val response = repository.getClaimFromApi(
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
          getServicioEncuesta()
          descargarFichaTecnica()
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

  private suspend fun descargarFichaTecnica() {
    try {
      val response = repository.getFichaTecnica(authorizationContext)
      if (response.body()?.respuesta?.error != 0) {
        Log.e("Log error", response.body()?.respuesta?.message.toString())
      } else {
        val joFichaTecnica = JSONObject(response.body()?.respuesta?.body?.fichaTecnica)

        if (joFichaTecnica.length() > 0) {

          var jaTipoTabla = joFichaTecnica.getJSONArray("TipoDenuncia")

          if (jaTipoTabla.length() > 0) {

            val mt: ArrayList<TipoDenunciaEntity> = ArrayList<TipoDenunciaEntity>()
            //val mt: ArrayList<MaterialEntity> = ArrayList<MaterialEntity>()

            for (i in 0 until jaTipoTabla.length()) {
              val jsonObjectChild: JSONObject = jaTipoTabla.getJSONObject(i)
              val smTipoDenuncia: TipoDenunciaEntity = TipoDenunciaEntity()
              smTipoDenuncia.CodigoTipoDenuncia = jsonObjectChild.getString("CodigoTipoDenuncia")
              smTipoDenuncia.NombreTipoDenuncia = jsonObjectChild.getString("NombreTipoDenuncia")
              smTipoDenuncia.Simbolo = jsonObjectChild.getString("Simbolo")

              mt.add(smTipoDenuncia)
            }
            withContext(Dispatchers.IO) {
              repository.insertMultipleTipoDenuncia(mt)
            }
          }
          //CONTINUO
          jaTipoTabla = joFichaTecnica.getJSONArray("TipoInstalacionElectricaAfectada")
          if (jaTipoTabla.length() > 0) {
            val tipoInstalacionElectricaAfectada: MutableList<TipoInstalacionElectricaAfectadaEntity> =
              mutableListOf()
            for (i in 0 until jaTipoTabla.length()) {
              val jsonObjectChild = jaTipoTabla.getJSONObject(i)

              val smTipoInstalacionElectricaAfectada = TipoInstalacionElectricaAfectadaEntity(
                CodigoTipoInstalacionElectricaAfectada = jsonObjectChild.getString("CodigoTipoInstalacionElectricaAfectada"),
                NombreTipoInstalacionElectricaAfectada = jsonObjectChild.getString("NombreTipoInstalacionElectricaAfectada"),
                Simbolo = jsonObjectChild.getString("Simbolo")
              )
              tipoInstalacionElectricaAfectada.add(smTipoInstalacionElectricaAfectada)
            }
            withContext(Dispatchers.IO) {
              repository.insertMultipleTipoInstalacionElectricaAfectada(
                tipoInstalacionElectricaAfectada
              )
            }

          }

          jaTipoTabla = joFichaTecnica.getJSONArray("TipoInstalacionAfectada")
          if (jaTipoTabla.length() > 0) {
            val tipoInstalacionAfectada: MutableList<TipoInstalacionAfectadaEntity> =
              mutableListOf()
            for (i in 0 until jaTipoTabla.length()) {
              val jsonObjectChild = jaTipoTabla.getJSONObject(i)

              val smTipoInstalacionAfectada = TipoInstalacionAfectadaEntity(
                CodigoTipoUbicacionElectrica = jsonObjectChild.getString("CodigoTipoUbicacionElectrica"),
                NombreTipoUbicacionElectrica = jsonObjectChild.getString("NombreTipoUbicacionElectrica"),
                Simbolo = jsonObjectChild.getString("Simbolo"),
                CodigoNTCSE = jsonObjectChild.getString("CodigoNTCSE")
              )
              tipoInstalacionAfectada.add(smTipoInstalacionAfectada)
            }
            withContext(Dispatchers.IO) {
              repository.insertMultipleTipoInstalacionAfectada(tipoInstalacionAfectada)
            }
          }

          jaTipoTabla = joFichaTecnica.getJSONArray("TipoEquipoProteccionManiobra")
          if (jaTipoTabla.length() > 0) {

            val tipoEquipoProteccionManiobra: MutableList<TipoEquipoProteccionManiobraEntity> =
              mutableListOf()
            for (i in 0 until jaTipoTabla.length()) {
              val jsonObjectChild = jaTipoTabla.getJSONObject(i)

              val smTipoEquipoProteccionManiobra = TipoEquipoProteccionManiobraEntity(
                codigoTipoEquipoProteccionManiobra = jsonObjectChild.getString(
                  "CodigoTipoEquipoProteccionManiobra"
                ),
                nombreTipoEquipoProteccionManiobra = jsonObjectChild.getString(
                  "NombreTipoEquipoProteccionManiobra"
                ),
                codigoTipoInstalacionElectricaAfectada = jsonObjectChild.getString("CodigoTipoInstalacionElectricaAfectada"),
                simbolo = jsonObjectChild.getString("Simbolo"),
              )
              tipoEquipoProteccionManiobra.add(smTipoEquipoProteccionManiobra)
            }

            withContext(Dispatchers.IO) {
              repository.insertMultipleTipoEquipoProteccionManiobra(
                tipoEquipoProteccionManiobra
              )
            }
          }

          jaTipoTabla = joFichaTecnica.getJSONArray("TipoManiobraCapacidad")
          if (jaTipoTabla.length() > 0) {
            val tipoManiobraCapacidad: MutableList<TipoManiobraCapacidadEntity> = mutableListOf()

            for (i in 0 until jaTipoTabla.length()) {
              val jsonObjectChild = jaTipoTabla.getJSONObject(i)

              val smTipoManiobraCapacidad = TipoManiobraCapacidadEntity(
                CodigoTipoManiobraCapacidad = jsonObjectChild.getString("CodigoTipoManiobraCapacidad"),
                CodigoTipoEquipoProteccionManiobra = jsonObjectChild.getString(
                  "CodigoTipoEquipoProteccionManiobra"
                ),
                NombreTipoManiobraCapacidad = jsonObjectChild.getString("NombreTipoManiobraCapacidad")
              )

              tipoManiobraCapacidad.add(smTipoManiobraCapacidad)
            }
            withContext(Dispatchers.IO) {
              repository.insertMultipleTipoManiobraCapacidad(
                tipoManiobraCapacidad
              )
            }
          }

          jaTipoTabla = joFichaTecnica.getJSONArray("CausaAveria")
          if (jaTipoTabla.length() > 0) {
            val causaAveria: MutableList<CausaAveriaEntity> = mutableListOf()
            for (i in 0 until jaTipoTabla.length()) {
              val jsonObjectChild = jaTipoTabla.getJSONObject(i)

              val smCausaAveria = CausaAveriaEntity(
                codigoCausaAveria = jsonObjectChild.getString("CodigoCausaAveria"),
                nombreCausaAveriaElectrica = jsonObjectChild.getString("NombreCausaAveriaElectrica"),
                codigoTipoInstalacionElectricaAfectada = jsonObjectChild.getString("CodigoTipoInstalacionElectricaAfectada"),
                codigoTipoDenuncia = jsonObjectChild.getString("CodigoTipoDenuncia"),
                codigoTipoInstalacionAfectada = jsonObjectChild.getString("CodigoTipoInstalacionAfectada")
              )


              causaAveria.add(smCausaAveria)
            }
            withContext(Dispatchers.IO) {
              repository.insertMultipleCausaAveria(causaAveria)
            }
          }


          jaTipoTabla = joFichaTecnica.getJSONArray("SolucionAveria")
          if (jaTipoTabla.length() > 0) {
            val solucionAveria: MutableList<SolucionAveriaEntity> = mutableListOf()

            for (i in 0 until jaTipoTabla.length()) {
              val jsonObjectChild = jaTipoTabla.getJSONObject(i)

              val smSolucionAveria = SolucionAveriaEntity(
                CodigoAccionSolucionAveria = jsonObjectChild.getString("CodigoAccionSolucionAveria"),
                NombreAccionSolucionAveria = jsonObjectChild.getString("NombreAccionSolucionAveria"),
                CodigoCausaAveria = jsonObjectChild.getString("CodigoCausaAveria"),
                Activo = jsonObjectChild.getString("Activo")
              )
              solucionAveria.add(smSolucionAveria)
            }
            withContext(Dispatchers.IO) {
              repository.insertMultipleSolucionAveria(solucionAveria)
            }
          }

          jaTipoTabla = joFichaTecnica.getJSONArray("TipoSolucionInterrupcion")
          if (jaTipoTabla.length() > 0) {
            val solucionInterrupcion: MutableList<SolucionInterrupcionEntity> = mutableListOf()

            //creamos el campo vacio
            var smSolucionInterrupcion = SolucionInterrupcionEntity(
              CodigoTipoSolucionInterrupcion = "0",
              NombreTipoSolucionInterrupcion = "",
              Simbolo = ""
            )

            solucionInterrupcion.add(smSolucionInterrupcion)
            for (i in 0 until jaTipoTabla.length()) {
              val jsonObjectChild = jaTipoTabla.getJSONObject(i)
              smSolucionInterrupcion = SolucionInterrupcionEntity(
                CodigoTipoSolucionInterrupcion = jsonObjectChild.getString("CodigoTipoSolucionInterrupcion"),
                NombreTipoSolucionInterrupcion = jsonObjectChild.getString("NombreTipoSolucionInterrupcion"),
                Simbolo = jsonObjectChild.getString("Simbolo")
              )
              solucionInterrupcion.add(smSolucionInterrupcion)
            }
            withContext(Dispatchers.IO) {
              repository.insertMultipleSolucionInterrupcion(solucionInterrupcion)
            }
          }

          jaTipoTabla = joFichaTecnica.getJSONArray("TipoAreaIntervencion")
          if (jaTipoTabla.length() > 0) {
            val tipoAreaIntervencion: MutableList<TipoAreaIntervencionEntity> = mutableListOf()

            var smTipoAreaIntervencion = TipoAreaIntervencionEntity(
              CodigoTipoAreaIntervencion = "0",
              NombreTipoAreaIntervencion = ""
            )
            tipoAreaIntervencion.add(smTipoAreaIntervencion)

            for (i in 0 until jaTipoTabla.length()) {
              val jsonObjectChild = jaTipoTabla.getJSONObject(i)

              smTipoAreaIntervencion = TipoAreaIntervencionEntity(
                CodigoTipoAreaIntervencion = jsonObjectChild.getString("CodigoTipoAreaIntervencion"),
                NombreTipoAreaIntervencion = jsonObjectChild.getString("NombreTipoAreaIntervencion")
              )
              tipoAreaIntervencion.add(smTipoAreaIntervencion)
            }
            withContext(Dispatchers.IO) {
              repository.insertMultipleTipoAreaIntervencion(tipoAreaIntervencion)
            }
          }


          //endregion
          jaTipoTabla = joFichaTecnica.getJSONArray("OMSMaterial")
          if (jaTipoTabla.length() > 0) {
            if (jaTipoTabla.length() > 0) {
              val mt: MutableList<gnMaterialEntity> = mutableListOf()

              for (i in 0 until jaTipoTabla.length()) {
                val jsonObjectChild = jaTipoTabla.getJSONObject(i)
                val item = gnMaterialEntity(
                  CodigoMaterial = jsonObjectChild.getString("CodigoMaterial"),
                  NombreMaterial = jsonObjectChild.getString("NombreMaterial"),
                  CodigoMaterialSAP = jsonObjectChild.getString("CodigoMaterialSAP"),
                  TipoReclamo = jsonObjectChild.getString("TipoReclamo")
                )
                mt.add(item)
              }
              withContext(Dispatchers.IO) {
                repository.insertMultipleMaterialesGeneral(mt)
              }
            }
          }
        }
      }

    } catch (e: Exception) {
      Log.e("API Exception", "Exception: ${e.message}")
    }
  }

  private val arrayListPregunta: java.util.ArrayList<PreguntaEntity> =
    java.util.ArrayList<PreguntaEntity>()
  private val arrayListEncuesta: java.util.ArrayList<EncuestaEntity> =
    java.util.ArrayList<EncuestaEntity>()

  private suspend fun getServicioEncuesta() {
    try {
      val response =
        repository.getEncuesta(
          "Bearer $authorizationContext"
        )
      if (response.isSuccessful) {
        if (response.body()?.respuesta?.error != 0) {
          Log.e("Log error", response.body()?.respuesta?.message.toString())
        } else {
          val joEncuesta: JSONArray = JSONArray(response.body()?.respuesta?.body?.encuesta)

          for (i in 0 until joEncuesta.length()) {
            val jsonObjectChildEncuesta = joEncuesta.getJSONObject(i)
            val joPregunta = JSONArray(jsonObjectChildEncuesta.getString("Pregunta"))

            arrayListEncuesta.add(EncuestaEntity(i, "", i.toString()))

            val arrayListPosition = java.util.ArrayList<Int>()


            for (j in 0 until joPregunta.length()) {
              val jsonObjectChild = joPregunta.getJSONObject(j)
              val CodigoEncuesta = jsonObjectChild.getString("CodigoEncuesta")
              val NombreEncuesta = jsonObjectChild.getString("NombreEncuesta")
              val CodigoPregunta =
                jsonObjectChild.getString("CodigoPregunta") // 1, 2 cantida de preguntas
              val NombrePregunta = jsonObjectChild.getString("NombrePregunta")
              val CodigoTipoRespuesta =
                jsonObjectChild.getString("CodigoTipoRespuesta") // 3, 2 cantidad de respuestas
              val NombreTipoRespuesta = jsonObjectChild.getString("NombreTipoRespuesta")
              val CodigoAlternativa = jsonObjectChild.getString("CodigoAlternativa")
              val Valor = jsonObjectChild.getString("Valor")

              arrayListPregunta.add(
                PreguntaEntity(
                  joEncuesta.length(),
                  CodigoEncuesta,
                  NombreEncuesta,
                  CodigoPregunta,
                  NombrePregunta,
                  CodigoTipoRespuesta,
                  NombreTipoRespuesta,
                  CodigoAlternativa,
                  Valor
                )
              )
              Log.e("TAG", "Codigo encuesta: $NombrePregunta")
              Log.e("TAG", "Valor: $Valor")

              arrayListPosition.add(arrayListPregunta.size - 1)
            }

            arrayListPosition.clear()
          }

          //base de datoss
          withContext(Dispatchers.IO) {
            repository.delete_All_Encuesta()
            repository.delete_All_Pregunta()
            repository.insertMultipleEncuesta(arrayListEncuesta)
            repository.insertMultiplePregunta(arrayListPregunta)
          }


        }
      } else {
        Log.e("API Error", "HTTP error: ${response.code()} ${response.message()}")
      }
    } catch (e: Exception) {
      Log.e("API Exception", "Exception: ${e.message}")

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
          ObtenerMaterialReclamo(item.CodigoReclamo)
          insertarFotos(item.CodigoReclamo, arrayReclamo.size)
        }
      }
    }
  }

  // obtiene foto(s) del reclamo y lo almacena en local
  private suspend fun insertarFotos(codigoReclamo: String, size: Int) {
    val photos: ArrayList<FotoEntity> = ArrayList<FotoEntity>()

    try {
      val response = repository.getReclamoComercialArchivoMovilLeer(
        authorizationContext,
        codigoReclamo
      )
      if (response.isSuccessful) {
        if (response.body()?.respuesta?.error != 0) {
          Log.e("Log error", response.body()?.respuesta?.message.toString())
        } else {
          val body = response.body()?.respuesta?.body
          val fotosReclamo = body?.fotosReclamo
          if (!fotosReclamo.isNullOrEmpty()) {
            fotosReclamo.forEach { fotoReclamo ->
              val fotoEntity = FotoEntity(
                CodigoReclamo = codigoReclamo,
                ItemID = fotoReclamo.itemID,
                base64Source = fotoReclamo.base64Source,
                Enviado = "0"
              )
              photos.add(fotoEntity)
            }
          }
          withContext(Dispatchers.IO) {
            repository.insertMultipleFotos(photos)
          }
          Log.e("Log error", response.body()?.respuesta?.message.toString())

        }
      } else {
        Log.e("API Error", "HTTP error: ${response.code()} ${response.message()}")
      }

    } catch (e: Exception) {
      Log.e("API Exception", "Exception: ${e.message}")
    }
  }

  private suspend fun ObtenerMaterialReclamo(codigoReclamo: String) {
    val response = repository.getReclamoInformeMaterial(
      authorization = authorizationContext,
      strCodigoReclamo = codigoReclamo,
      strCodigoCuadrilla = cuadrilla
    )
    try {
      if (response.isSuccessful) {
        val responseBody = response.body()
        responseBody?.respuesta?.body?.smMaterial?.let { smMaterial ->
          if (smMaterial.isNotEmpty()) {

            val jarray = JSONArray(responseBody.respuesta.body.smMaterial)
            val mt: java.util.ArrayList<MaterialEntity> = java.util.ArrayList<MaterialEntity>()

            for (i in 0 until jarray.length()) {
              val jsonObjectChild = jarray.getJSONObject(i)
              val item: MaterialEntity = MaterialEntity(
                CodigoReclamo = jsonObjectChild.getString("CodigoReclamo"),
                CodigoCuadrilla = jsonObjectChild.getString("CodigoCuadrilla"),
                CodigoMaterial = jsonObjectChild.getString("CodigoMaterial"),
                NombreMaterial = jsonObjectChild.getString("NombreMaterial"),
                Unidad = jsonObjectChild.getString("Unidad"),
                Cantidad = jsonObjectChild.getString("CantidadUtilizada"),
                CodigoMaterialSAP = jsonObjectChild.getString("CodigoMaterialSAP"),
                Tipo = jsonObjectChild.getString("tipo_listareclamo"),
                Enviado = "0"
              )
              mt.add(item)
            }
            withContext(Dispatchers.IO) {
              repository.insertMultipleMateriales(mt)
            }
          } else {
            Log.d("API Response", "La lista smMaterial esta vacía.")
          }
        }
      }
    } catch (e: Exception) {
      Log.e("API Exception", "Exception: ${e.message}")
    }
  }

  private suspend fun descargarDatosFichaTecnica(codigoReclamo: String) {
    withContext(Dispatchers.IO) {
      val fichaResponse =
        repository.getFichaApi(
          "Bearer $authorizationContext",
          codigoReclamo,
          cuadrilla
        )
      val respuesta = fichaResponse.body()?.respuesta
      if (respuesta?.error != 0) {
        Log.e("Mensaje", "Mensaje: " + respuesta?.message)

      } else {
        respuesta.body.let { body ->
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
      }
    }
  }
}