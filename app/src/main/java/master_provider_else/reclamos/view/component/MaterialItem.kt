package master_provider_else.reclamos.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import master_provider_else.reclamos.R
import master_provider_else.reclamos.ui.theme.view.component.CardListItem

@Preview(showBackground = true)
@Composable
fun MaterialItem() {
  Column(
    modifier = Modifier
      .fillMaxWidth()
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Column {

        CardListItem(
          textTitle = "Material:",
          textValue = "material ejemplo"
        )
        CardListItem(
          textTitle = "Unidad:",
          textValue = "23"
        )
        CardListItem(
          textTitle = "Cantidad:",
          textValue = ""
        )
        Divider(
          color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f),
          thickness = 1.dp,
          modifier = Modifier.padding(vertical = 8.dp)
        )
      }
      Column(
        modifier = Modifier
          .padding(top = 5.dp)
          .clickable {
            //navController.navigate(route = AppScreens.LocationMap.route)
          }) {
        Image(
          painter = painterResource(id = R.drawable.marca_x),
          contentDescription = "Eliminar",
          modifier = Modifier.size(50.dp)
        )
      }
    }

  }
}