package master_provider_else.reclamos.data.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import master_provider_else.reclamos.data.dto.LoginRequest
import master_provider_else.reclamos.data.dto.ApiResponse
import master_provider_else.reclamos.data.dto.ApiResponseArchivoMovil
import master_provider_else.reclamos.data.dto.ApiResponseEncuesta
import master_provider_else.reclamos.data.dto.ApiResponseEstado
import master_provider_else.reclamos.data.dto.ApiResponseFicha
import master_provider_else.reclamos.data.dto.ApiResponseFichaMapa
import master_provider_else.reclamos.data.dto.ApiResponseFichaTecnica
import master_provider_else.reclamos.data.dto.ApiResponseFinTrabajoCompleto
import master_provider_else.reclamos.data.dto.ApiResponseGeneral
import master_provider_else.reclamos.data.dto.ApiResponseGuardarMaterial
import master_provider_else.reclamos.data.dto.ApiResponseInicioTrabajo
import master_provider_else.reclamos.data.dto.ApiResponseMaterial
import master_provider_else.reclamos.data.dto.ApiResponseReclamo
import master_provider_else.reclamos.data.dto.EliminarFotoRequest
import master_provider_else.reclamos.data.dto.EstadoRequest
import master_provider_else.reclamos.data.dto.FinTrabajoCompletoRequest
import master_provider_else.reclamos.data.dto.GuardarMaterialRequest
import master_provider_else.reclamos.data.dto.InicioTrabajoRequest
import master_provider_else.reclamos.data.dto.EncuestaRequest
import master_provider_else.reclamos.data.dto.fotoRequest
import retrofit2.Response
import javax.inject.Inject

class QuoteService @Inject constructor(private val api: QuoteApliClient) {
  ///del api
  suspend fun getLogin(usuario: String, pass: String): Response<ApiResponse> {
    return withContext(Dispatchers.IO) {
      val loginRequest = LoginRequest(usuario, pass);
      val response: Response<ApiResponse> = api.loginUser(loginRequest);
      Log.e("Log QuoteService", response.message().toString())
      response
    }
  }

