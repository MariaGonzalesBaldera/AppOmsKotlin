package master_provider_else.reclamos.ui.theme.view.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import master_provider_else.reclamos.CardItemContent
import master_provider_else.reclamos.ConfirmExitDialog
import master_provider_else.reclamos.R
import master_provider_else.reclamos.ToolbarTop
import master_provider_else.reclamos.navigation.AppScreens
import master_provider_else.reclamos.ui.theme.viewModel.UserViewModel

@Composable
fun MenuActivity(navController: NavController, userViewModel: UserViewModel) {
  val username = userViewModel.userPreferences.getUserCredentials()?.nombreUsuario ?: "usuario"
  Surface(color = MaterialTheme.colors.background) {
    Scaffold(
      topBar = { ToolbarTop(title = "Bienvenido(a) $username") }
    ) { padding ->
      MenuItem(
        navController,
        modifier = Modifier.padding(padding),
        username = username,
        viewModel = userViewModel
      )
    }
  }
  /*
  Box(
    contentAlignment = Alignment.Center,
  ) {

    Column {
      CardItemContent(
        painterResource(id = R.drawable.emergency_call),
        "OSM",
        "Icono de llamada de emergencia"
      ){
      }
      Spacer(modifier = Modifier.padding(10.dp))
      CardItemContent(
        painterResource(id = R.drawable.street_lamps),
        "Alumbrado Público",
        "Icono de llamada de emergencia"
      ){
      }
    }
  }
  * */
}

@Composable
fun MenuItem(
  navController: NavController,
  modifier: Modifier,
  username: String,
  viewModel: UserViewModel
) {
  var showDialog by remember { mutableStateOf(false) }

  Box(
    contentAlignment = Alignment.Center,
    modifier = modifier.fillMaxSize()
  ) {
    BackHandler(true) {
      showDialog = true
    }
    if (showDialog) {
      ConfirmExitDialog(
        onDismiss = { showDialog = false },
        onConfirm = { android.os.Process.killProcess(android.os.Process.myPid()) }
      )
    }
    Column {
      CardItemContent(
        painterResource(id = R.drawable.emergency_call),
        "OSM",
        "Icono de llamada de emergencia"
      ) {
        navController.navigate(
          route = AppScreens.ReclamosListaActivityScreen.route
        )
      }
      Spacer(modifier = Modifier.padding(10.dp))
      CardItemContent(
        painterResource(id = R.drawable.street_lamps),
        "Alumbrado Público",
        "Icono de llamada de emergencia"
      ) {
        navController.navigate(
          route = AppScreens.AlumbradoDatosCampoFragmentScreen.route
        )
      }
    }
  }

}