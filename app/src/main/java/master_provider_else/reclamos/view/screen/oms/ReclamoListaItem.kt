package master_provider_else.reclamos.ui.theme.view.component

import androidx.compose.animation.animateContentSize
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import master_provider_else.reclamos.R
import master_provider_else.reclamos.data.database.entity.ReclamoEntity
import master_provider_else.reclamos.domain.model.ParamMap
import master_provider_else.reclamos.navigation.AppScreens
import master_provider_else.reclamos.view.ui.theme.toast
import master_provider_else.reclamos.viewModel.ClaimViewModel

@Composable
fun ReclamoListaItem(
  item: ReclamoEntity,
  isExpanded: Boolean,
  onItemClick: (String) -> Unit,
  navController: NavController,
  ap: String,
  estado: String,
  claimViewModel: ClaimViewModel
) {
  val context = LocalContext.current

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onItemClick(item.CodigoReclamo) }
      .padding(10.dp)
      .animateContentSize()
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
    ) {
      Column(modifier = Modifier.fillMaxWidth()) {
        Row(
          horizontalArrangement = Arrangement.SpaceBetween,
          modifier = Modifier
            .fillMaxWidth()
        ) {
          Text(
            text = item.NombreClaseReclamo,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = colorResource(id = R.color.colorPrimary),
            modifier = Modifier.padding(start = 15.dp)
          )
          Image(
            painter = painterResource(
              id = if (isExpanded) R.drawable.ic_expand_less else R.drawable.ic_expand_more
            ),
            contentDescription = if (isExpanded) "Icono de contraer" else "Icono de expandir"
          )
        }
        if (isExpanded) {


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
                  textValue = item.CodigoReclamo,
                  painterResource(id = R.drawable.ic_id),
                  spacing = 10.dp
                )
                CardListItem(
                  textTitle = "Nombre:",
                  textValue = item.NombreSuministro,
                  painterResource(id = R.drawable.ic_suministro),
                  spacing = 12.dp,
                  size = 12.sp
                )
                CardListItem(
                  textTitle = "Dirección:",
                  textValue = item.DireccionElectrica,
                  painterResource(id = R.drawable.ic_home),
                  spacing = 8.dp,
                  size = 12.sp

                )
                CardListItem(
                  textTitle = "Descripción:",
                  textValue = item.DescripcionReclamo,
                  painterResource(id = R.drawable.ic_source),
                  size = 12.sp

                )
                CardListItem(
                  textTitle = "Referencia:",
                  textValue = item.ReferenciaUbicacion,
                  painterResource(id = R.drawable.ic_file),
                  spacing = 4.dp,
                  size = 12.sp
                )
              }
              Column(modifier = Modifier
                .clickable {
                  //val result = claimViewModel.cambioEstadoViewModel(item, context, "2", "1")
                  //if (result) {
                  //val params = mapOf(
                  //  "latitud" to item.latitud,
                  //  "longitud" to item.longitud,
                  //  "codigoReclamo" to item.CodigoReclamo,
                  //  "codigoEstado" to item.CodigoEstadoReclamo,
                  //  "nombreClaseReclamo" to item.NombreClaseReclamo,
                  //  "sed" to item.CodigoSED,
                  // "ruta" to item.CodigoRutaSuministro,
                  //  "celular" to item.Celular,
                  //  "codigoDireccionElectrica" to item.CodigoDireccionElectrica
                  //)
                  val gson = Gson()

                  val paramsJson = ParamMap(
                    latitud = item.latitud.toDouble(),
                    longitud = item.longitud.toDouble(),
                    codigoReclamo = item.CodigoReclamo,
                    codigoEstadoReclamo = item.CodigoEstadoReclamo,
                    nombreClaseReclamo = item.NombreClaseReclamo,
                    codigoSED = item.CodigoSED,
                    codigoRutaSuministro = item.CodigoRutaSuministro,
                    celular = item.Celular,
                    codigoDireccionElectrica = item.CodigoDireccionElectrica
                  )
                  navController.navigate(
                    route = AppScreens.LocationMap.createRoute(
                      ap,
                      estado,
                      paramsJson
                    )
                  )
                  //} else {
                  //  context.toast("Ocurrió un error")
                  // }
                }) {
                val imageResource = when (item.CodigoEstadoReclamo) {
                  "2" -> R.drawable.pin_celeste
                  "3" -> R.drawable.pin_amarillo
                  "4" -> R.drawable.pin_verde
                  "5" -> R.drawable.pin_negro
                  else -> R.drawable.pin_default
                }
                Image(
                  painter = painterResource(id = imageResource),
                  contentDescription = "Estado del reclamo: ${item.CodigoEstadoReclamo}",
                  modifier = Modifier.size(45.dp)
                )
              }
            }
          }
          Column(
            modifier = Modifier
              .fillMaxWidth()
          ) {

            Row(
              modifier = Modifier
                .fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Column(modifier = Modifier.width(210.dp)) {
                CardListItem(
                  textTitle = "Ruta:",
                  textValue = item.CodigoRutaSuministro,
                  painterResource(id = R.drawable.ic_place),
                  spacing = 23.dp
                )
              }
              Column(modifier = Modifier.width(170.dp)) {
                CardListItem(
                  textTitle = "SED:",
                  textValue = item.CodigoSED,
                  painterResource(id = R.drawable.ic_sed)
                )
              }
            }
            if (item.Celular.isNotEmpty()) {
              Row {
                CardListItem(
                  textTitle = "Celular:",
                  textValue = item.Celular,
                  painter = painterResource(id = R.drawable.ic_call),
                  size = 15.sp,
                  spacing = 15.dp,
                  widthTextValue = 80.dp
                )
                Row {
                  IconButton(
                    onClick = {
                      CallPhone(context, item.Celular)
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
            }
            CardListItem(
              textTitle = "Fecha Registro:",
              textValue = (item.FechaRegistro), //formatDateTime
              painterResource(id = R.drawable.ic_calendar)
            )
          }
        }
      }

    }
  }
}
/*

@Preview(showBackground = true)
@Composable
private fun Muestrareclamo() {
  val navController = rememberNavController()
  val item = ReclamoArray(
    codigoSuministro = "CASAS PAREJA, RUT LORGIA SEBASTIAN",
    codigoSED = "0010826",
    codigoEstadoReclamo = "2",
    direccionElectrica = "APV. MINKA A - 7 SAN SEBASTIAN SEBASTIAN SEBASTIAN",
    nombreSuministros = "nombre Suministros SEBASTIAN",
    codigoReclamo = "2024001000000028182",
    nombreClaseReclamo = "FALTA DE SERVICIO EN EL PREDIO",
    codigoRutaSuministro = "0010610006085",
    celular = "999999999",
    latitud = "latitud",
    longitud = "longitud",
    descripcionReclamo = "SASASASASASA",
    referenciaUbicacion = "SASASASASASA",
    codigoDireccionElectrica = "codigoDireccionElectrica",
    fechaRegistro = "30 jul. 2024 09:34:00",
    sectorTipico = "sectorTipico",
    plazoDias = "plazoDias",
    fechaLimiteAtencion = "fechaLimiteAtencion",
    tipoReclamo = "tipoReclam"
  )
  ReclamoListaItem(
    item,
    true,
    { },
    navController,
    "0",
    "2"
  )
}*/
