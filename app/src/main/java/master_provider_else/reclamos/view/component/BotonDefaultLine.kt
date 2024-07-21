package master_provider_else.reclamos.ui.theme.view.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BotonDefaultLine(
  modifier: Modifier = Modifier,
  title: String,
  onClick: () -> Unit,
  icon: ImageVector? = null
) {
  OutlinedButton(
    border = BorderStroke(width = 1.dp, color = Color.Gray),
    onClick = onClick,
    modifier = modifier.fillMaxWidth()

  ) {
    Row() {
      icon?.let {
        Icon(
          imageVector = it,
          contentDescription = "Icono",
          modifier = Modifier.padding(horizontal = 2.dp)
        )
      }
      Text(
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Gray
      )
    }
  }
}