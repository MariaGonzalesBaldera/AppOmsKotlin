package master_provider_else.reclamos.domain.UseCase

import android.content.Context
import android.util.Log
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import master_provider_else.reclamos.data.QuoteRepository
import master_provider_else.reclamos.data.database.entity.DaoCoordenadasEntity
import master_provider_else.reclamos.data.database.entity.LineasEntity
import master_provider_else.reclamos.data.database.entity.ReclamoEntity
import master_provider_else.reclamos.data.dto.InicioTrabajoRequest
import master_provider_else.reclamos.domain.model.ParamMap
import master_provider_else.reclamos.utils.isConnected
import org.json.JSONArray
import java.util.Calendar
import javax.inject.Inject

class GetMapUseCase @Inject constructor(
  private val repository: QuoteRepository
) {
  var authorizationContext = ""
  var cuadrillaContext = ""
  var codigoReclamoContext = ""
  suspend fun fetchGetPoint(
    authorization: String,
    strCodigoCuadrilla: String,
    context: Context,
    item: ParamMap
  ): Boolean {
    try {
      authorizationContext = authorization
      cuadrillaContext = strCodigoCuadrilla
      codigoReclamoContext = item.codigoReclamo

      if (isConnected(context)) {
        obtenerPuntosGPSServicio(item)
        return true
      } else {
        return false
      }
    } catch (e: Exception) {
      Log.e("API Exception", "Exception: ${e.message}")
      return false
    }
  }

  private suspend fun obtenerPuntosGPSServicio(
    item: ParamMap
  ) {
    val arrayListCoordenadasRedesMt1: ArrayList<DaoCoordenadasEntity> =
      ArrayList<DaoCoordenadasEntity>()
    val arrayListCoordenadasRedesBt1: ArrayList<DaoCoordenadasEntity> =
      ArrayList<DaoCoordenadasEntity>()
    val arrayListLineRedesMt1: ArrayList<LineasEntity> = ArrayList<LineasEntity>()

    try {
      val response = repository.getMapa(
        "Bearer $authorizationContext",
        cuadrillaContext,
        item.codigoDireccionElectrica
      )
      if (response.body()?.respuesta?.error == 0) {
        arrayListCoordenadasRedesMt1.clear()
        arrayListCoordenadasRedesBt1.clear()
        arrayListLineRedesMt1.clear()

        val joUbicacion = JSONArray(response.body()?.respuesta?.body?.Ubicacion)

        if (joUbicacion.length() > 0) {

          for (i in 0 until joUbicacion.length()) {
            val jsonObjectChild = joUbicacion.getJSONObject(i)
            val codigoTipoUbicacion = jsonObjectChild.getString("CodigoTipoUbicacionElectrica")
            val TipoCoordenada = jsonObjectChild.getString("TipoCoordenada")
            val CodigoNTCSE = jsonObjectChild.getString("CodigoNTCSE")
            val CodigoUbicacionElectrica = jsonObjectChild.getString("CodigoUbicacionElectrica")

            val joCoordenadas = JSONArray(jsonObjectChild.getString("Coordenadas"))

            if (TipoCoordenada == "PUNTO") {
              if (joCoordenadas.length() > 0) {
                val jsonObjectChildX = joCoordenadas.getJSONObject(0)
                val LATITUD = jsonObjectChildX.getString("Latitud")
                val LONGITUD = jsonObjectChildX.getString("Longitud")
                val latLng = LatLng(
                  LATITUD.toDouble(),
                  LONGITUD.toDouble()
                )

                if (codigoTipoUbicacion == "8" || codigoTipoUbicacion == "10" || codigoTipoUbicacion == "17") {
                  arrayListCoordenadasRedesMt1.add(
                    DaoCoordenadasEntity(
                      CodigoReclamo = item.codigoReclamo,
                      Latitud = LATITUD,
                      Longitud = LONGITUD,
                      codigoTipoUbicacion = codigoTipoUbicacion,
                      CodigoNTCSE = CodigoNTCSE,
                      tipoLista = "MT",
                      TipoCoordenada = TipoCoordenada
                    )
                  )
                } else if (codigoTipoUbicacion == "7" || codigoTipoUbicacion == "16") {
                  arrayListCoordenadasRedesBt1.add(
                    DaoCoordenadasEntity(
                      CodigoReclamo = item.codigoReclamo,
                      Latitud = LATITUD,
                      Longitud = LONGITUD,
                      codigoTipoUbicacion = codigoTipoUbicacion,
                      CodigoNTCSE = CodigoNTCSE,
                      tipoLista = "BT",
                      TipoCoordenada = TipoCoordenada
                    )
                  )
                }
              }
            } else if (TipoCoordenada == "LINEA") {
              if (joCoordenadas.length() > 0) {
                if (codigoTipoUbicacion == "8" || codigoTipoUbicacion == "10" || codigoTipoUbicacion == "17") {
                  arrayListCoordenadasRedesMt1.add(
                    DaoCoordenadasEntity(
                      CodigoReclamo = item.codigoReclamo,
                      Latitud = "0",
                      Longitud = "0",
                      codigoTipoUbicacion = codigoTipoUbicacion,
                      CodigoNTCSE = CodigoNTCSE,
                      tipoLista = "MT",
                      TipoCoordenada = TipoCoordenada
                    )
                  )
                  for (id in 0 until joCoordenadas.length()) {
                    val jsonObjectChildX = joCoordenadas.getJSONObject(id)
                    val LATITUD = jsonObjectChildX.getString("Latitud")
                    val LONGITUD = jsonObjectChildX.getString("Longitud")
                    arrayListLineRedesMt1.add(
                      LineasEntity(
                        CodigoReclamo = item.codigoReclamo,
                        Latitud = LATITUD,
                        Longitud = LONGITUD,
                        CodigoNTCSE = CodigoNTCSE,
                        CodigoUbicacionElectrica = CodigoUbicacionElectrica
                      )
                    )
                  }
                }
              }
            }
          }
          insertarCoordenadas(arrayListCoordenadasRedesMt1)
          insertarCoordenadas(arrayListCoordenadasRedesBt1)
          insertarLineas(arrayListLineRedesMt1)
        }
      }
    } catch (e: Exception) {
      Log.e("API Exception", "Exception: ${e.message}")
    }
  }

  private suspend fun insertarLineas(arrayL: ArrayList<LineasEntity>) {
    withContext(Dispatchers.IO) {
      repository.insertMultipleLineas(arrayL)
      Log.d("TAG", "run: DaoCoordenadas has been inserted...")

    }
  }

  private suspend fun insertarCoordenadas(arrayL: ArrayList<DaoCoordenadasEntity>) {
    withContext(Dispatchers.IO) {
      repository.insertMultipleDaoCoordenadas(arrayL)
      Log.d("TAG", "run: DaoCoordenadas has been inserted...")

    }
  }


  //INICIAR TRABAJO
  suspend fun fetchIniciarTrabajo(context: Context, usuario: String) {
    try {
      if (isConnected(context)) {
        iniciotrabajolinea(usuario)
      } else {
        InicioTrabajo()
      }
    } catch (e: Exception) {
      Log.e("API Exception", "Exception: ${e.message}")
    }
  }

  suspend fun InicioTrabajo() {
    withContext(Dispatchers.IO) {
      val reclamo: ReclamoEntity = repository.reclamo_Get(codigoReclamoContext)
      reclamo.CodigoEstadoReclamo = "4"

      val currentTime = Calendar.getInstance().time
      val time = currentTime.time
      reclamo.FechaEjecucionInicio = time

      repository.reclamo_Update(reclamo)
    }
  }

  private suspend fun iniciotrabajolinea(usuario: String) {
    try {
      val inicioTrabajoRequest = InicioTrabajoRequest(
        codigoReclamo = codigoReclamoContext,
        codigoCuadrilla = cuadrillaContext,
        loginUsuario = usuario
      )
      val response = repository.InicioTrabajo(authorizationContext, inicioTrabajoRequest)

      if (response.body()?.respuesta?.error != 0) {
        Log.e("Error", response.body()?.respuesta?.message.toString())
      } else {
        InicioTrabajo()
      }
    } catch (e: Exception) {
      Log.e("API Exception", "Exception: ${e.message}")

    }
  }
}






















