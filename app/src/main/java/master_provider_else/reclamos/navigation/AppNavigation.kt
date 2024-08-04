package master_provider_else.reclamos.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import master_provider_else.reclamos.ProgressDialogLoading
import master_provider_else.reclamos.domain.model.ParamMap
import master_provider_else.reclamos.ui.theme.view.component.CardAlumbradoDesestimar
import master_provider_else.reclamos.ui.theme.view.component.CardSelectMaterial
import master_provider_else.reclamos.ui.theme.view.component.ContentTabItem
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
import master_provider_else.reclamos.viewModel.MapaViewModel
import master_provider_else.reclamos.viewModel.UserViewModel


@Composable
fun AppNavigation(
  userViewModel: UserViewModel,
  claimViewModel: ClaimViewModel,
  mapaViewModel: MapaViewModel
) {
  val navController = rememberNavController()
  NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
    composable(route = AppScreens.LoginScreen.route) {
      LoginScreen(navController = navController, userViewModel = userViewModel)
    }
    composable(route = AppScreens.MenuActivityScreen.route) {
      MenuActivity(navController = navController, claimViewModel = claimViewModel)
    }
    composable(route = AppScreens.LocationMap.route) { backStackEntry ->
      val ap = backStackEntry.arguments?.getString("ap") ?: ""
      val estado = backStackEntry.arguments?.getString("estado") ?: ""
      val paramsJson = backStackEntry.arguments?.getString("paramsJson") ?: ""
      val gson = Gson()
      val params = gson.fromJson(paramsJson, ParamMap::class.java)
      LocationScreen(navController = navController,mapaViewModel = mapaViewModel, ap = ap, estado = estado, params = params)
    }


    composable(route = AppScreens.AlumbradoDesestimarCard.route) {
      CardAlumbradoDesestimar()
    }
    composable(route = AppScreens.AlumbradoDatosCampoFragmentScreen.route) {
      AlumbradoDatosCampoFragment()
    }
    composable(route = AppScreens.ContentTabItem.route) {
      ContentTabItem(
        navController = navController,
        modifier = Modifier,
        ap = "",
        claimViewModel = claimViewModel
      )
    }

    composable(route = AppScreens.SelectMaterialCard.route) {
      CardSelectMaterial(onDismiss = {})
    }
    composable(route = AppScreens.ProgressDialogLoadingCard.route) {
      val showProgress by remember { mutableStateOf(false) }
      ProgressDialogLoading({}, showProgress)
    }
    composable(route = AppScreens.ReclamoListaScreen.route) { backStackEntry ->
      val ap = backStackEntry.arguments?.getString("ap") ?: ""
      ReclamoListaScreen(navController, userViewModel, claimViewModel, ap = ap)
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