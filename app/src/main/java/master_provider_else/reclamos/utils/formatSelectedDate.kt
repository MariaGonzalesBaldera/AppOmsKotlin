package master_provider_else.reclamos.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun formatSelectedDate(selectedDateMillis: Long?): String? {
  val calendar = Calendar.getInstance().apply {
    if (selectedDateMillis != null) {
      timeInMillis = selectedDateMillis
    }
    // Adjust for the time zone offset to get the correct local date
    val zoneOffset = get(Calendar.ZONE_OFFSET)
    val dstOffset = get(Calendar.DST_OFFSET)
    add(Calendar.MILLISECOND, -(zoneOffset + dstOffset))
  }

  return selectedDateMillis?.let {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("es", "ES"))
    dateFormat.format(calendar.time)
  }
}