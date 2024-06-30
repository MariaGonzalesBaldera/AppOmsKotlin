package master_provider_else.reclamos.ui.theme.view.component

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import master_provider_else.reclamos.R


@SuppressLint("UnrememberedMutableState")
@Composable
fun ContentTabItem(navController: NavController, modifier: Modifier) {
  var tabIndex by remember { mutableIntStateOf(0) }
  val tabs = listOf("Programado", "Ejecutado")
  val context = LocalContext.current

  @Composable
  fun EjecutadoScreen(context: Context) {
    Column(
      Modifier
        .fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(text = "Programado", fontSize = 25.sp)
      //ObtenerReclamo(context = context, viewModel)
    }
  }

  @Composable
  fun ProgramadoScreen() {
    Column(
      Modifier
        .fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(text = "Ejecutado", fontSize = 25.sp)
    }
  }

  LazyColumn(modifier = Modifier.fillMaxSize()) {
    item {
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
            modifier = Modifier.fillMaxSize(),
            selected = tabIndex == index,
            onClick = { tabIndex = index },
            icon = {
              when (index) {
                0 -> Icon(imageVector = Icons.Default.Home, contentDescription = null)
                1 -> Icon(imageVector = Icons.Default.Settings, contentDescription = null)
              }
            }
          )
        }
      }
      when (tabIndex) {
        0 -> EjecutadoScreen(context)
        1 -> ProgramadoScreen()
      }
    }
  }
}

