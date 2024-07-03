package master_provider_else.reclamos.ui.theme.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import master_provider_else.reclamos.CardTextImage
import master_provider_else.reclamos.R
import master_provider_else.reclamos.SpinnerText
import master_provider_else.reclamos.TransparentTextField
import master_provider_else.reclamos.ui.theme.view.component.ShowDialogDate
import master_provider_else.reclamos.ui.theme.view.component.ShowDialogTime
import master_provider_else.reclamos.utils.formatSelectedDate
import java.util.Calendar
import java.util.Locale

@Preview(showBackground = true)
@Composable
private fun TestView() {
  ReclamosRegistroInfFichaTecnica()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReclamosRegistroInfFichaTecnica(modifier: Modifier = Modifier) {
  val circuitoBTValue = rememberSaveable() { mutableStateOf("") }
  val sedValue = rememberSaveable() { mutableStateOf("") }
  val observacionValue = rememberSaveable() { mutableStateOf("") }
  val focusManager = LocalFocusManager.current
  var showDialog by remember { mutableStateOf(false) }
  var showDialogTime by remember { mutableStateOf(false) }

  val stateFecha = rememberDatePickerState()
  var stateHora by remember { mutableStateOf("") }


  LazyColumn(modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp)) {
    item {
      Box(
        modifier = modifier
          .background(color = Color.White)
          .fillMaxSize()
      ) {
        MaterialTheme {

          val entry1 = Pair("Key1", "Entry1")
          val entry2 = Pair("Key2", "Entry2")
          val entry3 = Pair("Key3", "Entry3")

          SpinnerText(
            label = "Tipo Denuncia Verificada",
            list = listOf(entry1, entry2, entry3),
            preselected = entry2,
            onSelectionChanged = { selected -> /* do something with selected */ }
          )
        }

      }
      Spacer(modifier = Modifier.padding(5.dp))
      Box(
        modifier = modifier
          .background(color = Color.White)
          .fillMaxSize()
      ) {
        MaterialTheme {

          val entry1 = Pair("Key1", "Entry1")
          val entry2 = Pair("Key2", "Entry2")
          val entry3 = Pair("Key3", "Entry3")

          SpinnerText(
            label = "Tipo Instalción",
            listOf(entry1, entry2, entry3),
            preselected = entry2,
            onSelectionChanged = { selected -> /* do something with selected */ }
          )
        }

      }
      Spacer(modifier = Modifier.padding(5.dp))
      Box(
        modifier = modifier
          .background(color = Color.White)
          .fillMaxSize()
      ) {
        MaterialTheme {

          val entry1 = Pair("Key1", "Entry1")
          val entry2 = Pair("Key2", "Entry2")
          val entry3 = Pair("Key3", "Entry3")

          SpinnerText(
            label = "Instalación Afectada",
            listOf(entry1, entry2, entry3),
            preselected = entry2,
            onSelectionChanged = { selected -> /* do something with selected */ }
          )
        }

      }
      Spacer(modifier = Modifier.padding(5.dp))
      Box(
        modifier = modifier
          .background(color = Color.White)
          .fillMaxSize()
      ) {
        MaterialTheme {

          val entry1 = Pair("Key1", "Entry1")
          val entry2 = Pair("Key2", "Entry2")
          val entry3 = Pair("Key3", "Entry3")

          SpinnerText(
            label = "Punto Maniobra",
            listOf(entry1, entry2, entry3),
            preselected = entry2,
            onSelectionChanged = { selected -> /* do something with selected */ }
          )
        }

      }
      Spacer(modifier = Modifier.padding(5.dp))
      Box(
        modifier = modifier
          .background(color = Color.White)
          .fillMaxSize()
      ) {
        MaterialTheme {

          val entry1 = Pair("Key1", "Entry1")
          val entry2 = Pair("Key2", "Entry2")
          val entry3 = Pair("Key3", "Entry3")

          SpinnerText(
            label = "Capacidad",
            listOf(entry1, entry2, entry3),
            preselected = entry2,
            onSelectionChanged = { selected -> /* do something with selected */ }
          )
        }

      }
      Spacer(modifier = Modifier.padding(5.dp))
      TransparentTextField(
        textFieldValue = circuitoBTValue,
        textLabel = "Circuito BT",
        keyboardType = KeyboardType.Text,
        keyboardActions = KeyboardActions(
          onNext = {
            focusManager.moveFocus(FocusDirection.Down)
          }),
        imeAction = ImeAction.Next
      )

      Spacer(modifier = Modifier.padding(5.dp))
      TransparentTextField(
        textFieldValue = sedValue,
        textLabel = "SED",
        keyboardType = KeyboardType.Text,
        keyboardActions = KeyboardActions(
          onNext = {
            focusManager.moveFocus(FocusDirection.Down)
          }),
        imeAction = ImeAction.Next
      )
      Spacer(modifier = Modifier.padding(5.dp))
      val formattedDateSub = formatSelectedDate(stateFecha.selectedDateMillis)
      CardTextImage(
        title = "",
        subTitle = if (formattedDateSub == null) "Seleccione la fecha" else "$formattedDateSub",
        painter = painterResource(id = R.drawable.icono_date),
        descriptionImage = "Icono de calendario",
        onClick = { showDialog = true }
      )
      ShowDialogDate(
        stateFecha,
        showDialog,
        onDismissRequest = { showDialog = false },
        clickButtonConfirm = { showDialog = false })
      Spacer(modifier = Modifier.padding(5.dp))

      CardTextImage(
        title = "",
        subTitle = if (stateHora == "") "Seleccione Hora" else stateHora,
        painter = painterResource(id = R.drawable.icono_hour),
        descriptionImage = "Icono de reloj",
        onClick = { showDialogTime = true }
      )
      ShowDialogTime(
        onCancel = { showDialogTime = false },
        onConfirm = { calendar ->
          val hour = calendar[Calendar.HOUR_OF_DAY]
          val minute = calendar[Calendar.MINUTE]
          val amPm = if (calendar[Calendar.AM_PM] == Calendar.AM) "AM" else "PM"
          stateHora = String.format(Locale.getDefault(), "%02d:%02d %s", hour, minute, amPm)
          showDialogTime = false
        },
        showDialog = showDialogTime
      )
      Spacer(modifier = Modifier.padding(5.dp))
      Box(
        modifier = modifier
          .background(color = Color.White)
          .fillMaxWidth()
      ) {
        MaterialTheme {

          val entry1 = Pair("Key1", "Entry1")
          val entry2 = Pair("Key2", "Entry2")
          val entry3 = Pair("Key3", "Entry3")

          SpinnerText(
            label = "Causa Avería",
            listOf(entry1, entry2, entry3),
            preselected = entry2,
            onSelectionChanged = { selected -> /* do something with selected */ }
          )
        }

      }
      Spacer(modifier = Modifier.padding(5.dp))
      Box(
        modifier = modifier
          .background(color = Color.White)
          .fillMaxSize()
      ) {
        MaterialTheme {

          val entry1 = Pair("Key1", "Entry1")
          val entry2 = Pair("Key2", "Entry2")
          val entry3 = Pair("Key3", "Entry3")

          SpinnerText(
            label = "Acciones Realizadas",
            listOf(entry1, entry2, entry3),
            preselected = entry2,
            onSelectionChanged = { selected -> /* do something with selected */ }
          )
        }

      }
      Spacer(modifier = Modifier.padding(5.dp))
      Box(
        modifier = modifier
          .background(color = Color.White)
          .fillMaxSize()
      ) {
        MaterialTheme {

          val entry1 = Pair("Key1", "Entry1")
          val entry2 = Pair("Key2", "Entry2")
          val entry3 = Pair("Key3", "Entry3")

          SpinnerText(
            label = "Tipo Solución",
            listOf(entry1, entry2, entry3),
            preselected = entry2,
            onSelectionChanged = { selected -> /* do something with selected */ }
          )
        }

      }
      Spacer(modifier = Modifier.padding(5.dp))
      Box(
        modifier = modifier
          .background(color = Color.White)
          .fillMaxSize()
      ) {
        MaterialTheme {

          val entry1 = Pair("Key1", "Entry1")
          val entry2 = Pair("Key2", "Entry2")
          val entry3 = Pair("Key3", "Entry3")

          SpinnerText(
            label = "Requiere intervención",
            listOf(entry1, entry2, entry3),
            preselected = entry2,
            onSelectionChanged = { selected -> /* do something with selected */ }
          )
        }

      }
      Spacer(modifier = Modifier.padding(5.dp))
      TransparentTextField(
        textFieldValue = observacionValue,
        textLabel = "Observación de solución",
        keyboardType = KeyboardType.Text,
        keyboardActions = KeyboardActions(
          onNext = {
            focusManager.moveFocus(FocusDirection.Down)
          }),
        imeAction = ImeAction.Next
      )
    }
  }
}