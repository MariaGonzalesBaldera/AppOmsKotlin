package master_provider_else.reclamos.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import master_provider_else.reclamos.ProgressDialogLoading
import master_provider_else.reclamos.ui.theme.view.component.CardAlumbradoDesestimar
import master_provider_else.reclamos.ui.theme.view.component.CardSelectMaterial
import master_provider_else.reclamos.ui.theme.view.component.ReclamoListaItem
import master_provider_else.reclamos.ui.theme.view.screen.AlumbradoDatosCampoFragment
import master_provider_else.reclamos.ui.theme.view.screen.AlumbradoListaScreen
import master_provider_else.reclamos.ui.theme.view.screen.LoginScreen
import master_provider_else.reclamos.ui.theme.view.screen.MenuActivity
import master_provider_else.reclamos.ui.theme.view.screen.ReclamoListaScreen
import master_provider_else.reclamos.ui.theme.view.screen.ReclamosRegistroInfFichaTecnica
import master_provider_else.reclamos.view.screen.oms.InicioTrabajoOmsScreen
import master_provider_else.reclamos.view.screen.LocationMap
import master_provider_else.reclamos.view.screen.LocationScreen
import master_provider_else.reclamos.view.screen.ap.InicioTrabajoApScreen
import master_provider_else.reclamos.viewModel.ClaimViewModel
import master_provider_else.reclamos.viewModel.UserViewModel


@Composable
fun AppNavigation(userViewModel: UserViewModel, claimViewModel: ClaimViewModel) {
  val navController = rememberNavController()
  var tipo = ""
  NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
    composable(route = AppScreens.LoginScreen.route) {
      LoginScreen(navController = navController, userViewModel = userViewModel)
    }
    composable(route = AppScreens.MenuActivityScreen.route) {
      MenuActivity(navController = navController, claimViewModel = claimViewModel)
    }
    composable(route = AppScreens.LocationMap.route) { backStackEntry ->
      tipo = backStackEntry.arguments?.getString("tipo") ?: ""

      LocationMap(navController, tipo = tipo)
    }
    composable(route = AppScreens.AlumbradoDesestimarCard.route) {
      CardAlumbradoDesestimar()
    }
    composable(route = AppScreens.AlumbradoDatosCampoFragmentScreen.route) {
      AlumbradoDatosCampoFragment()
    }
    composable(route = AppScreens.LocationScreen.route) {
      LocationScreen(navController, tipo = tipo)
    }

//show card
    composable(route = AppScreens.ReclamoListaItemCard.route) {
      ReclamoListaItem(navController)
    }

    composable(route = AppScreens.SelectMaterialCard.route) {
      CardSelectMaterial(onDismiss = {})
    }
    composable(route = AppScreens.ProgressDialogLoadingCard.route) {
      val showProgress by remember { mutableStateOf(false) }
      ProgressDialogLoading({}, showProgress)
    }
    composable(route = AppScreens.ReclamoListaScreen.route) {
      ReclamoListaScreen(navController, userViewModel)
    }
    composable(route = AppScreens.ReclamosRegistroInfFichaTecnicaScreen.route) {
      ReclamosRegistroInfFichaTecnica()
    }
    composable(route = AppScreens.InicioTrabajoOmsScreen.route) {
      InicioTrabajoOmsScreen(navController)
    }
    composable(route = AppScreens.AlumbradoListaScreen.route) {
      AlumbradoListaScreen(navController, userViewModel)
    }
    composable(route = AppScreens.InicioTrabajoApScreen.route) {
      InicioTrabajoApScreen(navController)
    }
  }
}