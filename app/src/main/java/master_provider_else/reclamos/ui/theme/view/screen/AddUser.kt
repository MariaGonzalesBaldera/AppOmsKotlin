package master_provider_else.reclamos.ui.theme.view.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import master_provider_else.reclamos.BotonDefault
import master_provider_else.reclamos.TransparentTextField
import master_provider_else.reclamos.ui.theme.viewModel.UserViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddUser(navController: NavController, viewModel: UserViewModel) {
  Scaffold {
    FormUser(navController = navController, viewModel = viewModel)
  }
}

@Composable
fun FormUser(
  modifier: Modifier = Modifier,
  navController: NavController,
  viewModel: UserViewModel
) {
  val userValue = rememberSaveable { mutableStateOf("") }
  val passValue = rememberSaveable { mutableStateOf("") }
  val context = LocalContext.current
  var checkedState by remember { mutableStateOf(false) }
  var checkedStateValue by remember { mutableStateOf("0") }
  val coroutineScope = rememberCoroutineScope()


  LazyColumn(
    modifier = Modifier
      .padding(vertical = 20.dp, horizontal = 20.dp)
  ) {
    item {
      Box(
        modifier = modifier
            .background(color = Color.White)
            .fillMaxSize()
      ) {
        MaterialTheme {
        }

      }
      Spacer(modifier = Modifier.padding(5.dp))
      TransparentTextField(
        textFieldValue = userValue,
        textLabel = "Usuario",
        keyboardType = KeyboardType.Text,
        keyboardActions = KeyboardActions(
          onNext = {}),
        imeAction = ImeAction.Next
      )

      Spacer(modifier = Modifier.padding(5.dp))
      TransparentTextField(
        textFieldValue = passValue,
        textLabel = "Clave",
        keyboardType = KeyboardType.Text,
        keyboardActions = KeyboardActions(
          onNext = {}),
        imeAction = ImeAction.Next
      )
      Spacer(modifier = Modifier.padding(5.dp))
      Box {
        Row(
          modifier = Modifier
              .padding(horizontal = 10.dp)
              .fillMaxWidth()
              .background(color = Color.White)
              .clickable {
                  Toast
                      .makeText(context, "Row clicked", Toast.LENGTH_SHORT)
                      .show()
                  checkedState = !checkedState
              },
          verticalAlignment = Alignment.CenterVertically
        ) {
          Checkbox(
            checked = checkedState,
            onCheckedChange = {
              Toast.makeText(context, "Checkbox check", Toast.LENGTH_SHORT)
                .show()
              checkedState = it
            }
          )
          Text(text = "Recordar mis credenciales")
        }
      }
      Spacer(modifier = Modifier.padding(5.dp))

      BotonDefault(title = "Registrar",onClick= {
        if (checkedState) {
          checkedStateValue = "1"
        } else {
          checkedStateValue = "0"
        }
        //val user = Usuario(
        //  usuario = userValue.value,
        //  pass = passValue.value,
        //  cuadrilla = checkedStateValue
        //)
        coroutineScope.launch {
          //viewModel.addUsers(user)
          navController.popBackStack()
        }
      })
    }
  }

}