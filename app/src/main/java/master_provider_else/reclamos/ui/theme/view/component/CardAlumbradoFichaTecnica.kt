package master_provider_else.reclamos.ui.theme.view.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import master_provider_else.reclamos.BotonDefault
import master_provider_else.reclamos.R
import master_provider_else.reclamos.SpinnerText
import master_provider_else.reclamos.ui.theme.toast

@Preview(showBackground = true)
@Composable
fun CardAlumbradoFichaTecnica() {
  var tabIndex by remember { mutableIntStateOf(0) }
  val tabs = listOf("Datos campo", "Actividades", "Materiales", "Fotos")
  val context = LocalContext.current
  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(10.dp)
  ) {
    Column() {
      BotonDefault(
        title = "LLAMAR",
        icon = Icons.Default.Call,
        modifier = Modifier.fillMaxWidth(),
        onClick = {
          CallPhone(context, "+51999000999")
        })
      Spacer(modifier = Modifier.padding(1.dp))
      LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
          TabRow(
            selectedTabIndex = tabIndex,
            modifier = Modifier,
            backgroundColor = colorResource(id = R.color.colorPrimary),
            contentColor = Color.White,
            indicator = { tabPositions ->
              TabRowDefaults.apply {
                Divider(
                  Modifier
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
                modifier = Modifier,
                selected = tabIndex == index,
                onClick = { tabIndex = index }
              )
            }
          }

          when (tabIndex) {
            0 -> TabDatos()
            1 -> TabActividades()
            2 -> TabMateriales()
            3 -> TabFotos()
          }

        }
      }
    }

  }
}

@Composable
fun TabDatos() {
  Column(
    Modifier
      .fillMaxWidth()
      .height(500.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(text = "Datos", fontSize = 25.sp)
  }
}

@Composable
fun TabActividades() {
  Column(
    Modifier
      .fillMaxWidth()
      .height(500.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(text = "Actividades", fontSize = 25.sp)
  }
}

@Composable
fun TabMateriales() {
  Column(
    Modifier
      .fillMaxWidth()
      .height(500.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(text = "Materiales", fontSize = 25.sp)
  }
}

@Composable
fun TabFotos() {
  Column(
    Modifier
      .fillMaxWidth()
      .height(500.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(text = "Fotos", fontSize = 25.sp)
  }
}