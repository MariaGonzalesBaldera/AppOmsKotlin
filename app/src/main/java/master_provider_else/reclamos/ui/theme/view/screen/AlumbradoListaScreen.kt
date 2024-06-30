package master_provider_else.reclamos.ui.theme.view.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import master_provider_else.reclamos.ToolbarTop
import master_provider_else.reclamos.ui.theme.view.component.BottomBar
import master_provider_else.reclamos.ui.theme.view.component.ContentTabItem
import master_provider_else.reclamos.ui.theme.viewModel.UserViewModel


@Composable
fun AlumbradoListaScreen(
  navController: NavController,
  userViewModel: UserViewModel
) {
  val username = userViewModel.userPreferences.getUserCredentials()?.nombreUsuario ?: "usuario"

  Surface(color = MaterialTheme.colors.background) {
    Scaffold(
      topBar = { ToolbarTop(title = username) },

      bottomBar = { BottomBar() }
    ) { padding ->
      ContentTabItem(
        navController,
        modifier = Modifier
          .padding(padding),
        //    userViewModel
      )
    }
  }
}
