package master_provider_else.reclamos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import master_provider_else.reclamos.AppOmsKoltinApp
import master_provider_else.reclamos.ProgressDialogLoading
import master_provider_else.reclamos.ui.theme.view.component.AlumbradoListaItem
import master_provider_else.reclamos.ui.theme.view.component.CardAlumbradoDesestimar
import master_provider_else.reclamos.ui.theme.view.component.CardAlumbradoFichaTecnica
import master_provider_else.reclamos.ui.theme.view.component.CardSelectMaterial
import master_provider_else.reclamos.ui.theme.view.component.ScreenTakePhoto
import master_provider_else.reclamos.ui.theme.view.screen.AlumbradoDatosCampoFragment
import master_provider_else.reclamos.ui.theme.view.screen.LoginScreen
import master_provider_else.reclamos.ui.theme.view.screen.MainMapsActivity
import master_provider_else.reclamos.ui.theme.view.screen.MenuActivity
import master_provider_else.reclamos.ui.theme.view.screen.ReclamoListaScreen
import master_provider_else.reclamos.ui.theme.view.screen.ReclamosRegistroInfFichaTecnica
import master_provider_else.reclamos.ui.theme.view.screen.ShowComponentsScreen
import master_provider_else.reclamos.ui.theme.viewModel.UserViewModel


@Composable
fun AppNavigation(userViewModel: UserViewModel) {
  val navController = rememberNavController()
  NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
    composable(route = AppScreens.LoginScreen.route) {
      LoginScreen(navController = navController, userViewModel = userViewModel)
    }
    composable(route = AppScreens.MenuActivityScreen.route) {
      MenuActivity(navController = navController, userViewModel = userViewModel)
    }
    composable(route = AppScreens.AppOmsKotlinApp.route) {
      AppOmsKoltinApp()
    }
    composable(route = AppScreens.AlumbradoDesestimarCard.route) {
      CardAlumbradoDesestimar()
    }
    composable(route = AppScreens.AlumbradoDatosCampoFragmentScreen.route) {
      AlumbradoDatosCampoFragment()
    }
    composable(route = AppScreens.ContentMapScreen.route) {
      MainMapsActivity()
    }
    composable(route = AppScreens.ContentCameraScreen.route) {
      ScreenTakePhoto()
    }

//show card
    composable(route = AppScreens.AlumbradoListaItemCard.route) {
      AlumbradoListaItem()
    }
    composable(route = AppScreens.AlumbradoFichaTecnicaCard.route) {
      CardAlumbradoFichaTecnica()
    }
    composable(route = AppScreens.SelectMaterialCard.route) {
      CardSelectMaterial()
    }
    composable(route = AppScreens.ProgressDialogLoadingCard.route) {
      ProgressDialogLoading({})
    }
    composable(route = AppScreens.ReclamoListaScreen.route) {
      ReclamoListaScreen(navController,userViewModel)
    }
    composable(route = AppScreens.ReclamosRegistroInfFichaTecnicaScreen.route) {
      ReclamosRegistroInfFichaTecnica()
    }
    composable(route = AppScreens.ShowComponentsScreen.route) {
      ShowComponentsScreen(navController = navController)
    }
  }
}