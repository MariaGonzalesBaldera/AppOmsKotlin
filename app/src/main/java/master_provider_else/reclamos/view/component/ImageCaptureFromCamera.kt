package master_provider_else.reclamos.view.component

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import master_provider_else.reclamos.BotonDefault
import master_provider_else.reclamos.R
import master_provider_else.reclamos.ui.theme.view.component.BotonDefaultLine
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

data class ImageItem(
  val uri: Uri,
  var isSelected: MutableState<Boolean>
)

@Composable
fun ImageCaptureFromCamera() {
  val context = LocalContext.current
  val imageList = remember { mutableStateListOf<ImageItem>() }

  var currentUri: Uri? by remember {
    mutableStateOf(null)
  }

  val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
    if (success) {
      currentUri?.let { imageList.add(ImageItem(it, mutableStateOf(false))) }
    }
  }

  val permissionLauncher = rememberLauncherForActivityResult(
    ActivityResultContracts.RequestPermission()
  ) { isGranted ->
    if (isGranted) {
      Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
      currentUri = context.createImageFileUri()
      cameraLauncher.launch(currentUri!!)
    } else {
      Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
    }
  }

  Column(
    modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 60.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Row {
      BotonDefault(
        title = "Tomar foto",
        onClick = {
          val permissionCheckResult =
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)

          if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
            currentUri = context.createImageFileUri()
            cameraLauncher.launch(currentUri!!)
          } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
          }
        },
        modifier = Modifier
          .width(155.dp)
          .padding(horizontal = 5.dp)
      )
      BotonDefaultLine(
        title = "Eliminar Foto",
        onClick = {
          imageList.removeAll { it.isSelected.value }
        },
        modifier = Modifier
          .width(155.dp)
          .padding(horizontal = 5.dp)
      )
    }

    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      items(imageList) { imageItem ->
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Image(
            modifier = Modifier
              .size(80.dp)
              .padding(16.dp, 8.dp),
            painter = rememberAsyncImagePainter(imageItem.uri),
            contentDescription = null
          )
          Checkbox(
            checked = imageItem.isSelected.value,
            onCheckedChange = { checkedState ->
              imageItem.isSelected.value = checkedState
            }
          )
        }
      }

      if (imageList.isEmpty()) {
        item {
          Image(
            modifier = Modifier
              .padding(16.dp, 8.dp),
            painter = painterResource(id = R.drawable.ic_image),
            contentDescription = null
          )
        }
      }
    }
  }
}
fun Context.createImageFileUri(): Uri {
  val file = createImageFile()

  return FileProvider.getUriForFile(
    Objects.requireNonNull(this),
    "$packageName.provider",
    file
  )
}

fun Context.createImageFile(): File {
  val timeStamp = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Date())
  val imageFileName = "JPEG_" + timeStamp + "_"
  val storageDir = externalCacheDir

  return File.createTempFile(
    imageFileName,
    ".jpg",
    storageDir
  )
}

@Preview(showBackground = true)
@Composable
private fun ShowPrev() {
  ImageCaptureFromCamera()
}