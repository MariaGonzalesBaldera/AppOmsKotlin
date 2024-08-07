package master_provider_else.reclamos.domain.UseCase

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import master_provider_else.reclamos.data.QuoteRepository
import master_provider_else.reclamos.data.database.entity.FotoEntity
import master_provider_else.reclamos.domain.model.SMPhoto
import org.json.JSONArray
import org.json.JSONException
import javax.inject.Inject

class GetFotoUseCase @Inject constructor(
  private val repository: QuoteRepository
) {
  private val photos: ArrayList<SMPhoto> = arrayListOf()
  private var codigoContext: String = ""
  suspend fun listarFotos(codigoReclamo: String) {
    codigoContext = codigoReclamo
    try {
      withContext(Dispatchers.IO) {
        photos.clear()
        var fotos: List<FotoEntity> = java.util.ArrayList<FotoEntity>()
        val enviados = arrayOfNulls<String>(2)
        enviados[0] = "0"
        enviados[1] = "1"

        fotos = repository.foto_Get_CodigoReclamo(codigoReclamo, enviados)

        try {
          val json = Gson().toJson(fotos)

          val joReclamo = JSONArray(json)
          if (joReclamo.length() > 0) {
            for (i in 0 until joReclamo.length()) {
              val jsonObjectChild = joReclamo.getJSONObject(i)
              val smPhoto = SMPhoto()
              smPhoto.ItemID = jsonObjectChild.getString("ItemID")
              smPhoto.base64Source = jsonObjectChild.getString("base64Source")
              photos.add(smPhoto)
            }
          } else {
            Log.e("Lista vacia", "NO hay fotos para mostrar")
          }
        } catch (ex: JSONException) {
          Log.e("DB Exception", "Exception: ${ex.message}")
        }
      }
    } catch (e: Exception) {
      Log.e("DB Exception", "Exception: ${e.message}")
    }
  }

  suspend fun eliminarFoto(_codigo: String) {
    val foto: FotoEntity = FotoEntity()
    foto.ItemID = _codigo
    foto.NombreArchivo = "Android$codigoContext-"
    foto.Extension = "jpg"
    foto.base64Source = ""
    foto.CodigoReclamo = codigoContext
    foto.Enviado = "-1"

    return withContext(Dispatchers.IO) {
      repository.fotos_New(foto)
    }
  }

  suspend fun nuevaFoto(paramsFoto: HashMap<String?, String?>?) {
    val foto: FotoEntity = FotoEntity()
    foto.ItemID = paramsFoto?.get("ItemID") ?: ""
    foto.NombreArchivo = "Android$codigoContext-"
    foto.Extension = "jpg"
    foto.base64Source = paramsFoto?.get("Itemimage") ?: ""
    foto.CodigoReclamo = codigoContext
    foto.Enviado = "0"

    if (paramsFoto?.isNotEmpty() == true) {
      return withContext(Dispatchers.IO) {
        repository.fotos_New(foto)
        try {
          listarFotos(codigoContext)
        } catch (e: InterruptedException) {
          e.printStackTrace()
        }
      }
    }
  }
}









