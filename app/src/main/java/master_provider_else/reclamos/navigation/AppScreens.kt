package master_provider_else.reclamos.navigation

import com.google.gson.Gson
import master_provider_else.reclamos.domain.model.ParamMap

sealed class AppScreens(val route: String) {
  object LoginScreen : AppScreens("login_screen")
  object MenuActivityScreen : AppScreens("menu_screen")
  object AppOmsKotlinApp : AppScreens("app_activity")
  object AlumbradoListaScreen : AppScreens("alumbrado_lista_screen")
  object AlumbradoDatosCampoFragmentScreen : AppScreens("alumbrado_datos_campos_screen")

  // object LocationScreen : AppScreens("map_screen")
  object ContentCameraScreen : AppScreens("camera_screen")
  object AlumbradoDesestimarCard : AppScreens("alumbrado_desestimar_card")

  //show card
  object ContentTabItem : AppScreens("content_tab_item")
  object SelectMaterialCard : AppScreens("select_material_card")
  object ProgressDialogLoadingCard : AppScreens("progress_dialog_loading_card")

  //show Screen
  object ReclamoListaScreen : AppScreens("reclamo_lista_screen/{ap}") {
    fun createRoute(ap: String) = "reclamo_lista_screen/$ap"
  }

  object ReclamosRegistroInfFichaTecnicaScreen :
    AppScreens("reclamos_registro_inf_ficha_tecnica_screen")

  object InicioTrabajoOmsScreen : AppScreens("inicio_trabajo_screen/{paramsJson}"){
    fun createRoute(params: ParamMap): String {
      val gson = Gson()
      val paramsJson = gson.toJson(params)
      return "inicio_trabajo_screen/$paramsJson"
    }
  }

  object LocationMap : AppScreens("location_map/{ap}/{estado}/{paramsJson}") {
    fun createRoute(ap: String, estado: String, params: ParamMap): String {
      val gson = Gson()
      val paramsJson = gson.toJson(params)
      return "location_map/$ap/$estado/$paramsJson"
    }
  }

  object ImageCaptureFromCamera : AppScreens("image_capture_from_camera")
  object InicioTrabajoApScreen : AppScreens("Inicio_trabajo_ap_screen")

}