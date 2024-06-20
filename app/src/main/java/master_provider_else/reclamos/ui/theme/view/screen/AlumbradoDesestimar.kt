package master_provider_else.reclamos.ui.theme.view.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import master_provider_else.reclamos.BotonDefault
import master_provider_else.reclamos.ToolbarTop
import master_provider_else.reclamos.R
import master_provider_else.reclamos.SpinnerText

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AlumbradoDesestimar(navController: NavController){
    Scaffold (
        topBar = { ToolbarTop(title = "Alumbrado Desestimar") }
    ) {padding->
        ShowAlumbradoDesestimar(
            navController =  navController,
            modifier = Modifier.padding(padding))
    }
}
@Preview(showBackground = true)
@Composable
private fun ShowAlumnbrado() {
    var navController =  rememberNavController();
    AlumbradoDesestimar(navController)
}
@Composable
fun ShowAlumbradoDesestimar(
    navController:NavController,
    modifier: Modifier) {
    Box(
        modifier = modifier,
    ) {
        Column {
            Button(
                shape = RectangleShape,
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R .color.colorPrimary)),
                onClick = {
                    @Suppress("MissingPermission")
                    //if (intent.getStringExtra("Celular")?.trim().isNullOrEmpty()) {
                    //    Toast.makeText(baseContext, "No existe el número a marcar", Toast.LENGTH_LONG).show()
                    //} else {
                    if (isPermissionGranted()) {
                        val intent = Intent(Intent.ACTION_CALL)
                        intent.data = Uri.parse("tel:${intent.getStringExtra("Celular")?.trim()}")
                        //Toast.makeText(context, "no hay permiso", Toast.LENGTH_LONG).show()

                        //    startActivity(intent)
                    } else {
                        //Toast.makeText(context, "no hay permiso", Toast.LENGTH_LONG).show()
                    }
                    //}
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)

            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        tint = Color.White,
                        painter = painterResource(id = R.drawable.icono_llamar_blanco),
                        contentDescription = "Icono de llamada",
                        modifier = Modifier
                            .height(15.dp)
                            .width(15.dp)
                    )
                    Text(
                        text = "LLAMAR",
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal=8.dp)
                    )
                }
            }
            Text(
                text = "Motivo Desestimación",
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                modifier = modifier
            )
            MaterialTheme {

                val entry1 = Pair("Key1", "Entry1")
                val entry2 = Pair("Key2", "Entry2")
                val entry3 = Pair("Key3", "Entry3")

                SpinnerText(
                    label="",
                    listOf(entry1, entry2, entry3),
                    preselected = entry2,
                    onSelectionChanged = { selected -> /* do something with selected */ }
                )
            }
            Spacer(modifier = modifier.padding(10.dp))

            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                BotonDefault(title="Aceptar"){}
            }
        }
    }
}

fun isPermissionGranted(): Boolean {
    return if (Build.VERSION.SDK_INT >= 23) {
        if (0 === PackageManager.PERMISSION_GRANTED) {
            Log.v("TAG", "Permission is granted")
            true
        } else {
            Log.v("TAG", "Permission is revoked")
            /*ActivityCompat.requestPermissions(
                (this as AlumbradoDesestimar?)!!, arrayOf(
                    Manifest.permission.CALL_PHONE
                ), 1
            )*/
            false
        }
    } else { //permission is automatically granted on sdk<23 upon installation
        Log.v("TAG", "Permission is granted")
        true
    }
}