package master_provider_else.reclamos.view.screen.ap

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import master_provider_else.reclamos.R


@Preview(showBackground = true)
@Composable
private fun PrevActividadesNodo() {
  ActividadesNodo()
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ActividadesNodo() {
  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 60.dp)
  ) {

    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 24.dp)
          .border(1.dp, colorResource(id = R.color.colorTextGray), RoundedCornerShape(3.dp)
          )
      ) {
        FlowRow(
          modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
          horizontalArrangement = Arrangement.Center,
          verticalArrangement = Arrangement.Center
        ) {
          repeat(10) { index ->
            Box(
              modifier = Modifier
                .padding(4.dp)
                .border(1.dp, colorResource(id = R.color.colorGreen), RoundedCornerShape(3.dp))
                .padding(5.dp)
                .align(Alignment.CenterVertically)
            ) {
              Text(
                text = "Item " + (index+1),
                modifier = Modifier.align(Alignment.Center)
              )
            }
          }
        }
      }
    }
    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 10.dp)
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(5.dp))
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "Actividad",
              fontWeight = FontWeight.Bold,
              modifier = Modifier
                .weight(0.5f),
              textAlign = TextAlign.Center
            )
            Text(
              text = "Realizada",
              fontWeight = FontWeight.Bold,
              textAlign = TextAlign.Right,
              modifier = Modifier
                .weight(0.5f)
                .padding(end = 12.dp),

              )
          }
          Divider(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
          )
          Column(
            modifier = Modifier
              .fillMaxWidth()
          ) {
            repeat(10) { index ->
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(
                  text = "Inspeccion y deteccion de puntos apagados $index",
                  modifier = Modifier.weight(1f)
                )
                Checkbox(
                  checked = false, // or some state variable
                  onCheckedChange = { /* handle check change */ }
                )
              }
            }
          }
        }
      }
    }
  }
}
