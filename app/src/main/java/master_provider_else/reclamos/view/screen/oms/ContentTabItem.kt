package master_provider_else.reclamos.ui.theme.view.component

import android.annotation.SuppressLint
import android.content.Context
import androidx.activity.viewModels
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.ViewModelLifecycle
import master_provider_else.reclamos.R
import master_provider_else.reclamos.data.database.entity.ReclamoEntity
import master_provider_else.reclamos.data.dto.ReclamoArray
import master_provider_else.reclamos.navigation.AppScreens
import master_provider_else.reclamos.utils.formatDateTime
import master_provider_else.reclamos.view.ui.theme.toast
import master_provider_else.reclamos.viewModel.ClaimViewModel

@Composable
fun ExpandableList(
  items: List<ReclamoEntity>,
  modifier: Modifier = Modifier,
  navController: NavController,
  ap: String,
  estado: String,
  claimViewModel: ClaimViewModel
) {
  var expandedItemId by remember { mutableStateOf(items.firstOrNull()?.CodigoReclamo) }

  LazyColumn(modifier = modifier) {
    items(items) { item ->
      ReclamoListaItem(
        item = item,
        isExpanded = expandedItemId == item.CodigoReclamo,
        onItemClick = { id ->
          expandedItemId = if (expandedItemId == id) null else id
        },
        navController = navController,
        ap,
        estado,
        claimViewModel = claimViewModel
      )
    }
  }
}

@Composable
fun ProgramadoScreen(
  context: Context,
  navController: NavController,
  ap: String,
  claimViewModel: ClaimViewModel
) {
  val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
  var items by remember { mutableStateOf(listOf<ReclamoEntity>()) }
  //llama al api
  LaunchedEffect(Unit) {
    claimViewModel.onClaimView(ap, context, "2")
  }

  /*
  * claimViewModel.programadoReclamos.observe(lifecycleOwner) { value ->
    items = value.map { reclamo ->
      ReclamoEntity(
        CodigoSuministro = reclamo.codigoSuministro,
        CodigoSED = reclamo.codigoSED,
        CodigoEstadoReclamo = reclamo.codigoEstadoReclamo,
        DireccionElectrica = reclamo.direccionElectrica,
        NombreSuministro = reclamo.nombreSuministros,
        CodigoReclamo = reclamo.codigoReclamo,
        NombreClaseReclamo = reclamo.nombreClaseReclamo,
        CodigoRutaSuministro = reclamo.codigoRutaSuministro,
        Celular = reclamo.celular,
        latitud = reclamo.latitud,
        longitud = reclamo.longitud,
        DescripcionReclamo = reclamo.descripcionReclamo,
        ReferenciaUbicacion = reclamo.referenciaUbicacion,
        CodigoDireccionElectrica = reclamo.codigoDireccionElectrica,
        FechaRegistro = reclamo.fechaRegistro,
        SectorTipico = reclamo.sectorTipico,
        Plazo = reclamo.plazoDias,
        FechaLimiteAtencion = reclamo.fechaLimiteAtencion,
        tipoReclamo = reclamo.tipoReclamo
      )
    }
  }
  * */
  val itemsFake = listOf(
    ReclamoEntity(
      CodigoSuministro = "codigoSuministro",
      CodigoSED = "0010297",
      CodigoEstadoReclamo = "2",
      DireccionElectrica = "CALLE GARCILASO N°200 (4TO NIVEL)",
      NombreSuministro = "GUISPECUSCO CCORIHUAMANI, LINDA GLORGINA",
      CodigoReclamo = "codigoReclamo",
      NombreClaseReclamo = "nombreClaseReclamo",
      CodigoRutaSuministro = "0010609002844",
      Celular = "954607452",
      latitud = "-79.02123342224186",
      longitud = "-8.130710445269498",
      DescripcionReclamo = "DESCRIPCION PRUEBA",
      ReferenciaUbicacion = "REFERENCIA PRUEBA",
      CodigoDireccionElectrica = "codigoDireccionElectrica",
      FechaRegistro = "16 jul. 2024 07:52:00",
      SectorTipico = "sectorTipico",
      Plazo = "1",
      FechaLimiteAtencion = "fechaLimiteAtencion",
      tipoReclamo = "tipoReclamo"
    )
  )
  ExpandableList(
    items = itemsFake,
    modifier = Modifier,
    navController = navController,
    ap = ap,
    "2",
    claimViewModel = claimViewModel
  )
}

@Composable
fun EjecutadoScreen(
  context: Context,
  navController: NavController,
  ap: String,
  claimViewModel: ClaimViewModel
) {
  val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
  var items by remember { mutableStateOf(listOf<ReclamoEntity>()) }

  LaunchedEffect(Unit) {
    claimViewModel.onClaimView(ap, context, "5")
  }
  claimViewModel.ejecutadoReclamos.observe(lifecycleOwner) { value ->
    items = value.map { reclamo ->
      ReclamoEntity(
        CodigoSuministro = reclamo.codigoSuministro,
        CodigoSED = reclamo.codigoSED,
        CodigoEstadoReclamo = reclamo.codigoEstadoReclamo,
        DireccionElectrica = reclamo.direccionElectrica,
        NombreSuministro = reclamo.nombreSuministros,
        CodigoReclamo = reclamo.codigoReclamo,
        NombreClaseReclamo = reclamo.nombreClaseReclamo,
        CodigoRutaSuministro = reclamo.codigoRutaSuministro,
        Celular = reclamo.celular,
        latitud = reclamo.latitud,
        longitud = reclamo.longitud,
        DescripcionReclamo = reclamo.descripcionReclamo,
        ReferenciaUbicacion = reclamo.referenciaUbicacion,
        CodigoDireccionElectrica = reclamo.codigoDireccionElectrica,
        FechaRegistro = reclamo.fechaRegistro,
        SectorTipico = reclamo.sectorTipico,
        Plazo = reclamo.plazoDias,
        FechaLimiteAtencion = reclamo.fechaLimiteAtencion,
        tipoReclamo = reclamo.tipoReclamo
      )
    }

  }
  ExpandableList(
    items = items,
    modifier = Modifier,
    navController = navController,
    ap = ap,
    "5",
    claimViewModel = claimViewModel
  )
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ContentTabItem(
  navController: NavController,
  modifier: Modifier,
  ap: String,
  claimViewModel: ClaimViewModel = viewModel()
) {
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
      0 -> ProgramadoScreen(context, navController, ap = ap, claimViewModel = claimViewModel)
      1 -> EjecutadoScreen(context, navController, ap = ap, claimViewModel = claimViewModel)
    }
  }
}
