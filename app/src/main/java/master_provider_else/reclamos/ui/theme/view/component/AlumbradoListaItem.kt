package master_provider_else.reclamos.ui.theme.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import master_provider_else.reclamos.R

@Preview(showBackground = true)
@Composable()
fun AlumbradoListaItem() {
  Column {
    Row() {
      Column {
        Text(
          text = "Clase Alumbrado: Nombre Clase Alumbrado",
          modifier = Modifier
            .padding(top = 5.dp),
          fontWeight = FontWeight.Bold,
          color = colorResource(id = R.color.colorAccent)
        )
        CardListItem(textTitle = "Reclamo:", textValue = "Código Reclamo")
        CardListItem(textTitle = "Nombre:", textValue = "Nombre Suministro XXXXXXXXXXXXXXXXX")
        CardListItem(textTitle = "Dirección:", textValue = "")
        CardListItem(textTitle = "Descripción:", textValue = "XXXXXXXXXXXXXXXXX")
        CardListItem(textTitle = "Referencia:", textValue = "")
        CardListItem(textTitle = "Ruta:", textValue = "Código Ruta")
        CardListItem(textTitle = "SED:", textValue = "Código SED")
      }
      Column(modifier = Modifier.padding(top = 5.dp)) {
        Image(
          painter = painterResource(id = R.drawable.pin_celeste),
          contentDescription = "pin celeste"
        )
      }
    }

    Row(modifier = Modifier) {
      Column {
        Row {
          CardListItem(textTitle = "Celular:", textValue = "Celular reclamo")
          Row() {
            IconButton(
              onClick = {}, modifier = Modifier
                .padding(start = 5.dp)
                .width(30.dp)
                .height(20.dp)
            ) {
              Image(
                painter = painterResource(id = R.drawable.icono_llamar_blanco),
                contentDescription = "Icono llamada",
                modifier = Modifier
                  .background(
                    color = colorResource(id = R.color.colorPrimary),
                    shape = RoundedCornerShape(20)
                  )
                  .size(30.dp)
                  .padding(5.dp)
              )
            }
          }
        }
        CardListItem(textTitle = "Fecha Registro:", textValue = "")
        CardListItem(textTitle = "Plazo:", textValue = "")
        CardListItem(textTitle = "Fecha Limite Atención:", textValue = "")
        CardListItem(textTitle = "Sector Típico:", textValue = "")
      }
    }

  }
}