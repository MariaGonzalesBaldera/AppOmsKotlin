package master_provider_else.reclamos.ui.theme.view.screen

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import master_provider_else.reclamos.BotonDefault
import master_provider_else.reclamos.navigation.AppScreens
import master_provider_else.reclamos.ui.theme.view.component.BotonDefaultLine
import master_provider_else.reclamos.ui.theme.view.component.ScreenTakePhoto

@Composable
fun ShowComponentsScreen(navController: NavController) {
  val context = LocalContext.current
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .fillMaxSize()
      .padding(50.dp)
  ) {
    Text(text = "COMPONENTES ", fontSize = 25.sp, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.padding(8.dp))
    BotonDefault(title = "AlumbradoListaItem", onClick = {
      navController.navigate(route = AppScreens.AlumbradoListaItemCard.route)
    })
    Spacer(modifier = Modifier.padding(8.dp))
    BotonDefaultLine(title = "CardAlumbradoFichaTecnica", onClick = {
      navController.navigate(route = AppScreens.AlumbradoFichaTecnicaCard.route)
    })
    Spacer(modifier = Modifier.padding(8.dp))
    BotonDefault(title = "CardSelectMaterial", onClick = {
      navController.navigate(route = AppScreens.SelectMaterialCard.route)
    })
    Spacer(modifier = Modifier.padding(8.dp))
    BotonDefaultLine(title = "ReclamoListaScreen", onClick = {
      navController.navigate(route = AppScreens.ReclamoListaScreen.route)
    })
    Spacer(modifier = Modifier.padding(8.dp))
    BotonDefault(title = "ReclamosRegistroInfFichaTecnica", onClick = {
      navController.navigate(route = AppScreens.ReclamosRegistroInfFichaTecnicaScreen.route)
    })
    Spacer(modifier = Modifier.padding(8.dp))
    BotonDefaultLine(title = "AlumbradoDesestimarCard", onClick = {
      navController.navigate(
        route = AppScreens.AlumbradoDesestimarCard.route
      )
    })
    Spacer(modifier = Modifier.padding(8.dp))
    BotonDefault(title = "AlumbradoDatosCampoFragment", onClick = {
      navController.navigate(
        route = AppScreens.AlumbradoDatosCampoFragmentScreen.route
      )
    })
    Spacer(modifier = Modifier.padding(8.dp))
    BotonDefaultLine(title = "ProgressDialogLoading", onClick = {
      navController.navigate(route = AppScreens.ProgressDialogLoadingCard.route)
    })
    Spacer(modifier = Modifier.padding(8.dp))
    BotonDefault(title = "Ir a mapa", onClick = {
      val intent = Intent(context, MainMapsActivity::class.java)
      context.startActivity(intent)
    })
    Spacer(modifier = Modifier.padding(8.dp))
    BotonDefaultLine(title = "Tomar foto", onClick = {
      val intent = Intent(context, ScreenTakePhoto::class.java)
      context.startActivity(intent)
    })
  }
}

@Preview(showBackground = true)
@Composable
private fun ShowNav() {
  val navController = rememberNavController()
  ShowComponentsScreen(navController)
}