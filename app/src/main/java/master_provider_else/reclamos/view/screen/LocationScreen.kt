package master_provider_else.reclamos.view.screen

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.LocationManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.Polyline
import com.mapbox.geojson.Point
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import master_provider_else.reclamos.BotonDefault
import master_provider_else.reclamos.R
import master_provider_else.reclamos.data.database.entity.LineasEntity
import master_provider_else.reclamos.data.model.Coordenadas
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
  val celular by remember { mutableStateOf(params.celular) }

  val arrayListCoordenadasRedesMt: ArrayList<Coordenadas> = ArrayList<Coordenadas>()
  val arrayListCoordenadasRedesMtLINEAS: ArrayList<Coordenadas> = ArrayList<Coordenadas>()
  val arrayListCoordenadasRedesBt: ArrayList<Coordenadas> = ArrayList<Coordenadas>()
  val arrayListLineRedesMt: List<LineasEntity> = ArrayList<LineasEntity>()

  var colorPin by remember { mutableIntStateOf(R.drawable.ubication) }


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
  val puntosRedesBt = remember { mutableStateListOf<Point>() }
  val puntosRedesMt = remember { mutableStateListOf<Point>() }
  val lineasRedesMt = remember { mutableStateListOf<Point>() }
  var redesMT by remember { mutableStateOf(false) }
  var redesBT by remember { mutableStateOf(false) }

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
      AddPointer(Point.fromLngLat(latitud, longitud), context, colorPin)
      puntosRedesBt.forEach { punto ->
        AddPointer(punto, context, colorPin)
      }
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

      Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
          checked = redesMT,
          onCheckedChange = {
            redesMT = it
            if (redesMT) {
              arrayListCoordenadasRedesMt.forEach { coordenada ->
                colorPin=R.drawable.pin_rojo
                if (coordenada.TipoCoordenada == "10") {
                  puntosRedesMt.add(
                    Point.fromLngLat(
                      coordenada.latLng.latitude,
                      coordenada.latLng.longitude
                    )
                  )
                } else if (coordenada.TipoCoordenada == "8" || coordenada.TipoCoordenada == "17") {
                  puntosRedesMt.add(
                    Point.fromLngLat(
                      coordenada.latLng.latitude,
                      coordenada.latLng.longitude
                    )
                  )
                }

              }
              for (i in arrayListCoordenadasRedesMtLINEAS.indices) {
                val arrayLatlng = java.util.ArrayList<LatLng>()
                arrayLatlng.clear()
                for (j in arrayListLineRedesMt.indices) {
                  if (arrayListCoordenadasRedesMtLINEAS[i].CodigoNTCSE
                      .equals(arrayListLineRedesMt[j].CodigoNTCSE)
                  ) {
                    val latLng = LatLng(
                      arrayListLineRedesMt[j].Latitud.toDouble(),
                      arrayListLineRedesMt[j].Longitud.toDouble()
                    )
                    arrayLatlng.add(latLng)
                  }
                }
                paintLine(arrayLatlng)
              }
            } else {
              puntosRedesMt.clear()
              lineasRedesMt.clear()
            }
          }
        )
        Text(text = "Redes MT", fontSize = 17.sp)
      }
      Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
          checked = redesBT,
          onCheckedChange = {
            redesBT = it
            if (redesBT) {
              puntosRedesBt.clear()
              arrayListCoordenadasRedesBt.forEach { coordenada ->
                if (coordenada.TipoCoordenada == "7" || coordenada.TipoCoordenada == "16") {
                  colorPin = R.drawable.pin_verde
                  puntosRedesBt.add(
                    Point.fromLngLat(
                      coordenada.latLng.latitude,
                      coordenada.latLng.longitude
                    )
                  )
                }
              }
            } else {
              puntosRedesBt.clear()
            }
          }
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
          mapaViewModel.inciarTrabajoBoton()
          navController.navigate(route = AppScreens.InicioTrabajoOmsScreen.createRoute(params))

        } else {
          navController.navigate(route = AppScreens.InicioTrabajoApScreen.route)
        }
      })
    }

  }
}

fun paintLine(listPoint: ArrayList<LatLng>) {

}


@OptIn(MapboxExperimental::class)
@Composable
fun AddPointer(point: Point, context: Context, colorPin: Int) {
  val drawable = ResourcesCompat.getDrawable(
    context.resources,
    colorPin,
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