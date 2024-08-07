package master_provider_else.reclamos

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
private fun ShowPrev() {
  CardTextImage(
    title = "Fecha Verificación",
    subTitle = "Seleccione la fecha",
    painter = painterResource(id = R.drawable.icono_date),
    descriptionImage = "Icono de calendario",
    onClick = { Log.e("", "") }
  )
}

@Composable
fun CardTextImage(
  title: String,
  subTitle: String,
  painter: Painter,
  descriptionImage: String,
  onClick: () -> Unit
) {
  Box(
    modifier = Modifier
      .background(Color.White)
      .fillMaxWidth()
      .clickable(onClick = onClick)
      .padding( 10.dp)
  ) {
    Column {
      Text(text = title)
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
          .fillMaxWidth()
      ) {
        Text(
          text = subTitle,
          color = Color.Gray,
        )
        Image(
          painter = painter, // painterResource(id = R.drawable.icono_date),
          contentDescription = descriptionImage,
          modifier = Modifier
            .size(30.dp)
            .align(Alignment.CenterVertically)
        )
      }
    }
  }
}