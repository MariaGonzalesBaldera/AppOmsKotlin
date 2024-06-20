package master_provider_else.reclamos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun LinkButton() {
    TextButton(
        onClick = {},
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