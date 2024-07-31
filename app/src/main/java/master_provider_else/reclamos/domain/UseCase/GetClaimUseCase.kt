package master_provider_else.reclamos.domain.UseCase

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import master_provider_else.reclamos.data.QuoteRepository
import master_provider_else.reclamos.data.database.entity.ReclamoEntity
import master_provider_else.reclamos.data.database.entity.toEntity
import master_provider_else.reclamos.data.dto.ReclamoArray
import master_provider_else.reclamos.domain.model.Claim
import master_provider_else.reclamos.utils.isConnected
import javax.inject.Inject

class GetClaimUseCase @Inject constructor(
  private val repository: QuoteRepository
) {
  val arrayReclamo: ArrayList<ReclamoEntity> = ArrayList<ReclamoEntity>()

  suspend fun fetchClaims(
    contentType: String,
    authorization: String,
    strCodigoCuadrilla: String,
    strCodigoEstadoReclamo: String,
    strAP: String,
    context: Context
  ): List<Any> {
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
    }
  }
}