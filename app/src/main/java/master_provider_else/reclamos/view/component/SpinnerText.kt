package master_provider_else.reclamos

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp


@Composable
fun SpinnerText(
  label: String,
  list: List<Pair<String, String>>,
  preselected: Pair<String, String>,
  onSelectionChanged: (selection: Pair<String, String>) -> Unit
) {
  var selected by remember { mutableStateOf(preselected) }
  var expanded by remember { mutableStateOf(false) }  // initial value
  Box(
    modifier = Modifier.padding(5.dp)
  ) {
    Column {
      OutlinedTextField(
        value = (selected.second),
        onValueChange = { },
        label = { Text(text = label) },
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = { Icon(Icons.Outlined.ArrowDropDown, null) },
        readOnly = true,

        )
      DropdownMenu(
        modifier = Modifier
          .background(colorResource(id = R.color.colorPlatino)).fillMaxWidth(),
        expanded = expanded,

        onDismissRequest = { expanded = false },
      ) {
        list.forEach { entry ->

          DropdownMenuItem(
            modifier = Modifier,
            onClick = {
              selected = entry
              expanded = false
              onSelectionChanged(entry)
            },
            colors = MenuDefaults.itemColors(
              textColor = colorResource(id = R.color.colorText),
            ),
            text = {
              Text(
                text = (entry.second),
                modifier = Modifier
                  .wrapContentWidth()
                  .align(Alignment.Start)
              )
            }
          )
        }
      }
    }

    Spacer(
      modifier = Modifier
        .matchParentSize()
        .background(Color.Transparent)
        .padding(10.dp)
        .clickable(
          onClick = { expanded = !expanded }
        )
    )
  }

}
