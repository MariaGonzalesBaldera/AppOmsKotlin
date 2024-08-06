package master_provider_else.reclamos.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import master_provider_else.reclamos.R
import master_provider_else.reclamos.domain.model.Material
import master_provider_else.reclamos.ui.theme.view.component.CardListItem

@Preview(showBackground = true)
@Composable
private fun ShowPrev() {
  val material = Material(
    material = "herramientas",
    unidad = "1",
    cantidad = "1"
  )
  MaterialItem(material,{})
}

@Composable
fun MaterialItem(material: Material, onDelete: (Material) -> Unit) {
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
      Column(modifier = Modifier.width(260.dp)) {

        CardListItem(
          textTitle = "Material:",
          textValue = material.material
        )
        CardListItem(
          textTitle = "Unidad:",
          textValue = material.unidad
        )
        CardListItem(
          textTitle = "Cantidad:",
          textValue = material.cantidad
        )

      }

      Column(
        modifier = Modifier
          .padding()
          .clickable {
            onDelete(material)
          }) {
        Image(
          painter = painterResource(id = R.drawable.marca_x),
          contentDescription = "Eliminar",
          modifier = Modifier.size(25.dp)
        )
      }

    }
    Divider(
      color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f),
      thickness = 1.dp,
      modifier = Modifier.padding(vertical = 8.dp)
    )
  }

}