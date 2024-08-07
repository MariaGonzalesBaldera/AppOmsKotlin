package master_provider_else.reclamos.view.component

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import master_provider_else.reclamos.BotonDefault
import master_provider_else.reclamos.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

data class ImageItem(
  val uri: Uri,
  var isSelected: MutableState<Boolean>
)

@Composable
fun ImageCaptureFromCamera() { //claimViewModel: ClaimViewModel
  val context = LocalContext.current
  val imageList = remember { mutableStateListOf<ImageItem>() }

  var currentUri: Uri? by remember {
    mutableStateOf(null)
  }
  ///LaunchedEffect(Unit) {
  //  claimViewModel.listarFotos(codigoReclamo)
  //}
  val cameraLauncher =
    rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
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

    }

    LazyVerticalGrid(
      columns = GridCells.Fixed(2),
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Top,
      horizontalArrangement = Arrangement.Center
    ) {
      itemsIndexed(imageList) { index, imageItem ->
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(width = 0.3.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp)),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Image(
            modifier = Modifier
              .size(130.dp)
              .padding(8.dp),
            contentScale = ContentScale.Fit,
            painter = rememberAsyncImagePainter(imageItem.uri),
            alignment = Alignment.Center,
            contentDescription = null
          )
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
          ) {
            IconButton(
              onClick = {
                imageList.remove(imageItem)
              }
            ) {
              Icon(
                painter = painterResource(id = R.drawable.ic_delete), // Asegúrate de tener un icono de basurero en tus recursos
                contentDescription = "Eliminar Foto",
                tint = Color.Red
              )
            }
            Text(
              text = "Captura #${index + 1}"
            )
          }
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
private fun ShowPrevPhoto() {
  ImageCaptureFromCamera()
}