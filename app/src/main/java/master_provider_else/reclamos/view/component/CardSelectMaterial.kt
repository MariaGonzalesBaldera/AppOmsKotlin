package master_provider_else.reclamos.ui.theme.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import master_provider_else.reclamos.BotonDefault
import master_provider_else.reclamos.SpinnerText
import master_provider_else.reclamos.TransparentTextField

@Composable
fun CardSelectMaterial(onDismiss: () -> Unit) {
  Dialog(onDismissRequest = onDismiss) {
    Surface(
      shape = MaterialTheme.shapes.medium,
      modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
      color = Color.Black,
    ) {
      CardSelectMaterialContent(onDismiss)
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun showPrev() {
  CardSelectMaterialContent({})
}

@Composable
fun CardSelectMaterialContent(onDismiss: () -> Unit) {
  val unidadValue = remember { mutableStateOf("") }
  val cantidadValue = remember { mutableStateOf("1") }
  val focusManager = LocalFocusManager.current
  val entry1 = Pair("Key1", "Entry1")
  val entry2 = Pair("Key2", "Entry2")
  val entry3 = Pair("Key3", "Entry3")
  Box(modifier = Modifier.background(Color.White).height(400.dp)) {

    Column(modifier = Modifier.padding(10.dp)) {
      Text(
        text = "Selección de Material",
        modifier = Modifier
          .fillMaxWidth()
          .padding(5.dp),
        textAlign = TextAlign.Center,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
      )
      Spacer(modifier = Modifier.padding(5.dp))
      SpinnerText(label = "Material", list = listOf(entry1, entry2, entry3), preselected = entry1) {
      }
      Spacer(modifier = Modifier.padding(5.dp))
      TransparentTextField(
        textFieldValue = unidadValue,
        textLabel = "Unidad:",
        keyboardType = KeyboardType.Text,
        keyboardActions = KeyboardActions(
          onNext = {
            focusManager.moveFocus(FocusDirection.Down)
          }
        ),
        imeAction = ImeAction.Next
      )
      Spacer(modifier = Modifier.padding(5.dp))
      TransparentTextField(
        textFieldValue = cantidadValue,
        textLabel = "Cantidad:",
        keyboardType = KeyboardType.Number,
        keyboardActions = KeyboardActions(
          onNext = {
            focusManager.moveFocus(FocusDirection.Down)
          }
        ),
        imeAction = ImeAction.Next
      )
      Spacer(modifier = Modifier.width(5.dp))
      Row(
        Modifier
          .fillMaxSize()
          .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 30.dp),
          verticalArrangement = Arrangement.Bottom,
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Row(
            modifier = Modifier
              .padding(1.dp)
          ) {
            BotonDefault(
              title = "Agregar",
              modifier = Modifier
                .width(155.dp)
                .padding(horizontal = 5.dp),
              onClick = onDismiss)
            BotonDefaultLine(
              title = "Cancelar",
              modifier = Modifier
                .width(155.dp)
                .padding(horizontal = 5.dp),
              onClick = onDismiss)
          }
        }
      }
    }
  }
}