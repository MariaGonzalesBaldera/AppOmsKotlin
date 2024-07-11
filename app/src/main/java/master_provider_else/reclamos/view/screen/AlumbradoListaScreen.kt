package master_provider_else.reclamos.ui.theme.view.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import master_provider_else.reclamos.ToolbarTop
import master_provider_else.reclamos.ui.theme.view.component.BottomBar
import master_provider_else.reclamos.ui.theme.view.component.ContentTabItem
import master_provider_else.reclamos.viewModel.UserViewModel


@Composable
fun AlumbradoListaScreen(
  navController: NavController,
  userViewModel: UserViewModel
) {
  val username = userViewModel.userPreferences.getUserCredentials()?.nombreUsuario ?: "usuario"

  //Surface(color = MaterialTheme.colors.background) {
  Scaffold(modifier = Modifier.border(1.dp, color = Color.Red),
    topBar = { ToolbarTop(title = "Menu de reclamos") },

    bottomBar = { BottomBar(title = "Sincronizar", onClick = {}) }
  ) { padding ->
    ContentTabItem(
      navController,
      modifier = Modifier
        .padding(padding),
      //    userViewModel
    )
  }
  //}
}
