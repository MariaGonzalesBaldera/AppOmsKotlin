package master_provider_else.reclamos.utils

import java.text.SimpleDateFormat
import java.util.Locale


// Salida: 31 jul. 2024 09:37:00
fun formatDateTime(dateString: String): String {
  val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
  val outputFormat = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
  val date = inputFormat.parse(dateString)
  return outputFormat.format(date)
}