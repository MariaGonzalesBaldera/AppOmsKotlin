package master_provider_else.reclamos.domain.UseCase

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import master_provider_else.reclamos.data.QuoteRepository
import master_provider_else.reclamos.data.database.entity.InformeOMSAPNodoEntity
import master_provider_else.reclamos.data.database.entity.ReclamoInformeOMSAPEntity
import master_provider_else.reclamos.data.database.entity.TipoDenunciaEntity
import master_provider_else.reclamos.domain.model.SMTipoDenuncia
import org.json.JSONObject
import java.util.Calendar
import javax.inject.Inject

class GetAlumbradoUseCase @Inject constructor(
  private val repository: QuoteRepository
) {
  //falta darle valor
  private val codigoReclamoContext: String = ""
  private val cuadrillaContext: String = ""

  suspend fun obtenerNodo(): String {
    return withContext(Dispatchers.IO) {
      val informeOMSAPNodo =
        repository.informeOMSAPNodo_Get_Codigo_Reclamo(codigoReclamoContext)
      var cadenanodos = ""

      if (informeOMSAPNodo.size > 0) {
        for (i in informeOMSAPNodo.indices) {
          val item: InformeOMSAPNodoEntity = informeOMSAPNodo[i]

          cadenanodos = if (i + 1 == informeOMSAPNodo.size) cadenanodos + item.Nodo
          else (cadenanodos + item.Nodo).toString() + ","
        }
      }
      cadenanodos
    }
  }

  suspend fun recuperarDataFichaTecnica() {
    withContext(Dispatchers.IO) {
      val _arrayLTipoDenuncia: List<TipoDenunciaEntity> =
        repository.tipoDenuncia_All()
      val tipoDenunciaEntity = arrayOfNulls<SMTipoDenuncia>(_arrayLTipoDenuncia.size)
      for (i in _arrayLTipoDenuncia.indices) {
        val item: TipoDenunciaEntity = _arrayLTipoDenuncia[i]
        val itemadd: SMTipoDenuncia = SMTipoDenuncia()
        itemadd.CodigoTipoDenuncia = item.CodigoTipoDenuncia
        itemadd.NombreTipoDenuncia = item.NombreTipoDenuncia
        itemadd.Simbolo = item.Simbolo
        tipoDenunciaEntity[i] = itemadd
      }

      val tipoDeficiencia =
        repository.tipoDeficiencia_All().toTypedArray()
    }
  }

  suspend fun recuperarFichaTecnica() {
    withContext(Dispatchers.IO) {
      var reclamoInformeOmsap =
        repository.reclamoInformeOMSAP_Get_codigoReclamo(
          codigoReclamoContext,
          cuadrillaContext
        )
      val json = Gson().toJson(reclamoInformeOmsap)

      if (json == "null") {
        reclamoInformeOmsap = ReclamoInformeOMSAPEntity()
        reclamoInformeOmsap.CodigoReclamo = codigoReclamoContext
        reclamoInformeOmsap.CodigoCuadrilla = cuadrillaContext

      } else {
        val joReclamo = JSONObject(json)

      }
    }
  }

  suspend fun insertarfechasInformeOMS(item: ReclamoInformeOMSAPEntity) {
    // Log.e( "insertarfechasInforeOMS", item.Nodo + "/" + getOrdenTrabajo() + "/" + getCodigoTipoDenuncia() )

    withContext(Dispatchers.IO) {
      if (item != null) {
        val inserted = repository.reclamoInformeOMSAP_New(item)
      }
    }
  }

  suspend fun actualizarEstadoReclamo(codigo: String, estado: String) {

    withContext(Dispatchers.IO) {
      val currentTime = Calendar.getInstance().time
      val time = currentTime.time

      val reclamo = repository.reclamo_Get(codigo)

      reclamo.CodigoEstadoReclamo = estado
      reclamo.FechaEjecucionFin = time

      repository.reclamo_Update(reclamo)
    }
  }
}


















