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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.core.content.ContextCompat
import master_provider_else.reclamos.CardTextImage
import master_provider_else.reclamos.R
import master_provider_else.reclamos.SpinnerText
import master_provider_else.reclamos.TransparentTextField
import master_provider_else.reclamos.ui.theme.view.component.ShowDialogDate
import master_provider_else.reclamos.ui.theme.view.component.ShowDialogTime
import master_provider_else.reclamos.utils.formatSelectedDate
import master_provider_else.reclamos.viewModel.ClaimViewModel
import java.util.Calendar
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReclamosRegistroInfFichaTecnica(claimViewModel: ClaimViewModel, modifier: Modifier = Modifier) {
  val circuitoBTValue = rememberSaveable() { mutableStateOf("") }
  val sedValue = rememberSaveable() { mutableStateOf("") }
  val observacionValue = rememberSaveable() { mutableStateOf("") }
  val focusManager = LocalFocusManager.current
  var showDialog by remember { mutableStateOf(false) }
  var showDialogTime by remember { mutableStateOf(false) }
  val stateFecha = rememberDatePickerState()
  var stateHora by remember { mutableStateOf("") }

  LaunchedEffect(Unit) {
    claimViewModel.datosTipoDenuncia()
    claimViewModel.datosTipoInstalacionElectricaAfectada()
    claimViewModel.datosTipoInstalacionAfectada()
    claimViewModel.datosTipoInstalacionElectricaAfectada()
    claimViewModel.datosTipoEquipoProteccionManiobra()
    claimViewModel.datosTipoManiobraCapacidad()
    claimViewModel.datosCausaAveria()
    claimViewModel.datosSolucionAveria()
    claimViewModel.datosSolucionInterrupcion()
    claimViewModel.datosTipoAreaIntervencion()
  }

  val tipoDenunciaList by claimViewModel.tipoDenunciaList
  val tipoInstalacionElectricaAfectadaList by claimViewModel.tipoInstalacionElectricaAfectadaList
  val tipoInstalacionAfectadaList by claimViewModel.tipoInstalacionAfectadaList
  val tipoEquipoProteccionManiobraList by claimViewModel.tipoEquipoProteccionManiobraList
  val tipoManiobraCapacidadList by claimViewModel.tipoManiobraCapacidadList
  val causaAveriaList by claimViewModel.causaAveriaList
  val solucionAveriaList by claimViewModel.solucionAveriaList
  val solucionInterrupcionList by claimViewModel.solucionInterrupcionList
  val tipoAreaIntervencionList by claimViewModel.tipoAreaIntervencionList

  LazyColumn(modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 60.dp)) {
    item {
      Box(
        modifier = modifier
          .background(color = Color.White)
          .fillMaxSize()
      ) {
        MaterialTheme {
          if (tipoDenunciaList.isNotEmpty()) {
            val preselected = tipoDenunciaList[1]
            SpinnerText(
              label = "Tipo Denuncia Verificada",
              list = tipoDenunciaList,
              preselected = preselected,
              onSelectionChanged = { selected -> /* do something with selected */ }
            )
          }
        }
      }
      Spacer(modifier = Modifier.padding(5.dp))
      Box(
        modifier = modifier
          .background(color = Color.White)
          .fillMaxSize()
      ) {
        MaterialTheme {
          if (tipoInstalacionElectricaAfectadaList.isNotEmpty()) {
            val preselected = tipoInstalacionElectricaAfectadaList[1]
            SpinnerText(
              label = "Tipo Instalción",
              list = tipoInstalacionElectricaAfectadaList,
              preselected = preselected,
              onSelectionChanged = { selected -> /* do something with selected */ }
            )
          }
        }

      }
      Spacer(modifier = Modifier.padding(5.dp))
      Box(
        modifier = modifier
          .background(color = Color.White)
          .fillMaxSize()
      ) {
        MaterialTheme {
          if (tipoInstalacionAfectadaList.isNotEmpty()) {
            val preselected = tipoInstalacionAfectadaList[1]
            SpinnerText(
              label = "Instalación Afectada",
              list = tipoInstalacionAfectadaList,
              preselected = preselected,
              onSelectionChanged = { selected -> /* do something with selected */ }
            )
          }

        }

      }
      Spacer(modifier = Modifier.padding(5.dp))
      Box(
        modifier = modifier
          .background(color = Color.White)
          .fillMaxSize()
      ) {
        MaterialTheme {
          if (tipoEquipoProteccionManiobraList.isNotEmpty()) {
            val preselected = tipoEquipoProteccionManiobraList[1]
            SpinnerText(
              label = "Punto Maniobra",
              list = tipoEquipoProteccionManiobraList,
              preselected = preselected,
              onSelectionChanged = { selected -> /* do something with selected */ }
            )
          }
        }
      }
      Spacer(modifier = Modifier.padding(5.dp))
      Box(
        modifier = modifier
          .background(color = Color.White)
          .fillMaxSize()
      ) {
        MaterialTheme {
          if (tipoManiobraCapacidadList.isNotEmpty()) {
            val preselected = tipoManiobraCapacidadList[1]

            SpinnerText(
              label = "Capacidad",
              list = tipoManiobraCapacidadList,
              preselected = preselected,
              onSelectionChanged = { selected -> /* do something with selected */ }
            )
          }
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
          if(causaAveriaList.isNotEmpty()){
            val preselected = causaAveriaList[1]
            SpinnerText(
              label = "Causa Avería",
              list=causaAveriaList,
              preselected = preselected,
              onSelectionChanged = { selected -> /* do something with selected */ }
            )
          }
        }
      }
      Spacer(modifier = Modifier.padding(5.dp))
      Box(
        modifier = modifier
          .background(color = Color.White)
          .fillMaxSize()
      ) {
        MaterialTheme {
          if(solucionAveriaList.isNotEmpty()){
            val preselected = solucionAveriaList[1]
            SpinnerText(
              label = "Acciones Realizadas",
              list = solucionAveriaList,
              preselected = preselected,
              onSelectionChanged = { selected -> /* do something with selected */ }
            )
          }

        }

      }
      Spacer(modifier = Modifier.padding(5.dp))
      Box(
        modifier = modifier
          .background(color = Color.White)
          .fillMaxSize()
      ) {
        MaterialTheme {

          if(solucionInterrupcionList.isNotEmpty()){
            val preselected = solucionInterrupcionList[1]
            SpinnerText(
              label = "Tipo Solución",
              list = solucionInterrupcionList,
              preselected = preselected,
              onSelectionChanged = { selected -> /* do something with selected */ }
            )

          }
        }

      }
      Spacer(modifier = Modifier.padding(5.dp))
      Box(
        modifier = modifier
          .background(color = Color.White)
          .fillMaxSize()
      ) {
        MaterialTheme {
          if(tipoAreaIntervencionList.isNotEmpty()){
            val preselected = tipoAreaIntervencionList[1]
            SpinnerText(
              label = "Requiere intervención",
              list = tipoAreaIntervencionList,
              preselected = preselected,
              onSelectionChanged = { selected -> /* do something with selected */ }
            )
          }

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