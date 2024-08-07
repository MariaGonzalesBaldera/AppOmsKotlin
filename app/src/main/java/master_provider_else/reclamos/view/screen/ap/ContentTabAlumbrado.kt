package master_provider_else.reclamos.view.screen.ap

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import master_provider_else.reclamos.R
import master_provider_else.reclamos.ui.theme.view.screen.AlumbradoDatosCampoFragment
import master_provider_else.reclamos.view.component.ComponentMaterial
import master_provider_else.reclamos.view.component.ImageCaptureFromCamera
import master_provider_else.reclamos.view.screen.oms.FotosScreen

@Composable
fun ContentTabAlumbrado(navController:NavController, modifier: Modifier) {
  var tabIndex by remember { mutableIntStateOf(0) }
  val tabs = listOf("Datos campo","Actividades", "Materiales", "Fotos")
  val context = LocalContext.current
  Column(modifier = Modifier.fillMaxSize()) {
    TabRow(
      selectedTabIndex = tabIndex,
      backgroundColor = colorResource(id = R.color.colorPrimary),
      contentColor = Color.White,
      indicator = { tabPositions ->
        TabRowDefaults.apply {
          Divider(
            Modifier
              .fillMaxSize()
              .height(2.dp)
              .padding(horizontal = 16.dp)
              .tabIndicatorOffset(tabPositions[tabIndex]),
            color = MaterialTheme.colors.primary
          )
        }
      },
      divider = {}
    ) {
      tabs.forEachIndexed { index, title ->
        Tab(text = { Text(title) },
          modifier = Modifier.fillMaxWidth(),
          selected = tabIndex == index,
          onClick = { tabIndex = index }
        )
      }
    }
    when (tabIndex) {
      0 -> TabDatos()
      1 -> TabActividades()
      2 -> TabMateriales()
      3 -> FotosScreen()
    }
  }
}
@Composable
fun TabDatos() {
  AlumbradoDatosCampoFragment()
}

@Composable
fun TabActividades() {
  ActividadesNodo()
}

@Composable
fun TabMateriales() {
  ComponentMaterial()
}

@Composable
fun TabFotos() {
  ImageCaptureFromCamera()
}