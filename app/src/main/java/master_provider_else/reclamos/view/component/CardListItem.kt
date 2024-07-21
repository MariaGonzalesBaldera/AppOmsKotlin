package master_provider_else.reclamos.ui.theme.view.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import master_provider_else.reclamos.R

@Composable
fun CardListItem(textTitle: String, textValue: String, painter: Painter? = null) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier.padding(2.dp)
  ) {
    painter?.let {
      Icon(
        painter = it,
        contentDescription = "icono",
        modifier = Modifier.size(16.dp),
        tint = colorResource(id = R.color.colorPrimary)
      )
      Spacer(modifier = Modifier.padding(1.dp))
    }
    Spacer(
      modifier = Modifier.padding(1.dp)
    )
    Text(
      text = textTitle,
      color = colorResource(id = R.color.colorTextGray),
      fontWeight = FontWeight.Bold,
    )
    Spacer(
      modifier = Modifier.padding(
        horizontal = 4.dp,
        vertical = 10.dp
      )
    )
    Text(
      text = textValue,
      color = colorResource(id = R.color.colorTextGray),
    )
  }
}