  suspend fun getReclamo(
    authorization: String,
    strCodigoCuadrilla: String,
    strCodigoEstadoReclamo: String,
    strAP: String
  ): Response<ApiResponseReclamo> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseReclamo> =
        api.reportClaim(
          "application/json; charset=UTF-8",
          authorization,
          strCodigoCuadrilla,
          strCodigoEstadoReclamo,
          strAP
        );
      Log.e("Log QuoteService reclamo", response.message().toString())
      response
    }
  }

  suspend fun getFichaTecnica(
    authorization: String,
    strCodigoReclamo: String,
    strCodigoCuadrilla: String,
  ): Response<ApiResponseFicha> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseFicha> =
        api.recuperarFicha(
          "application/json; charset=UTF-8",
          authorization,
          strCodigoReclamo,
          strCodigoCuadrilla,
        );
      Log.e("Recup.Ficha", "RecuperarFicha" + response.message().toString())
      response
    }
  }

  suspend fun getReclamoInformeMaterial(
    authorization: String,
    strCodigoReclamo: String,
    strCodigoCuadrilla: String,
  ): Response<ApiResponseMaterial> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseMaterial> =
        api.reclamoInformeMaterial(
          "application/json; charset=UTF-8",
          authorization,
          strCodigoReclamo,
          strCodigoCuadrilla,
        );
      Log.e("ReclamoInformeMaterial", "ReclamoInformeMaterial" + response.message().toString())
      response
    }
  }

  suspend fun getReclamoComercialArchivoMovilLeer(
    authorization: String,
    strCodigoReclamo: String,
  ): Response<ApiResponseArchivoMovil> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseArchivoMovil> =
        api.reclamoComercialArchivoMovilLeer(
          "application/json; charset=UTF-8",
          authorization,
          strCodigoReclamo,
        );
      Log.e(
        "ReclamoComercialArchivoMovilLeer",
        "ReclamoComercialArchivoMovilLeer" + response.message().toString()
      )
      response
    }
  }

  suspend fun getEncuesta(
    authorization: String,
  ): Response<ApiResponseEncuesta> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseEncuesta> = api.reclamoEncuesta(
        "application/json; charset=UTF-8",
        authorization
      );
      Log.e("Log Encuesta", "Encuesta" + response.message().toString())
      response
    }
  }

  suspend fun getCambioEstado(
    authorization: String,
    request: EstadoRequest
  ): Response<ApiResponseEstado> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseEstado> = api.cambiarEstado(
        "application/json; charset=UTF-8",
        authorization,
        request
      );
      Log.e("Log QuoteService", response.message().toString())
      response
    }
  }

  suspend fun getFichaTecnica(
    authorization: String
  ): Response<ApiResponseFichaTecnica> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseFichaTecnica> =
        api.getFichaTecnica(
          "application/json; charset=UTF-8",
          authorization
        );
      Log.e("ReclamoInformeMaterial", "ReclamoInformeMaterial" + response.message().toString())
      response
    }
  }

  suspend fun getMapa(
    authorization: String,
    strCodigoCuadrilla: String,
    strAMT: String
  ): Response<ApiResponseFichaMapa> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseFichaMapa> =
        api.getMapa(
          "application/json; charset=UTF-8",
          authorization,
          strCodigoCuadrilla,
          strAMT
        );
      Log.e("getMapa", "getMapa" + response.message().toString())
      response
    }
  }

  suspend fun inicioTrabajo(
    authorization: String,
    request: InicioTrabajoRequest
  ): Response<ApiResponseInicioTrabajo> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseInicioTrabajo> = api.iniciarTrabajo(
        "application/json; charset=UTF-8",
        authorization,
        request
      );
      Log.e("Log Inicio Trabajo", response.message().toString())
      response
    }
  }

  suspend fun finTrabajoCompletoService(
    authorization: String,
    request: FinTrabajoCompletoRequest
  ): Response<ApiResponseFinTrabajoCompleto> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseFinTrabajoCompleto> = api.finTrabajoCompleto(
        "application/json; charset=UTF-8",
        authorization,
        request
      );
      Log.e("Log Fin trabajo", response.message().toString())
      response
    }
  }

  suspend fun guardarInformeMaterialService(
    authorization: String,
    request: GuardarMaterialRequest
  ): Response<ApiResponseGuardarMaterial> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseGuardarMaterial> = api.guardarInformeMaterial(
        "application/json; charset=UTF-8",
        authorization,
        request
      );
      Log.e("Log Guardar material", "service " + response.message().toString())
      response
    }
  }

  suspend fun eliminarInformeMaterialService(
    authorization: String,
    request: GuardarMaterialRequest
  ): Response<ApiResponseGuardarMaterial> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseGuardarMaterial> = api.eliminarInformeMaterial(
        "application/json; charset=UTF-8",
        authorization,
        request
      );
      Log.e("Log Eliminar material", "service " + response.message().toString())
      response
    }
  }

  suspend fun guardarArchivoComercialService(
    authorization: String,
    request: fotoRequest
  ): Response<ApiResponseGeneral> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseGeneral> = api.guardarArchivoComercial(
        "application/json; charset=UTF-8",
        authorization,
        request
      );
      Log.e("Log Archivo comercial", "service " + response.message().toString())
      response
    }
  }

  suspend fun guardarEncuestaService(
    authorization: String,
    request: EncuestaRequest
  ): Response<ApiResponseGeneral> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseGeneral> = api.guardarEncuesta(
        "application/json; charset=UTF-8",
        authorization,
        request
      );
      Log.e("Log Guardar Encuesta", "service " + response.message().toString())
      response
    }
  }

  suspend fun archivoMovilEliminarService(
    authorization: String,
    request: EliminarFotoRequest
  ): Response<ApiResponseGeneral> {
    return withContext(Dispatchers.IO) {
      val response: Response<ApiResponseGeneral> = api.archivoMovilEliminar(
        "application/json; charset=UTF-8",
        authorization,
        request
      );
      Log.e("Log Movil Eliminar", "service " + response.message().toString())
      response
    }
  }
}