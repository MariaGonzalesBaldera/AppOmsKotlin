package master_provider_else.reclamos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardItemContent(
    painter: Painter,
    title: String,
    descriptionImage: String,
    onCLick: () -> Unit
) {
    Column(
        modifier = Modifier
            .border(width = 1.dp, color = colorResource(id = R.color.colorPlatino))
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(180.dp)
                .width(230.dp)
                .padding(5.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = descriptionImage,
                Modifier.size(150.dp),
                contentScale = ContentScale.Fit
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(5.dp)
                .height(60.dp)
                .width(220.dp)
                .clickable { onCLick() }
                .background(
                    color = colorResource(id = R.color.colorPrimary),
                    shape = RoundedCornerShape(10)
                )
        ) {
            Text(
                text = title,
                color = colorResource(id = R.color.colorTextWhite),
                fontSize = 18.sp
            )
        }
    }
}