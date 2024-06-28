package master_provider_else.reclamos.navigation

sealed class AppScreens(val route: String) {
  object LoginScreen : AppScreens("login_activity")
  object MenuActivityScreen : AppScreens("menu_activity")
  object AppOmsKotlinApp : AppScreens("app_activity")
  object ReclamosListaActivityScreen : AppScreens("list_activity")
  object AlumbradoDatosCampoFragmentScreen : AppScreens("alumbrado_datos_campos")
  object ContentMapScreen : AppScreens("map_activity")
  object ContentCameraScreen : AppScreens("camera_screen")

}