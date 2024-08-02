package master_provider_else.reclamos.view.screen.ap

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import master_provider_else.reclamos.R
import master_provider_else.reclamos.navigation.AppScreens
import master_provider_else.reclamos.ui.theme.view.component.CallPhone
import master_provider_else.reclamos.ui.theme.view.component.CardListItem

@Preview(showBackground = true)
@Composable
private fun Hola() {
  val navController = rememberNavController()
  AlumbradolistaItem(navController)
}


@Composable
fun AlumbradolistaItem(navController: NavController) {

  val context = LocalContext.current
  Column(
    modifier = Modifier
      .fillMaxWidth()
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 5.dp),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Column {
        CardListItem(
          textTitle = "Reclamo:",
          textValue = "Código Reclamo",
          painterResource(id = R.drawable.ic_id),
          spacing=10.dp
        )
        CardListItem(
          textTitle = "Nombre:",
          textValue = "MARIA CRI",
          painterResource(id = R.drawable.ic_suministro),
          spacing=12.dp
        )
        CardListItem(
          textTitle = "Dirección:",
          textValue = "MZ A LOTE",
          painterResource(id = R.drawable.ic_home),
          spacing=8.dp
        )
        CardListItem(
          textTitle = "Descripción:",
          textValue = "XXXXXXXXXX",
          painterResource(id = R.drawable.ic_source)
        )
        CardListItem(
          textTitle = "Referencia:",
          textValue = "azsxdcfvghj",
          painterResource(id = R.drawable.ic_file),
          spacing=3.dp
        )
        Row(
          modifier = Modifier
            .fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Column(modifier = Modifier.width(210.dp)) {
            CardListItem(
              textTitle = "Ruta:",
              textValue = "12323445670000",
              painterResource(id = R.drawable.ic_place),
              spacing = 22.dp
            )
          }
          Column(modifier = Modifier.width(170.dp)) {
            CardListItem(
              textTitle = "SED:",
              textValue = "34300543",
              painterResource(id = R.drawable.ic_sed)
            )
          }
        }
      }
      Column(modifier = Modifier
        .padding(top = 5.dp).border(width = 1.dp,color=Color.Red)
        .clickable {
         // navController.navigate(route = AppScreens.LocationMap.createRoute("AP",""))
        }) {
        Image(
          painter = painterResource(id = R.drawable.pin_celeste),
          contentDescription = "pin celeste",
          modifier = Modifier.size(50.dp)
        )
      }
    }

    Column() {
      Row {
        CardListItem(
          textTitle = "Celular:",
          textValue = "Celular reclamo",
          painterResource(id = R.drawable.ic_call),
          spacing = 15.dp
        )
        Row() {
          IconButton(
            onClick = {
              CallPhone(context, "+51999000999")
            }, modifier = Modifier
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
      CardListItem(
        textTitle = "Fecha Registro:",
        textValue = "wertyujiuytr",
        painterResource(id = R.drawable.ic_calendar)
      )

    }
  }
}