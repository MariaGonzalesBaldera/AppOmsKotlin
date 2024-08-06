package master_provider_else.reclamos.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import master_provider_else.reclamos.R
import master_provider_else.reclamos.domain.model.Material
import master_provider_else.reclamos.ui.theme.view.component.CardSelectMaterial

@Preview(showBackground = true)
@Composable
private fun ShowPrev() {
  ComponentMaterial()
}

@Composable
fun ComponentMaterial() {
  val showDialog = remember { mutableStateOf(false) }
  val materials = remember { mutableStateListOf<Material>() }

  //LaunchedEffect(Unit) {
  //codigoReclamo: String, tipo: Array<String>
  //  claimViewModel.datosMaterialesList(codigoReclamo.tipo)
  //}

  fun addMaterial(material: Material) {
    materials.add(material)
  }

  fun removeMaterial(material: Material) {
    materials.remove(material)
  }

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 60.dp)
  ) {
    item {
      Column(
        modifier = Modifier
          .background(color = Color.White)
          .fillMaxWidth()
          .padding(bottom = 10.dp)
      ) {
        Row(
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier
            .fillMaxWidth()
        ) {
          Column {
            Text(text = "Material :", fontWeight = FontWeight.Bold, fontSize = 16.sp)
          }
          Column(modifier = Modifier.width(180.dp)) {
            TextField(
              enabled = false,
              value = "", onValueChange = {},
              colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent
              )
            )
          }
          Column() {
            Image(
              painter = painterResource(id = R.drawable.mas),
              contentDescription = "agregar",
              modifier = Modifier
                .size(50.dp)
                .clickable { showDialog.value = true }
            )
            if (showDialog.value) {
              CardSelectMaterial(onDismiss = {
                showDialog.value = false
              }) { material, unidad, cantidad ->
                addMaterial(Material(material, unidad, cantidad))
                showDialog.value = false
              }
            }
          }
        }
      }
    }
    items(materials) { material ->
      MaterialItem(material = material, onDelete = { removeMaterial(it) })
    }
  }
}