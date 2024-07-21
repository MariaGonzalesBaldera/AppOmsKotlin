package master_provider_else.reclamos.navigation

sealed class AppScreens(val route: String) {
  object LoginScreen : AppScreens("login_screen")
  object MenuActivityScreen : AppScreens("menu_screen")
  object AppOmsKotlinApp : AppScreens("app_activity")
  object AlumbradoListaScreen : AppScreens("alumbrado_lista_screen")
  object AlumbradoDatosCampoFragmentScreen : AppScreens("alumbrado_datos_campos_screen")
  object LocationScreen : AppScreens("map_screen")
  object ContentCameraScreen : AppScreens("camera_screen")
  object AlumbradoDesestimarCard : AppScreens("alumbrado_desestimar_card")

  //show card
  object ReclamoListaItemCard : AppScreens("reclamo_lista_item_card")
  object SelectMaterialCard : AppScreens("select_material_card")
  object ProgressDialogLoadingCard : AppScreens("progress_dialog_loading_card")

  //show Screen
  object ReclamoListaScreen : AppScreens("reclamo_lista_screen")
  object ReclamosRegistroInfFichaTecnicaScreen :
    AppScreens("reclamos_registro_inf_ficha_tecnica_screen")

  object InicioTrabajoOmsScreen : AppScreens("inicio_trabajo_screen")
  object LocationMap : AppScreens("location_map/{tipo}"){
    fun createRoute(tipo: String) = "location_map/$tipo"
  }
  object ImageCaptureFromCamera : AppScreens("image_capture_from_camera")
  object InicioTrabajoApScreen : AppScreens("Inicio_trabajo_ap_screen")

}