package master_provider_else.reclamos.ui.theme.view.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.mapbox.geojson.Point
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import master_provider_else.reclamos.R
import master_provider_else.reclamos.view.ui.theme.AppOmsKotlinTheme
import master_provider_else.reclamos.view.ui.theme.toast

class MainMapsActivity : ComponentActivity() {

  private val permissions = arrayOf(
    android.Manifest.permission.ACCESS_COARSE_LOCATION,
    android.Manifest.permission.ACCESS_FINE_LOCATION,
  )

  lateinit var fusedLocationClient: FusedLocationProviderClient
  lateinit var locationCallback: LocationCallback
  var locationRequired: Boolean = false


  override fun onResume() {
    super.onResume()
    if (locationRequired) {
      startLocationUpdates()
    }
  }

  override fun onPause() {
    super.onPause()
    locationCallback?.let {
      fusedLocationClient?.removeLocationUpdates(it)
    }
  }

  @SuppressLint("MissingPermission")
  fun startLocationUpdates() {
    locationCallback?.let {
      val locationRequest = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY, 100
      )
        .setWaitForAccurateLocation(false)
        .setMinUpdateIntervalMillis(3000)
        .setMaxUpdateDelayMillis(100)
        .build()

      fusedLocationClient?.requestLocationUpdates(
        locationRequest,
        it,
        Looper.getMainLooper()
      )
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    enableEdgeToEdge()
    setContent {
      val context = LocalContext.current
      context.toast("estamos en mainMaps")
      var currentLocation by remember {
        mutableStateOf(LatLng(0.toDouble(), 0.toDouble()))
      }
      AppOmsKotlinTheme {

        locationCallback = object : LocationCallback() {
          override fun onLocationResult(p0: LocationResult) {
            super.onLocationResult(p0)
            for (location in p0.locations) {
              currentLocation = LatLng(location.latitude, location.longitude)
            }
          }
        }
      }
      Surface(
        modifier = Modifier.fillMaxSize(),
        color = androidx.compose.material.MaterialTheme.colors.background
      ) {
        LocationScreen(this@MainMapsActivity, currentLocation)

      }
    }
  }

  @OptIn(MapboxExperimental::class)
  @Composable
  fun LocationScreen(context: Context, currentLocation: LatLng) {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    //informacion del gps
    val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
        locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

    val launchMultiplePermissions = rememberLauncherForActivityResult(
      contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionMaps ->
      val areGranted = permissionMaps.values.reduce { acc, next -> acc && next }
      if (areGranted) {
        locationRequired = true
        startLocationUpdates()
        context.toast("Permission Granted")
      } else {
        context.toast("Permission Denied")

      }
    }
    Box(modifier = Modifier.fillMaxSize()) {
      MapboxMap(
        Modifier.fillMaxWidth(),
        mapViewportState = MapViewportState().apply {
          setCameraOptions {
            zoom(15.0)
            center(Point.fromLngLat(currentLocation.longitude, currentLocation.latitude))
            pitch(0.0)
            bearing(0.0)
          }
        }
      ) {
        AddPointer(Point.fromLngLat(currentLocation.longitude, currentLocation.latitude), context)
      }
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(40.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(text = "Your location: ${currentLocation.latitude}/${currentLocation.longitude}")
        Button(onClick = {
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
        }) {
          Text(text = "Get your location")
        }
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

}