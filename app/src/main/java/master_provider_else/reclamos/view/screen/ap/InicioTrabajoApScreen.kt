package master_provider_else.reclamos.view.screen.ap

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import master_provider_else.reclamos.BotonDefault
import master_provider_else.reclamos.R
import master_provider_else.reclamos.ui.theme.view.component.BotonDefaultLine
import master_provider_else.reclamos.view.component.TopbarInfomacion

@Preview
@Composable
private fun showPrev() {
  InicioTrabajoApScreen(rememberNavController())
}

@Composable
fun InicioTrabajoApScreen(navController: NavController) {
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

    bottomBar = {
      Column(
        modifier = Modifier
          .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Row(
          modifier = Modifier
            .padding(1.dp)
        ) {
          BotonDefault(
            title = "Finalizar",
            modifier = Modifier
              .width(155.dp)
              .padding(horizontal = 5.dp),
            onClick = {})
          BotonDefaultLine(
            title = "Desestimar",
            modifier = Modifier
              .width(155.dp)
              .padding(horizontal = 5.dp),
            onClick = {})
          Image(
            painter = painterResource(id = R.drawable.pin_verde),
            contentDescription = "Pin color verde",
            modifier = Modifier
              .size(width = 40.dp, height = 50.dp)
          )
        }
      }
    }
  ) { padding ->
    ContentTabAlumbrado(
      navController,
      modifier = Modifier
        .padding(
          padding
        )
    )
  }
}