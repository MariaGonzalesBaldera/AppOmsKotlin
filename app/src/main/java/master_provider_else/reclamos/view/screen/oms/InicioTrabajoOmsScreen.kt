package master_provider_else.reclamos.view.screen.oms

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import master_provider_else.reclamos.domain.model.ParamMap
import master_provider_else.reclamos.ui.theme.view.component.BottomBar
import master_provider_else.reclamos.view.component.TopbarInfomacion

@Composable
fun InicioTrabajoOmsScreen(
  navController: NavController,
  params: ParamMap
) {
  val context = LocalContext.current
  Scaffold(
    topBar = {
      TopbarInfomacion(
        context = context,
        titulo = "0028140 POR ALUMBRADO PUBLICO",
        subTitulo = "Ruta:0011703004290 - SED:${params.codigoSED} - Celular:${params.celular}",
        phone = params.celular
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
  val param=ParamMap(
    0.0,
    0.0,
    "00145655566774",
    "2",
    "nombreClaseReclamo",
    "0010297",
    "0010609002844",
    "999999999",
    "234563"
  )
  InicioTrabajoOmsScreen(navController,param)
}