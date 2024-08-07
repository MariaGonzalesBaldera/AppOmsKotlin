package master_provider_else.reclamos.ui.theme.view.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import master_provider_else.reclamos.BotonDefault
import master_provider_else.reclamos.R
import master_provider_else.reclamos.TransparentTextField
import master_provider_else.reclamos.navigation.AppScreens
import master_provider_else.reclamos.view.ui.theme.toast
import master_provider_else.reclamos.viewModel.UserViewModel
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import master_provider_else.reclamos.ProgressDialogLoading

@Composable
fun LoginScreen(navController: NavController, userViewModel: UserViewModel) {
  val context = LocalContext.current
  val focusManager = LocalFocusManager.current
  var passwordVisibility by remember { mutableStateOf(false) }
  val userCredentials by userViewModel.userCredentials.collectAsState()
  val checkedStateValue = remember { (userCredentials?.cuadrilla ?: "0") }
  var checkedState by remember { mutableStateOf(checkedStateValue == "1") }
  val userValue = remember { mutableStateOf(userCredentials?.usuario ?: "") }
  val passwordValue = remember { mutableStateOf(userCredentials?.pass ?: "") }
  val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
  var dialogResponse by remember { mutableStateOf(false) }

  userViewModel.validModel.observe(lifecycleOwner) { value ->
    if (value == true) {
      dialogResponse = false
      navController.navigate(route = AppScreens.MenuActivityScreen.route)
    } else {
      dialogResponse = false
      context.toast("Credenciales inválidas")
    }
  }

  Scaffold(
    bottomBar = {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 50.dp),
        contentAlignment = Alignment.Center
      ) {
        BotonDefault(modifier = Modifier.width(150.dp), title = "Ingresar", onClick = {
          dialogResponse = true
          if (checkedState) {
            userViewModel.saveCredentials(
              userValue.value,
              passwordValue.value,
              "1"
            )
            Log.e("credenciales", userViewModel.userCredentials.value?.usuario ?: "")
          } else {
            userViewModel.clearCredentials()
          }
          userViewModel.onLoginView(userValue.value, passwordValue.value, context)
          navController.navigate(route = AppScreens.MenuActivityScreen.route)

        })
        ProgressDialogLoading(onDismiss = { dialogResponse = false }, showProgress = dialogResponse)
      }
    }
  ) { padding ->
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
    ) {
      item {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 30.dp)

        ) {
          Image(
            painter = painterResource(id = R.drawable.osm_sielse),
            contentDescription = "Logo de Sielse",
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 10.dp)

          )
          Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
              .fillMaxWidth()
              .padding(10.dp)
          ) {
            Text(
              text = "SIELSE Login",
              fontSize = 22.sp,
              fontWeight = FontWeight.Bold,
              color = Color.Gray,
              modifier = Modifier
                .padding(vertical = 40.dp)
            )
          }
          Column(
            modifier = Modifier
              .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
          ) {
            Column {
              TransparentTextField(
                textFieldValue = userValue,
                textLabel = "Usuario",
                keyboardType = KeyboardType.Text,
                keyboardActions = KeyboardActions(
                  onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                  }
                ),
                imeAction = ImeAction.Next
              )
              Spacer(
                modifier = Modifier
                  .background(Color.Transparent)
                  .padding(20.dp)
              )
              TransparentTextField(
                textFieldValue = passwordValue,
                textLabel = "Contraseña",
                keyboardType = KeyboardType.Password,
                keyboardActions = KeyboardActions(
                  onDone = {
                    focusManager.clearFocus()
                  }
                ),
                imeAction = ImeAction.Done,
                trailingIcon = {
                  IconButton(
                    onClick = {
                      passwordVisibility = !passwordVisibility
                    }
                  ) {

                    Icon(
                      painter = if (passwordVisibility) {
                        painterResource(id = R.drawable.ic_visibility)
                      } else {
                        painterResource(id = R.drawable.ic_visibility_off)
                      },
                      contentDescription = "Toggle Password Icon"
                    )
                  }
                },
                visualTransformation = if (passwordVisibility) {
                  VisualTransformation.None
                } else {
                  PasswordVisualTransformation()
                }
              )
            }
            Spacer(
              modifier = Modifier.padding(5.dp)
            )
            Row(
              modifier = Modifier

                .padding(vertical = 20.dp, horizontal = 10.dp)
                .fillMaxSize()
                .background(color = Color.White)
                .clickable {
                  checkedState = !checkedState
                },
              verticalAlignment = Alignment.CenterVertically
            ) {
              Checkbox(
                checked = checkedState,
                onCheckedChange = {
                  checkedState = !checkedState
                },
                colors = CheckboxDefaults.colors(colorResource(id = R.color.colorPrimary))
              )
              Text(text = "Recordar mis credenciales")
            }

          }

          /*
          Spacer(
            modifier = Modifier
              .background(Color.Transparent)
              .padding(20.dp)
          )
          Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
          ) {
            TextButton(
              onClick = { /* navController.navigate(route = AppScreens.AddUserScreen.route) */ },
              colors = ButtonDefaults.textButtonColors(
                contentColor = Color.Blue
              ),
              modifier = Modifier.padding(8.dp)
            ) {
              Text(
                text = "Registrarse",
                color = Color.Blue,
                style = LocalTextStyle.current.copy(textDecoration = TextDecoration.Underline)
              )
            }
          }
          * */
        }
      }
    }
  }
}
