package master_provider_else.reclamos.ui.theme.view.component

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import master_provider_else.reclamos.R

data class Item(
  val id: Int,
  val title: String,
  val description: String
)

@Composable
fun ExpandableListItem(item: Item, isExpanded: Boolean, onItemClick: (Int) -> Unit) {
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
          AlumbradoListaItem()
        }
      }

    }
  }
}

@Composable
fun ExpandableList(items: List<Item>, modifier: Modifier) {
  var expandedItemId by remember { mutableStateOf<Int?>(null) }

  LazyColumn {
    items(items) { item ->
      ExpandableListItem(
        item = item,
        isExpanded = expandedItemId == item.id,
        onItemClick = { id ->
          expandedItemId = if (expandedItemId == id) null else id
        }
      )
    }
  }
}

@Composable
fun ProgramadoScreen() {
  val items = listOf(
    Item(id = 1, title = "00001", description = "Detalle del reclamo 1."),
    Item(id = 2, title = "00002", description = "Detalle del reclamo 2."),
  )

  ExpandableList(
    items = items,
    modifier = Modifier
  )
}

@Composable
fun EjecutadoScreen(context: Context) {
  val items = listOf(
    Item(id = 1, title = "Código 00004", description = "Detalle del reclamo 4."),
    Item(id = 2, title = "Código 00005", description = "Detalle del reclamo 5."),
    Item(id = 3, title = "Código 00006", description = "Detalle del reclamo 6."),
  )

  ExpandableList(
    items = items,
    modifier = Modifier
  )
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ContentTabItem(navController: NavController, modifier: Modifier) {
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
      0 -> ProgramadoScreen()
      1 -> EjecutadoScreen(context)
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun ShowTabItem() {
  val navController = rememberNavController()
  ContentTabItem(navController = navController, modifier = Modifier)
}