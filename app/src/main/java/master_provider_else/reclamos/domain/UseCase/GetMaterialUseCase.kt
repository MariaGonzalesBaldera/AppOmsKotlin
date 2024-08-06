package master_provider_else.reclamos.domain.UseCase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import master_provider_else.reclamos.data.QuoteRepository
import master_provider_else.reclamos.data.database.entity.MaterialEntity
import master_provider_else.reclamos.data.database.entity.gnMaterialEntity
import javax.inject.Inject

class GetMaterialUseCase @Inject constructor(
  private val repository: QuoteRepository
) {
  var materiallista: ArrayList<MaterialEntity> = ArrayList<MaterialEntity>()
  var materiallistagn: java.util.ArrayList<gnMaterialEntity> =
    java.util.ArrayList<gnMaterialEntity>()
  var matsend: MaterialEntity = MaterialEntity()

  private var _cuadrillaContext: String = ""
  private var _codigoReclamoContext: String = ""

  suspend fun getMateriales(cuadrilla: String, codigoReclamo: String): List<gnMaterialEntity> {
    _cuadrillaContext = cuadrilla
    _cuadrillaContext = codigoReclamo
    return withContext(Dispatchers.IO) {
      val result = repository.material_Get_General(codigoReclamo)
      materiallistagn.clear()
      try {
        for (i in result.indices) {
          val item: gnMaterialEntity = result.get(i)
          materiallistagn.add(item)
        }
      } catch (e: java.lang.Exception) {
        e.printStackTrace()
      }
      result
    }

  }

  suspend fun getMaterialesReclamo(
    codigoReclamo: String,
    tipo: Array<String>
  ): List<MaterialEntity> {
    return withContext(Dispatchers.IO) {
      val result = repository.material_Get(codigoReclamo, tipo)
      materiallista.clear()
      try {
        for (i in result.indices) {
          val item: MaterialEntity = result.get(i)
          materiallista.add(item)
        }
      } catch (e: Exception) {
        e.printStackTrace()
      }
      result
    }
  }

  suspend fun ActualizarMaterial(Mat: MaterialEntity, enviado: String) {
    return withContext(Dispatchers.IO) {
      val _item =
        repository.material_Get_CodigoReclamo_CodigoMaterial(_codigoReclamoContext, Mat.CodigoMaterial)
      if (_item != null) {
        _item.CodigoCuadrilla = _cuadrillaContext
        _item.Enviado = enviado
        _item.Unidad = Mat.Unidad
        _item.Cantidad = Mat.Cantidad

        repository.material_Update(_item)
      } else {
        matsend.CodigoCuadrilla = _cuadrillaContext
        matsend.CodigoReclamo = _codigoReclamoContext
        matsend.Enviado = enviado
        repository.material_New(matsend)
      }
      val estados = arrayOfNulls<String>(2)
      estados[0] = "0"
      estados[1] = "1"

      materiallista.clear()

    }
  }
}