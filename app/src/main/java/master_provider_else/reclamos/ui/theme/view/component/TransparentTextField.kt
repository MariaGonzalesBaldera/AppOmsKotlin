package master_provider_else.reclamos

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TransparentTextField(
  modifier: Modifier = Modifier,
  textFieldValue: MutableState<String>,
  textLabel: String,
  maxChar: Int? = null,
  capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
  keyboardType: KeyboardType,
  keyboardActions: KeyboardActions,
  imeAction: ImeAction,
  trailingIcon: @Composable() (() -> Unit)? = null,
  visualTransformation: VisualTransformation = VisualTransformation.None
) {
  var isValid by remember { mutableStateOf(true) }
  val errorColor = Color.Red
  val primaryColor = colorResource(id = R.color.colorPrimary)

  TextField(
    modifier = modifier
      .fillMaxWidth()
      .padding(start = 10.dp, end = 10.dp),
    value = textFieldValue.value.take(maxChar ?: 40),
    onValueChange = { value ->
      textFieldValue.value = value
      isValid = value.isNotEmpty()
    },
    label = {
      Text(text = textLabel)
    },
    isError = !isValid,
    trailingIcon = trailingIcon,
    keyboardOptions = KeyboardOptions(
      capitalization = capitalization,
      keyboardType = keyboardType,
      imeAction = imeAction
    ),
    keyboardActions = keyboardActions,
    visualTransformation = visualTransformation,
    colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
      backgroundColor = Color.Transparent,
      focusedLabelColor = primaryColor,
      unfocusedIndicatorColor = primaryColor,
      focusedIndicatorColor = primaryColor,
      cursorColor = primaryColor,
      errorLabelColor = errorColor,
      errorLeadingIconColor = errorColor,
      errorIndicatorColor = errorColor,
      errorCursorColor = errorColor,
      errorTrailingIconColor = errorColor
    )
  )
  if (!isValid) {
    Text(
      text = "Please enter valid text", color = errorColor,
      modifier = Modifier.padding(start = 10.dp, end = 10.dp)
    )
  }
}

@Preview(showBackground = true)
@Composable
private fun ShowPrev() {
  val userValue = remember { mutableStateOf("") }
  val focusManager = LocalFocusManager.current
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

}