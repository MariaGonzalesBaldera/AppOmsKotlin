package master_provider_else.reclamos.domain.UseCase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import master_provider_else.reclamos.data.QuoteRepository
import master_provider_else.reclamos.data.database.entity.APActividadEntity
import master_provider_else.reclamos.data.database.entity.ReclamoInformeOMSAPNodoActividadEntity
import javax.inject.Inject

class GetActividadesUseCase @Inject constructor(
  private val repository: QuoteRepository
) {
  private val arrayNodos = ArrayList<String>()
  private val codigoReclamoContext: String = ""
  private val cuadrillaContext: String = ""

  suspend fun cargarlistanodos() {
    withContext(Dispatchers.IO) {

    }
  }

  suspend fun obtenerNodos(codigo: String) {
    withContext(Dispatchers.IO){
      val informeOMSAPNodo= repository.informeOMSAPNodo_Get_Codigo_Reclamo(codigo)
      if (informeOMSAPNodo.size > 0) {
        arrayNodos.clear()
        for (i in informeOMSAPNodo.indices) {
          val item = informeOMSAPNodo[i]
          arrayNodos.add(item.Nodo)
        }
        cargarlistanodos()
      }
    }
  }

  suspend fun obtenerFichaNodos(codigoNodo: String) {
    withContext(Dispatchers.IO) {
      val informeOMSAPNodo = repository.informeOMSAPNodo_Get_Nodo(codigoReclamoContext, codigoNodo)
      val actividadesgenerales = repository.apactividad_All()

      if (actividadesgenerales.size > 0) {
        //arrayNodos.clear();
        for (i in actividadesgenerales.indices) {
          val _actividad: APActividadEntity = actividadesgenerales[i]

          var item = repository.reclamoInformeOMSAPNodoActividad_GET(
            codigoReclamoContext,
            _actividad.codigoActividad,
            informeOMSAPNodo.CodigoUbicacionElectrica
          )

          if (item == null) {
            //item.setRealizada(reclamoInformeOMSAPNodoActividad.getRealizada());
            item = ReclamoInformeOMSAPNodoActividadEntity()
            item.CodigoActividad = _actividad.codigoActividad
            item.NombreActividad = _actividad.nombreActividad
            item.Realizada = _actividad.realizada
          }

          item.CodigoUbicacionElectrica = informeOMSAPNodo.CodigoUbicacionElectrica

          //return item
        }
      }
    }
  }

  suspend fun insertarReclamoInformeOMSAPNodoActividad(item: ReclamoInformeOMSAPNodoActividadEntity) {

    val iteminsert: ReclamoInformeOMSAPNodoActividadEntity =
      ReclamoInformeOMSAPNodoActividadEntity()
    iteminsert.CodigoReclamo = codigoReclamoContext
    iteminsert.CodigoCuadrilla = cuadrillaContext
    iteminsert.CodigoUbicacionElectrica = item.CodigoUbicacionElectrica
    iteminsert.CodigoActividad = item.CodigoActividad
    iteminsert.NombreActividad = item.NombreActividad
    iteminsert.Realizada = item.Realizada
    iteminsert.Enviado = item.Enviado

    withContext(Dispatchers.IO) {
      repository.reclamoInformeOMSAPNodoActividad_New(iteminsert)
    }
  }
}