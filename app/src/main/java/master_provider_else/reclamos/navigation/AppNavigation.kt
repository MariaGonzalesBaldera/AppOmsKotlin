package master_provider_else.reclamos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import master_provider_else.reclamos.AppOmsKoltinApp
import master_provider_else.reclamos.ui.theme.view.component.map.ContentMap
import master_provider_else.reclamos.ui.theme.view.screen.AlumbradoDatosCampoFragment
import master_provider_else.reclamos.ui.theme.view.screen.LoginScreen
import master_provider_else.reclamos.ui.theme.view.screen.MenuActivity
import master_provider_else.reclamos.ui.theme.view.screen.ReclamosListaActivity
import master_provider_else.reclamos.ui.theme.viewModel.UserViewModel


@Composable
fun AppNavigation(userViewModel:UserViewModel) {
  val navController = rememberNavController()
  NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
    composable(route = AppScreens.LoginScreen.route) {
      LoginScreen(navController=navController,userViewModel=userViewModel)
    }
    composable(route = AppScreens.MenuActivityScreen.route) {
      MenuActivity(navController=navController,userViewModel=userViewModel)
    }
    composable(route = AppScreens.AppOmsKotlinApp.route) {
      AppOmsKoltinApp()
    }
    composable(route = AppScreens.ReclamosListaActivityScreen.route) {
      ReclamosListaActivity(navController=navController,userViewModel=userViewModel)
    }
    composable(route = AppScreens.AlumbradoDatosCampoFragmentScreen.route) {
      AlumbradoDatosCampoFragment()
    }
    composable(route = AppScreens.ContentMapScreen.route) {
      ContentMap()
    }

  }
}