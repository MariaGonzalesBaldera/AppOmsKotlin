package master_provider_else.reclamos.ui.theme.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import master_provider_else.reclamos.CardTextImage
import master_provider_else.reclamos.R
import master_provider_else.reclamos.SpinnerText
import master_provider_else.reclamos.TransparentTextField


@Preview(showBackground = true)
@Composable
private fun TestView() {
    ReclamosRegistroInfFichaTecnica()
}

@Composable
fun  ReclamosRegistroInfFichaTecnica(modifier: Modifier = Modifier) {
    val circuitoBTValue = rememberSaveable() { mutableStateOf("") }
    val sedValue = rememberSaveable() { mutableStateOf("") }
    val observacionValue = rememberSaveable() { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    LazyColumn(modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp)) {
        item {
            Box(
                modifier = modifier
                    .background(color = Color.White)
                    .fillMaxSize()
            ) {
                MaterialTheme {

                    val entry1 = Pair("Key1", "Entry1")
                    val entry2 = Pair("Key2", "Entry2")
                    val entry3 = Pair("Key3", "Entry3")

                    SpinnerText(
                        label = "Tipo Denuncia Verificada" ,
                        listOf(entry1, entry2, entry3),
                        preselected = entry2,
                        onSelectionChanged = { selected -> /* do something with selected */ }
                    )
                }

            }
            Spacer(modifier = Modifier.padding(5.dp))
            Box(
                modifier = modifier
                    .background(color = Color.White)
                    .fillMaxSize()
            ) {
                MaterialTheme {

                    val entry1 = Pair("Key1", "Entry1")
                    val entry2 = Pair("Key2", "Entry2")
                    val entry3 = Pair("Key3", "Entry3")

                    SpinnerText(
                        label = "Tipo Instalción",
                        listOf(entry1, entry2, entry3),
                        preselected = entry2,
                        onSelectionChanged = { selected -> /* do something with selected */ }
                    )
                }

            }
            Spacer(modifier = Modifier.padding(5.dp))
            Box(
                modifier = modifier
                    .background(color = Color.White)
                    .fillMaxSize()
            ) {
                MaterialTheme {

                    val entry1 = Pair("Key1", "Entry1")
                    val entry2 = Pair("Key2", "Entry2")
                    val entry3 = Pair("Key3", "Entry3")

                    SpinnerText(
                        label = "Instalación Afectada",
                        listOf(entry1, entry2, entry3),
                        preselected = entry2,
                        onSelectionChanged = { selected -> /* do something with selected */ }
                    )
                }

            }
            Spacer(modifier = Modifier.padding(5.dp))
            Box(
                modifier = modifier
                    .background(color = Color.White)
                    .fillMaxSize()
            ) {
                MaterialTheme {

                    val entry1 = Pair("Key1", "Entry1")
                    val entry2 = Pair("Key2", "Entry2")
                    val entry3 = Pair("Key3", "Entry3")

                    SpinnerText(
                        label = "Punto Maniobra",
                        listOf(entry1, entry2, entry3),
                        preselected = entry2,
                        onSelectionChanged = { selected -> /* do something with selected */ }
                    )
                }

            }
            Spacer(modifier = Modifier.padding(5.dp))
            Box(
                modifier = modifier
                    .background(color = Color.White)
                    .fillMaxSize()
            ) {
                MaterialTheme {

                    val entry1 = Pair("Key1", "Entry1")
                    val entry2 = Pair("Key2", "Entry2")
                    val entry3 = Pair("Key3", "Entry3")

                    SpinnerText(
                        label = "Capacidad",
                        listOf(entry1, entry2, entry3),
                        preselected = entry2,
                        onSelectionChanged = { selected -> /* do something with selected */ }
                    )
                }

            }
            Spacer(modifier = Modifier.padding(5.dp))
            TransparentTextField(
                textFieldValue = circuitoBTValue,
                textLabel = "Circuito BT",
                keyboardType = KeyboardType.Text,
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                imeAction = ImeAction.Next
            )

            Spacer(modifier = Modifier.padding(5.dp))
            TransparentTextField(
                textFieldValue = sedValue,
                textLabel = "SED",
                keyboardType = KeyboardType.Text,
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                imeAction = ImeAction.Next
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardTextImage(
                title = "",
                subTitle = "Seleccione Fecha",
                painter = painterResource(id = R.drawable.icono_date),
                descriptionImage = "Icono de calendario"
            )
            CardTextImage(
                title = "",
                subTitle = "Seleccione Hora",
                painter = painterResource(id = R.drawable.icono_hour),
                descriptionImage = "Icono de calendario"
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Box(
                modifier = modifier
                    .background(color = Color.White)
                    .fillMaxSize()
            ) {
                MaterialTheme {

                    val entry1 = Pair("Key1", "Entry1")
                    val entry2 = Pair("Key2", "Entry2")
                    val entry3 = Pair("Key3", "Entry3")

                    SpinnerText(
                        label = "Causa Avería",
                        listOf(entry1, entry2, entry3),
                        preselected = entry2,
                        onSelectionChanged = { selected -> /* do something with selected */ }
                    )
                }

            }
            Spacer(modifier = Modifier.padding(5.dp))
            Box(
                modifier = modifier
                    .background(color = Color.White)
                    .fillMaxSize()
            ) {
                MaterialTheme {

                    val entry1 = Pair("Key1", "Entry1")
                    val entry2 = Pair("Key2", "Entry2")
                    val entry3 = Pair("Key3", "Entry3")

                    SpinnerText(
                        label = "Acciones Realizadas",
                        listOf(entry1, entry2, entry3),
                        preselected = entry2,
                        onSelectionChanged = { selected -> /* do something with selected */ }
                    )
                }

            }
            Spacer(modifier = Modifier.padding(5.dp))
            Box(
                modifier = modifier
                    .background(color = Color.White)
                    .fillMaxSize()
            ) {
                MaterialTheme {

                    val entry1 = Pair("Key1", "Entry1")
                    val entry2 = Pair("Key2", "Entry2")
                    val entry3 = Pair("Key3", "Entry3")

                    SpinnerText(
                        label = "Tipo Solución",
                        listOf(entry1, entry2, entry3),
                        preselected = entry2,
                        onSelectionChanged = { selected -> /* do something with selected */ }
                    )
                }

            }
            Spacer(modifier = Modifier.padding(5.dp))
            Box(
                modifier = modifier
                    .background(color = Color.White)
                    .fillMaxSize()
            ) {
                MaterialTheme {

                    val entry1 = Pair("Key1", "Entry1")
                    val entry2 = Pair("Key2", "Entry2")
                    val entry3 = Pair("Key3", "Entry3")

                    SpinnerText(
                        label = "Requiere intervención",
                        listOf(entry1, entry2, entry3),
                        preselected = entry2,
                        onSelectionChanged = { selected -> /* do something with selected */ }
                    )
                }

            }
            Spacer(modifier = Modifier.padding(5.dp))
            TransparentTextField(
                textFieldValue = observacionValue,
                textLabel = "Observación de solución",
                keyboardType = KeyboardType.Text,
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                imeAction = ImeAction.Next
            )
        }
    }
}