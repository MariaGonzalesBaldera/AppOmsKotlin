package master_provider_else.reclamos

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProgressDialog(onDismissRequest: () -> Unit) {
  AlertDialog(
    onDismissRequest = onDismissRequest,
    title = { Text(text = "Enviando solicitud", fontSize = 18.sp) },
    text = {
      Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        CircularProgressIndicator(
          color = colorResource(id = R.color.colorPrimary),
          modifier = Modifier
            .padding(start = 100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Espere por favor ...")
      }
    },
    confirmButton = {
      // Puedes dejar el botón de confirmación vacío o añadir uno si es necesario
    },
    shape = RoundedCornerShape(20.dp),
  )
}
