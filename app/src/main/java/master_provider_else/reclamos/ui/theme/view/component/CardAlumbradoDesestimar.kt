package master_provider_else.reclamos.ui.theme.view.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import master_provider_else.reclamos.BotonDefault
import master_provider_else.reclamos.SpinnerText


@Preview(showBackground = true)
@Composable
fun CardAlumbradoDesestimar() {
  val entry1 = Pair("Key1", "Entry1")
  val entry2 = Pair("Key2", "Entry2")
  val entry3 = Pair("Key3", "Entry3")
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .padding(10.dp)
  ) {
    Column() {
      BotonDefault(
        title = "LLAMAR",
        icon = Icons.Default.Call,
        modifier = Modifier.fillMaxWidth(),
        onClick = {})
      Spacer(modifier = Modifier.padding(5.dp))
      SpinnerText(
        label = "Motivo Desestimación",
        list = listOf(entry1, entry2, entry3),
        preselected = entry1
      ) {}
      Spacer(modifier = Modifier.padding(5.dp))
      Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        BotonDefault(title = "Aceptar", onClick = {})
      }
    }
  }
}