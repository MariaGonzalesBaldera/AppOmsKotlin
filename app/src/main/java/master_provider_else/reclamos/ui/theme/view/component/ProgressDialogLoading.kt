package master_provider_else.reclamos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ProgressDialogLoading(onDismiss: () -> Unit) {
  var showProgress by remember { mutableStateOf(true) }

  LaunchedEffect(Unit) {
    launch {
      delay(2000) // Simulate initial delay
      // Simulate background work
      for (i in 1..5) {
        delay(1000)
      }
      showProgress = false
      onDismiss()
    }
  }

  if (showProgress) {
    AlertDialog(
      onDismissRequest = onDismiss,
      title = {
        Text(text = "Enviando solicitud", fontSize = 18.sp)
      },
      text = {
        Column(
          modifier = Modifier.fillMaxWidth(),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          CircularProgressIndicator(color = colorResource(id = R.color.colorPrimary))
          Spacer(modifier = Modifier.height(16.dp))
          Text(text = "Espere por favor ...")
        }
      },
      confirmButton = {
        // Puedes dejar el botón de confirmación vacío o añadir uno si es necesario
      },
      shape = MaterialTheme.shapes.medium
    )
  }
}