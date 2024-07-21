package master_provider_else.reclamos.view.screen

import android.Manifest
import androidx.compose.runtime.Composable
import android.annotation.SuppressLint
import android.os.Looper
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import master_provider_else.reclamos.view.ui.theme.AppOmsKotlinTheme

@SuppressLint("UnrememberedMutableState")
@Composable
fun LocationMap(
  navController: NavController,
  tipo: String
) {
  val context = LocalContext.current
  var currentLocation by remember { mutableStateOf(LatLng(0.0, 0.0)) }
  var locationRequired by remember { mutableStateOf(false) }

  // Initialize FusedLocationProviderClient and LocationCallback
  val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
  val locationCallback = rememberUpdatedState(newValue = object : LocationCallback() {
    override fun onLocationResult(locationResult: LocationResult) {
      super.onLocationResult(locationResult)
      for (location in locationResult.locations) {
        currentLocation = LatLng(location.latitude, location.longitude)
      }
    }
  })

  // Handle location updates
  LaunchedEffect(locationRequired) {
    if (locationRequired) {
      startLocationUpdates(fusedLocationClient, locationCallback.value)
    }
  }

  // Content
  AppOmsKotlinTheme {
    Surface(
      modifier = Modifier.fillMaxSize(),
      color = androidx.compose.material.MaterialTheme.colors.background
    ) {
      LocationScreen(
        navController = navController,
        context = context,
        currentLocation = currentLocation,
        permissions = arrayOf(
          Manifest.permission.ACCESS_COARSE_LOCATION,
          Manifest.permission.ACCESS_FINE_LOCATION
        ),
        locationRequired = mutableStateOf(locationRequired),
        startLocationUpdates = {
          locationRequired = true
          startLocationUpdates(fusedLocationClient, locationCallback.value)
        },
        tipo = tipo
      )
    }
  }
}

@SuppressLint("MissingPermission")
private fun startLocationUpdates(
  fusedLocationClient: FusedLocationProviderClient,
  locationCallback: LocationCallback
) {
  val locationRequest = LocationRequest.Builder(
    Priority.PRIORITY_HIGH_ACCURACY, 100
  )
    .setWaitForAccurateLocation(false)
    .setMinUpdateIntervalMillis(3000)
    .setMaxUpdateDelayMillis(100)
    .build()

  fusedLocationClient.requestLocationUpdates(
    locationRequest,
    locationCallback,
    Looper.getMainLooper()
  )
}
