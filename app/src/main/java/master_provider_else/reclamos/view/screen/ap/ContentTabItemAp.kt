package master_provider_else.reclamos.view.screen.ap

import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import master_provider_else.reclamos.R

data class Item(
  val id: Int,
  val title: String,
  val description: String
)

@Composable
fun ContentTabItemAp(navController: NavController, modifier: Modifier) {
  var tabIndex by remember { mutableIntStateOf(0) }
  val tabs = listOf("Programado", "Ejecutado")
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
      0 -> ProgramadoScreenAp(navController)
      1 -> EjecutadoScreenAp(context, navController)
    }
  }
}

@Composable
fun EjecutadoScreenAp(context: Context, navController: NavController) {
  val items = listOf(
    Item(id = 1, title = "Código 00004", description = "Detalle del reclamo 4."),
    Item(id = 2, title = "Código 00005", description = "Detalle del reclamo 5."),
    Item(id = 3, title = "Código 00006", description = "Detalle del reclamo 6."),
  )

  ExpandableListAp(
    items = items,
    modifier = Modifier,
    navController
  )
}

@Composable
fun ProgramadoScreenAp(navController: NavController) {
  val items = listOf(
    Item(id = 1, title = "00001", description = "Detalle del reclamo 1."),
    Item(id = 2, title = "00002", description = "Detalle del reclamo 2."),
  )

  ExpandableListAp(
    items = items,
    modifier = Modifier,
    navController = navController
  )
}

@Composable
fun ExpandableListAp(
  items: List<Item>,
  modifier: Modifier = Modifier,
  navController: NavController
) {
  var expandedItemId by remember { mutableStateOf(items.firstOrNull()?.id) }

  LazyColumn(modifier = modifier) {
    items(items) { item ->
      ExpandableListItemAp(
        item = item,
        isExpanded = expandedItemId == item.id,
        onItemClick = { id ->
          expandedItemId = if (expandedItemId == id) null else id
        },
        navController = navController
      )
    }
  }
}
@Composable
fun ExpandableListItemAp(item: Item, isExpanded: Boolean, onItemClick: (Int) -> Unit,navController: NavController) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onItemClick(item.id) }
      .padding(10.dp)
      .animateContentSize()
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
    ) {
      Column(modifier = Modifier.fillMaxWidth()) {
        Row(
          horizontalArrangement = Arrangement.SpaceBetween,
          modifier = Modifier
            .fillMaxWidth()
        ) {
          Text(
            text = "Clase Alumbrado: Nombre Clase Alumbrado", fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = colorResource(id = R.color.colorPrimary)
          )
          Image(
            painter = painterResource(
              id = if (isExpanded) R.drawable.ic_expand_less else R.drawable.ic_expand_more
            ),
            contentDescription = if (isExpanded) "Icono de contraer" else "Icono de expandir"
          )
        }
        if (isExpanded) {
          AlumbradolistaItem(navController)
        }
      }

    }
  }
}
