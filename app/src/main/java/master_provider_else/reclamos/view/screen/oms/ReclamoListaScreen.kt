package master_provider_else.reclamos.ui.theme.view.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import master_provider_else.reclamos.ProgressDialogLoading
import master_provider_else.reclamos.ToolbarTop
import master_provider_else.reclamos.ui.theme.view.component.BotonDefaultLine
import master_provider_else.reclamos.ui.theme.view.component.ContentTabItem
import master_provider_else.reclamos.viewModel.UserViewModel
//oms

@Composable
fun ReclamoListaScreen(
  navController: NavController,
  userViewModel: UserViewModel
) {
  val username = userViewModel.userCredentials.value?.usuario ?: "usuario"
  var showProgress by remember { mutableStateOf(false) }
  Scaffold(
    topBar = { ToolbarTop(title = username) },
    bottomBar = {
      Box(
        contentAlignment = Alignment.BottomEnd, modifier = Modifier
          .fillMaxWidth()
          .padding(8.dp)
      ) {
        BotonDefaultLine(
          modifier = Modifier.width(180.dp),
          title = "Sincronizar",
          onClick = { showProgress = true })

      }
      ProgressDialogLoading({ showProgress = false }, showProgress = showProgress)
    }
  ) { padding ->
    ContentTabItem(
      navController,
      modifier = Modifier
        .padding(padding),
      //    userViewModel
    )
  }
}

