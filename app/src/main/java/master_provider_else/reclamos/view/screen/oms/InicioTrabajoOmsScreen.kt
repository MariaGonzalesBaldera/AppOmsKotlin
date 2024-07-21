package master_provider_else.reclamos.view.screen.oms

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import master_provider_else.reclamos.ui.theme.view.component.BottomBar
import master_provider_else.reclamos.view.component.TopbarInfomacion

@Composable
fun InicioTrabajoOmsScreen(
  navController: NavController
) {
  val context = LocalContext.current
  Scaffold(
    topBar = {
      TopbarInfomacion(
        context = context,
        titulo = "0028140 POR ALUMBRADO PUBLICO",
        subTitulo = "Ruta:0011703004290 - SED: 0011268 - Celular:99999999",
        phone = "+51999000999"
      )
    },

    bottomBar = { BottomBar(title = "Finalizar trabajo", onClick = {}) }
  ) { padding ->
    ContentTabOms(
      navController,
      modifier = Modifier
        .padding(padding)
    )
  }
}

@Preview
@Composable
private fun ShowPrev() {
  val navController = rememberNavController()
  InicioTrabajoOmsScreen(navController)
}