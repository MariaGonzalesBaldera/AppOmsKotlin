package master_provider_else.reclamos.view.component

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import master_provider_else.reclamos.BotonDefault
import master_provider_else.reclamos.R
import master_provider_else.reclamos.ui.theme.view.component.CallPhone


@Composable
fun TopbarInfomacion(context: Context, titulo: String, subTitulo: String, phone: String) {
  Column(
    modifier = Modifier
      .background(colorResource(id = R.color.colorPrimary))
      .padding(5.dp)
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 4.dp)
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column(modifier = Modifier.padding(start = 10.dp)) {
            Icon(
              painter = painterResource(id = R.drawable.ic_back),
              contentDescription = "back",
              tint = Color.White
            )
          }
          Column(modifier = Modifier) {
            Text(
              text = titulo,
              fontSize = 17.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
            Text(
              maxLines = 1,
              text = subTitulo,
              color = Color.White
            )
          }
        }
      }
    }
    Divider(
      color = colorResource(id = R.color.colorAccent),
      thickness = 1.dp,
    )
    BotonDefault(
      title = "LLAMAR",
      icon = Icons.Default.Call,
      modifier = Modifier.fillMaxWidth(),
      onClick = {
        CallPhone(context, phone)
      })
    Divider(
      color = colorResource(id = R.color.colorAccent),
      thickness = 1.dp,
    )
  }
}