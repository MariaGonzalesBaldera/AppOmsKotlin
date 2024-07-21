package master_provider_else.reclamos.ui.theme.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import master_provider_else.reclamos.R
import master_provider_else.reclamos.navigation.AppScreens


@Composable()
fun ReclamoListaItem(navController: NavController) {

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
          painterResource(id = R.drawable.ic_id)
        )
        CardListItem(
          textTitle = "Nombre:",
          textValue = "Nombre Suministro XXXXXXX",
          painterResource(id = R.drawable.ic_suministro)
        )
        CardListItem(
          textTitle = "Dirección:",
          textValue = "",
          painterResource(id = R.drawable.ic_home)
        )
        CardListItem(
          textTitle = "Descripción:",
          textValue = "XXXXXXXXXXXXXXXXX",
          painterResource(id = R.drawable.ic_source)
        )
        CardListItem(
          textTitle = "Referencia:",
          textValue = "",
          painterResource(id = R.drawable.ic_file)
        )
        CardListItem(
          textTitle = "Ruta:",
          textValue = "Código Ruta",
          painterResource(id = R.drawable.ic_place)
        )
        CardListItem(
          textTitle = "SED:",
          textValue = "Código SED",
          painterResource(id = R.drawable.ic_sed)
        )
      }
      Column(modifier = Modifier
        .padding(top = 5.dp)
        .clickable {
          navController.navigate(route = AppScreens.LocationMap.createRoute("reclamo"))
        }) {
        Image(
          painter = painterResource(id = R.drawable.pin_celeste_2),
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
          painterResource(id = R.drawable.ic_call)
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
        textValue = "",
        painterResource(id = R.drawable.ic_calendar)
      )
      CardListItem(
        textTitle = "Plazo:",
        textValue = "",
        painterResource(id = R.drawable.ic_outbound)
      )
      CardListItem(
        textTitle = "Fecha Limite Atención:",
        textValue = "",
        painterResource(id = R.drawable.ic_calendar)
      )
      CardListItem(
        textTitle = "Sector Típico:",
        textValue = "",
        painterResource(id = R.drawable.ic_group)
      )
    }
  }
}


@Preview(showBackground = true)
@Composable
private fun ShowAlumbrado() {
  val navController = rememberNavController()
  ReclamoListaItem(navController)
}