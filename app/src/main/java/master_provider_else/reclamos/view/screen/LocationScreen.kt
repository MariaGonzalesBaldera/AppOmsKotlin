package master_provider_else.reclamos.view.screen

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.LocationManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.model.LatLng
import com.mapbox.geojson.Point
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import master_provider_else.reclamos.BotonDefault
import master_provider_else.reclamos.R
import master_provider_else.reclamos.domain.model.ParamMap
import master_provider_else.reclamos.navigation.AppScreens
import master_provider_else.reclamos.ui.theme.view.component.CallPhone
import master_provider_else.reclamos.view.ui.theme.toast
import master_provider_else.reclamos.viewModel.MapaViewModel


@OptIn(MapboxExperimental::class)
@Composable
fun LocationScreen(
  navController: NavController,
  mapaViewModel: MapaViewModel = viewModel(),
  context: Context = LocalContext.current,
  currentLocation: LatLng = LatLng(0.0, 0.0),
  permissions: Array<String> = arrayOf(
    android.Manifest.permission.ACCESS_COARSE_LOCATION,
    android.Manifest.permission.ACCESS_FINE_LOCATION
  ),
  locationRequired: MutableState<Boolean> = remember { mutableStateOf(false) },
  startLocationUpdates: () -> Unit = {},
  ap: String,
  estado: String,
  params: ParamMap
) {
  val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
  val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
      locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

  val latitud: Double by remember { mutableDoubleStateOf(params.latitud) }
  val longitud: Double by remember { mutableDoubleStateOf(params.longitud) }
  var codigoReclamo by remember { mutableStateOf(params.codigoReclamo) }
  var codigoEstado by remember { mutableStateOf(params.codigoEstadoReclamo) }
  var nombreClaseReclamo by remember { mutableStateOf(params.nombreClaseReclamo) }
  var sed by remember { mutableStateOf(params.codigoSED) }
  var ruta by remember { mutableStateOf(params.codigoRutaSuministro) }
  val celular by remember { mutableStateOf(params.celular) }
  var codigoDireccionElectrica by remember { mutableStateOf(params.codigoDireccionElectrica) }

  val launchMultiplePermissions = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.RequestMultiplePermissions()
  ) { permissionMaps ->
    val areGranted = permissionMaps.values.reduce { acc, next -> acc && next }
    if (areGranted) {
      locationRequired.value = true //
      startLocationUpdates()
      context.toast("Permission Granted")
    } else {
      context.toast("Permission Denied")
    }
  }
  ///recuperando datos

  LaunchedEffect(Unit) {
    val response = mapaViewModel.obtenerPuntosGPSServicio(params, context)
    if (!response) {
      Log.e("ERROR", "Error a obnenter los puntos")
    }
    if (permissions.all {
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
      }) {
      if (!isGPSEnabled) {
        context.toast("Activa tu GPS para obtener la ubicación exacta")
      }
      startLocationUpdates()
    } else {
      launchMultiplePermissions.launch(permissions)
    }
  }
  Box(Modifier.padding()) {
    MapboxMap(
      Modifier.fillMaxWidth(),
      mapViewportState = MapViewportState().apply {
        setCameraOptions {
          zoom(15.0)
          center(Point.fromLngLat(latitud, longitud))
          pitch(0.0)
          bearing(0.0)
        }
      }
    ) {
      AddPointer(Point.fromLngLat(latitud, longitud), context)
    }

    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(color = colorResource(id = R.color.colorPrimary))
    ) {
      BotonDefault(
        title = "LLAMAR",
        icon = Icons.Default.Call,
        modifier = Modifier.fillMaxWidth(),
        onClick = {
          CallPhone(context, celular)
        })
    }
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 50.dp, horizontal = 10.dp)
    ) {
      var redesMT by remember { mutableStateOf(false) }
      var redesBT by remember { mutableStateOf(false) }

      Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
          checked = redesMT,
          onCheckedChange = { redesMT = it }
        )
        Text(text = "Redes MT", fontSize = 17.sp)
      }
      Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
          checked = redesBT,
          onCheckedChange = { redesBT = it }
        )
        Text(text = "Redes BT", fontSize = 17.sp)
      }
    }
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 10.dp, horizontal = 10.dp),
      verticalArrangement = Arrangement.Bottom,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {

      BotonDefault(title = "Iniciar Trabajo", onClick = {
        if (ap == "0") {
          navController.navigate(route = AppScreens.InicioTrabajoOmsScreen.route)
        } else {
          navController.navigate(route = AppScreens.InicioTrabajoApScreen.route)
        }
      })
    }

  }
}

@OptIn(MapboxExperimental::class)
@Composable
fun AddPointer(point: Point, context: Context) {
  val drawable = ResourcesCompat.getDrawable(
    context.resources,
    R.drawable.ubication,
    null
  )
  val bitmap = drawable!!.toBitmap(
    drawable.intrinsicWidth,
    drawable.intrinsicHeight,
    Bitmap.Config.ARGB_8888
  )
  PointAnnotation(
    iconImageBitmap = bitmap,
    iconSize = 0.07,
    point = point,
    onClick = {
      context.toast("${point.coordinates()}")
      true
    }
  )
}