package master_provider_else.reclamos.ui.theme.view.component

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import master_provider_else.reclamos.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDialogDate(
  stateFecha: DatePickerState,
  showDialog: Boolean,
  onDismissRequest: () -> Unit,
  clickButtonConfirm: () -> Unit
) {

  if (showDialog) {
    DatePickerDialog(
      onDismissRequest = onDismissRequest,
      confirmButton = {
        Button(
          onClick = clickButtonConfirm,
          colors = ButtonDefaults.buttonColors((colorResource(id = R.color.colorPrimary)))
        ) {
          Text(text = "Confirmar")
        }
      }) {
      DatePicker(
        state = stateFecha, colors = DatePickerDefaults.colors(
          selectedDayContainerColor = (colorResource(id = R.color.colorPrimary)),
          todayContentColor = (colorResource(id = R.color.colorPrimary)),
          todayDateBorderColor = (colorResource(id = R.color.colorPrimary)),
          yearContentColor = (colorResource(id = R.color.colorPrimary)),
          currentYearContentColor = (colorResource(id = R.color.colorPrimary)),
          selectedYearContainerColor = (colorResource(id = R.color.colorPrimary)),
        )
      )
    }
  }
}