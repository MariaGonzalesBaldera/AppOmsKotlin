package master_provider_else.reclamos

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ConfirmExitDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
  AlertDialog(
    onDismissRequest = onDismiss,
    title = { Text(text = "Salir de la aplicación") },
    text = { Text(text = "Estás por salir de la aplicación.\n¿Deseas continuar?") },
    confirmButton = {
      TextButton(onClick = onConfirm) {
        Text("ACEPTAR")
      }
    },
    dismissButton = {
      TextButton(onClick = onDismiss) {
        Text("CANCELAR")
      }
    }
  )
}