package master_provider_else.reclamos.ui.theme.view.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import master_provider_else.reclamos.R

@Composable
fun BotonDefaultLine(modifier: Modifier = Modifier, title: String, onClick: () -> Unit) {
  Button(
    colors = ButtonDefaults.buttonColors(
      containerColor = colorResource(id = R.color.colorAccent)
    ),
    shape = RoundedCornerShape(20),
    onClick = onClick,// { /* Acción al hacer clic en el botón */ },
    modifier = modifier
      .height(50.dp)

  ) {
    Text(
      text = title,
      fontSize = 16.sp,
      fontWeight = FontWeight.Bold,
      color = Color.White
    )
  }
}