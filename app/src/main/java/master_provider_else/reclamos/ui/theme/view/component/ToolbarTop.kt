package master_provider_else.reclamos

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarTop(title:String) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.colorPrimary)
        ),
    title= {Text(
            text = title,
            color=Color.White,
            fontSize = 18.sp)}
    )
}