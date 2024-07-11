package master_provider_else.reclamos.ui.theme.view.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import master_provider_else.reclamos.BotonDefault

@Composable
fun BottomBar(title: String, onClick: () -> Unit) {
  Row(
    Modifier
      .fillMaxWidth()
      .padding(5.dp),
    horizontalArrangement = Arrangement.End,
    verticalAlignment = Alignment.Bottom
  ) {
    BotonDefault(onClick = onClick, title = title)
  }
}