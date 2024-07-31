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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import master_provider_else.reclamos.R

@Preview(showBackground = true)
@Composable
private fun showPrev() {
  CardListItem("hola", "hola", painterResource(id = R.drawable.ic_file))
}

@Composable
fun CardListItem(
  textTitle: String,
  textValue: String,
  painter: Painter? = null,
  size: TextUnit = 15.sp,
  letterSpacing: TextUnit = (-1).sp,
  spacing: Dp=1.dp
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier.padding(horizontal = 0.dp, vertical = 2.dp)
  ) {
    painter?.let {
      Icon(
        painter = it,
        contentDescription = "icono",
        modifier = Modifier.size(12.dp),
        tint = colorResource(id = R.color.colorPrimary)
      )
    }
    Spacer(
      modifier = Modifier.padding(0.dp)
    )
    Text(
      text = textTitle,
      color = colorResource(id = R.color.colorTextGray),
      fontWeight = FontWeight.Bold,
    )
    Spacer(
      modifier = Modifier.padding(
        horizontal = spacing,
        vertical = 10.dp
      )
    )
    Text(
      text = textValue,
      color = colorResource(id = R.color.colorTextGray),
      fontSize = size,
      letterSpacing = letterSpacing
    )
  }
}