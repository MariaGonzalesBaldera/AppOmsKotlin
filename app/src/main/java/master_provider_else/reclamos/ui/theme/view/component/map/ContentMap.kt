package master_provider_else.reclamos.ui.theme.view.component.map

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.mapbox.geojson.Point
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import master_provider_else.reclamos.R

@Composable
fun ContentMap() {
  val context = LocalContext.current
  ShowMap(context)
}

@OptIn(MapboxExperimental::class)
@Composable
fun ShowMap(context: Context) {
  MapboxMap(
    Modifier.fillMaxSize(),
    mapViewportState = MapViewportState().apply {
      setCameraOptions {
        zoom(15.0)
        center(Point.fromLngLat(-77.02824, -12.04318))
        pitch(0.0)
        bearing(0.0)
      }
    },
  ) {
    AddPointer(Point.fromLngLat(-77.02824, -12.04318), context)
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
      Toast.makeText(
        context,
        "${point.coordinates()}",
        Toast.LENGTH_LONG
      ).show()
      true
    }
  )
}