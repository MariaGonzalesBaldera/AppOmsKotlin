package master_provider_else.reclamos.ui.theme.view.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardListItem(textTitle: String, textValue: String) {
  Row(
  ) {
    Text(
      text = textTitle,
      color = Color.Gray,
      fontWeight = FontWeight.Bold,
      fontSize = 13.sp
    )
    Spacer(
      modifier = Modifier.padding(
        horizontal = 5.dp,
        vertical = 8.dp
      )
    )
    Text(
      text = textValue,
      color = Color.Gray,
      fontSize = 12.sp

    )
  }
}