package master_provider_else.reclamos.ui.theme.view.component

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.rememberImagePainter
import master_provider_else.reclamos.BotonDefault
import master_provider_else.reclamos.R
import master_provider_else.reclamos.view.ui.theme.AppOmsKotlinTheme
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

class ScreenTakePhoto : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AppOmsKotlinTheme {
        // A surface container using the 'background' color from the theme
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = androidx.compose.material.MaterialTheme.colors.background
        ) {
          ImageCaptureFromCamera()
        }
      }

    }
  }
}

@Preview(showBackground = true)
@Composable
private fun ShowBotonImage() {
  ImageCaptureFromCamera()
}


@Composable
fun ImageCaptureFromCamera() {

  val context = LocalContext.current
  val file = context.createImageFile()
  val uri = FileProvider.getUriForFile(
    Objects.requireNonNull(context),
    context.packageName + ".provider", file
  )

  var capturedImageUri by remember {
    mutableStateOf<Uri>(Uri.EMPTY)
  }

  val cameraLauncher =
    rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
      capturedImageUri = uri
    }


  val permissionLauncher = rememberLauncherForActivityResult(
    ActivityResultContracts.RequestPermission()
  ) {
    if (it) {
      Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
      cameraLauncher.launch(uri)
    } else {
      Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
    }
  }


  Column(
    Modifier
      .fillMaxSize()
      .padding(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Bottom
  ) {

    Box(
      Modifier
        .fillMaxWidth()
        .padding(10.dp)
    ) {
      BotonDefault(
        title = "Tomar foto",
        onClick = {
          val permissionCheckResult =
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)

          if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
            cameraLauncher.launch(uri)
          } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
          }
        },
        modifier = Modifier.fillMaxWidth()
      )
    }
  }


  if (capturedImageUri.path?.isNotEmpty() == true) {
    Image(
      modifier = Modifier
        .padding(16.dp, 8.dp),
      painter = rememberImagePainter(capturedImageUri),
      contentDescription = null
    )
  } else {
    Image(
      modifier = Modifier
        .padding(16.dp, 8.dp),
      painter = painterResource(id = R.drawable.ic_image),
      contentDescription = null
    )
  }

}

fun Context.createImageFile(): File {
  val timeStamp = SimpleDateFormat("yyyy_MM_dd_HH:mm:ss").format(Date())
  val imageFileName = "JPEG_" + timeStamp + "_"
  val image = File.createTempFile(
    imageFileName,
    ".jpg",
    externalCacheDir
  )

  return image
}