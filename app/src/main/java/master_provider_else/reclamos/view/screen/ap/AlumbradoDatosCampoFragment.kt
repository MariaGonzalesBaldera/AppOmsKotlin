package master_provider_else.reclamos.ui.theme.view.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import master_provider_else.reclamos.CardTextImage
import master_provider_else.reclamos.R
import master_provider_else.reclamos.SpinnerText
import master_provider_else.reclamos.TransparentTextField
import master_provider_else.reclamos.ui.theme.view.component.ShowDialogDate
import master_provider_else.reclamos.utils.formatSelectedDate

@Preview(showBackground = true)
@Composable
private fun TestView() {
  AlumbradoDatosCampoFragment()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlumbradoDatosCampoFragment(modifier: Modifier = Modifier) {
  val orderValue = rememberSaveable() { mutableStateOf("") }
  val focusManager = LocalFocusManager.current
  val stateFechaVefi = rememberDatePickerState()
  val stateFechaSubsanacion = rememberDatePickerState()
  var showDialog by remember { mutableStateOf(false) }
  var showDialog2 by remember { mutableStateOf(false) }

  Surface(color = androidx.compose.material.MaterialTheme.colors.background) {
    Scaffold { padding ->

      LazyColumn(
        modifier = Modifier
          .padding(20.dp)
          .fillMaxSize()
      ) {
        item {

          val entry1 = Pair("Key1", "Entry1")
          val entry2 = Pair("Key2", "Entry2")
          val entry3 = Pair("Key3", "Entry3")

          SpinnerText(
            label = "Tipo Deficiencia Registrada",
            listOf(entry1, entry2, entry3),
            preselected = entry2,
            onSelectionChanged = { selected -> /* do something with selected */ }
          )

          Spacer(modifier = Modifier.padding(5.dp))

          MaterialTheme {

            val entry1 = Pair("Key1", "Entry1")
            val entry2 = Pair("Key2", "Entry2")
            val entry3 = Pair("Key3", "Entry3")

            SpinnerText(
              label = "Tipo Deficiencia Registrada",
              listOf(entry1, entry2, entry3),
              preselected = entry2,
              onSelectionChanged = { selected -> /* do something with selected */ }
            )
          }
          Spacer(modifier = Modifier.padding(5.dp))
          CardTextImage(
            "Node",
            "",
            painterResource(id = R.drawable.agregaritem),
            "Imagen de más",
            onClick = {}
          )
          Spacer(modifier = Modifier.padding(5.dp))

          val formattedDateVeri = formatSelectedDate(stateFechaVefi.selectedDateMillis)
          CardTextImage(
            title = "Fecha Verificación",
            subTitle = if (formattedDateVeri == null) "Seleccione la fecha" else "$formattedDateVeri",
            painter = painterResource(id = R.drawable.icono_date),
            descriptionImage = "Icono de calendario",
            onClick = { showDialog = true }
          )
          ShowDialogDate(
            stateFechaVefi,
            showDialog,
            onDismissRequest = { showDialog = false },
            clickButtonConfirm = { showDialog = false })
          Spacer(modifier = Modifier.padding(5.dp))

          val formattedDateSub = formatSelectedDate(stateFechaSubsanacion.selectedDateMillis)
          CardTextImage(
            title = "Fecha Subsanación",
            subTitle = if (formattedDateSub == null) "Seleccione la fecha" else "$formattedDateSub",
            painter = painterResource(id = R.drawable.icono_date),
            descriptionImage = "Icono de calendario",
            onClick = { showDialog2 = true }
          )
          ShowDialogDate(
            stateFechaSubsanacion,
            showDialog2,
            onDismissRequest = { showDialog2 = false },
            clickButtonConfirm = { showDialog2 = false })
          Spacer(modifier = Modifier.padding(5.dp))

          TransparentTextField(
            textFieldValue = orderValue,
            textLabel = "Orden Trabajo",
            keyboardType = KeyboardType.Text,
            keyboardActions = KeyboardActions(
              onNext = {
                focusManager.moveFocus(FocusDirection.Down)
              }),
            imeAction = ImeAction.Next
          )
          Spacer(modifier = Modifier.padding(5.dp))

        }
      }
    }
  }

}

