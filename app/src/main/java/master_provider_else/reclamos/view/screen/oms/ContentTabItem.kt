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
import master_provider_else.reclamos.data.dto.ReclamoArray
import master_provider_else.reclamos.navigation.AppScreens
import master_provider_else.reclamos.utils.formatDateTime
import master_provider_else.reclamos.viewModel.ClaimViewModel

@Composable
fun ExpandableList(
  items: List<ReclamoArray>,
  modifier: Modifier = Modifier,
  navController: NavController,
  ap: String
) {
  var expandedItemId by remember { mutableStateOf(items.firstOrNull()?.codigoReclamo) }

  LazyColumn(modifier = modifier) {
    items(items) { item ->
      ReclamoListaItem(
        item = item,
        isExpanded = expandedItemId == item.codigoReclamo,
        onItemClick = { id ->
          expandedItemId = if (expandedItemId == id) null else id
        },
        navController = navController,
        ap
      )
    }
  }
}

@Composable
fun ProgramadoScreen(navController: NavController, ap: String, claimViewModel: ClaimViewModel) {
  val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
  val context = LocalContext.current
  var items by remember { mutableStateOf(listOf<ReclamoArray>()) }
  //llama al api
  claimViewModel.onClaimView(ap, context,"3")

  claimViewModel.reclamoModel.observe(lifecycleOwner) { value ->
  items = value.map { reclamo ->
      ReclamoArray(
        codigoSuministro = reclamo.codigoSuministro,
        codigoSED = reclamo.codigoSED,
        codigoEstadoReclamo = reclamo.codigoEstadoReclamo,
        direccionElectrica = reclamo.direccionElectrica,
        nombreSuministros = reclamo.nombreSuministros,
        codigoReclamo = reclamo.codigoReclamo,
        nombreClaseReclamo = reclamo.nombreClaseReclamo,
        codigoRutaSuministro = reclamo.codigoRutaSuministro,
        celular = reclamo.celular,
        latitud = reclamo.latitud,
        longitud = reclamo.longitud,
        descripcionReclamo = reclamo.descripcionReclamo,
        referenciaUbicacion = reclamo.referenciaUbicacion,
        codigoDireccionElectrica = reclamo.codigoDireccionElectrica,
        fechaRegistro = reclamo.fechaRegistro,
        sectorTipico = reclamo.sectorTipico,
        plazoDias = reclamo.plazoDias,
        fechaLimiteAtencion = reclamo.fechaLimiteAtencion,
        tipoReclamo = reclamo.tipoReclamo
      )
  }
  /*
  codigoSuministro = reclamo.codigoSuministro,
          codigoSED = reclamo.codigoSED,
          codigoEstadoReclamo = reclamo.codigoEstadoReclamo,
          direccionElectrica = reclamo.direccionElectrica,
          nombreSuministros = reclamo.nombreSuministros,
          codigoReclamo = reclamo.codigoReclamo,
          nombreClaseReclamo = reclamo.nombreClaseReclamo,
          codigoRutaSuministro = reclamo.codigoRutaSuministro,
          celular = reclamo.celular,
          latitud = reclamo.latitud,
          longitud = reclamo.longitud,
          descripcionReclamo = reclamo.descripcionReclamo,
          referenciaUbicacion = reclamo.referenciaUbicacion,
          codigoDireccionElectrica = reclamo.codigoDireccionElectrica,
          fechaRegistro = reclamo.fechaRegistro,
          sectorTipico = reclamo.sectorTipico,
          plazoDias = reclamo.plazoDias,
          fechaLimiteAtencion = reclamo.fechaLimiteAtencion,
          tipoReclamo = reclamo.tipoReclamo
  * */
   }
  ExpandableList(
    items = items,
    modifier = Modifier,
    navController = navController,
    ap = ap
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
  val context = LocalContext.current
  var items by remember { mutableStateOf(listOf<ReclamoArray>()) }

  claimViewModel.onClaimView(ap, context,"5")

  claimViewModel.reclamoModel.observe(lifecycleOwner) { value ->
    items = value.map { reclamo ->
      ReclamoArray(
        codigoSuministro = reclamo.codigoSuministro,
        codigoSED = reclamo.codigoSED,
        codigoEstadoReclamo = reclamo.codigoEstadoReclamo,
        direccionElectrica = reclamo.direccionElectrica,
        nombreSuministros = reclamo.nombreSuministros,
        codigoReclamo = reclamo.codigoReclamo,
        nombreClaseReclamo = reclamo.nombreClaseReclamo,
        codigoRutaSuministro = reclamo.codigoRutaSuministro,
        celular = reclamo.celular,
        latitud = reclamo.latitud,
        longitud = reclamo.longitud,
        descripcionReclamo = reclamo.descripcionReclamo,
        referenciaUbicacion = reclamo.referenciaUbicacion,
        codigoDireccionElectrica = reclamo.codigoDireccionElectrica,
        fechaRegistro = reclamo.fechaRegistro,
        sectorTipico = reclamo.sectorTipico,
        plazoDias = reclamo.plazoDias,
        fechaLimiteAtencion = reclamo.fechaLimiteAtencion,
        tipoReclamo = reclamo.tipoReclamo
      )
    }

  }
  ExpandableList(
    items = items,
    modifier = Modifier,
    navController = navController,
    ap = ap
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
      0 -> ProgramadoScreen(navController, ap = ap, claimViewModel = claimViewModel)
      1 -> EjecutadoScreen(context, navController, ap = ap, claimViewModel = claimViewModel)
    }
  }
}
