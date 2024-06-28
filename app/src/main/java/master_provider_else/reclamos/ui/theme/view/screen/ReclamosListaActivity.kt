package master_provider_else.reclamos.ui.theme.view.screen

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import master_provider_else.reclamos.BotonDefault
import master_provider_else.reclamos.R
import master_provider_else.reclamos.ToolbarTop
import master_provider_else.reclamos.ui.theme.toast
import master_provider_else.reclamos.ui.theme.viewModel.UserViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ReclamosListaActivity(
  navController: NavController,
  userViewModel: UserViewModel
) {
  val username = userViewModel.userPreferences.getUserCredentials()?.nombreUsuario ?: "usuario"

  Surface(color = MaterialTheme.colors.background) {
    Scaffold(
      topBar = { ToolbarTop(title = username) }
    ) { padding ->
      ContentTabItem(
        navController,
        modifier = Modifier
          .padding(padding),
        userViewModel
      )
    }
  }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ContentTabItem(navController: NavController, modifier: Modifier, viewModel: UserViewModel) {
  var tabIndex by remember { mutableIntStateOf(0) }
  val tabs = listOf("Programado", "Ejecutado")
    val context = LocalContext.current

  @Composable
  fun EjecutadoScreen(context: Context, viewModel: UserViewModel) {
    Column(
      Modifier
        .fillMaxWidth()
        .height(500.dp),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(text = "Programado", fontSize = 25.sp)
      //ObtenerReclamo(context = context, viewModel)
    }
  }

  @Composable
  fun ProgramadoScreen() {
    Column(
      Modifier
        .fillMaxWidth()
        .height(500.dp),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(text = "Ejecutado", fontSize = 25.sp)
    }
  }
  /*

    var tipoListaReclamos: String by mutableStateOf("")
    val arrayReclamo: SnapshotStateList<Reclamo> = mutableStateListOf()
    val userState = viewModel.state
    var showDialog by remember { mutableStateOf(false) }
    var isConnected by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(context) {
      isConnected = isOnline(context)
    }

    if (showDialog) {
      ProgressDialogLoading { showDialog = false }
    }

    @Composable
    fun DeleteDB(context: Context, viewModel: UserViewModel) {
      try {
        val listUser: List<Usuario> = viewModel.getUsersDelete();
        viewModel.addMultiplesUsers(listUser)
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }

    //falta programar
    @Composable
    fun PeriodicNotificationTask(context: Context, interval: Long) {
      val scope = rememberCoroutineScope()

      LaunchedEffect(Unit) {
        while (true) {
          delay(interval)
          sendNotification(context)
        }
      }
    }

    fun descargarDatosFichaTecnica(codigoReclamo: String) {
      val url = SMConfig.HTTP_SCHEME + SMConfig.SERVER + "getReportClaim"
      val jsonObjectRequest = JsonObjectRequest(
        Request.Method.GET,
        url,
        JSONObject(),
        { response ->
          try {
            val respuesta = response.getJSONObject("Respuesta")

            if (respuesta.getInt("error") != 0) {
              Toast.makeText(context, respuesta.getString("message"), Toast.LENGTH_SHORT).show()
            } else {
              Log.e("else", respuesta.getString("message"))

              val jsonObject = respuesta.getJSONObject("body")
              val joReclamo = jsonObject.getJSONArray("Reclamo")

              //insertarReclamos(joReclamo, "2")
            }
          } catch (ex: JSONException) {
            Log.e("error", ex.message.toString())
            Toast.makeText(context, ex.message, Toast.LENGTH_SHORT).show()
          }
        },
        { error ->
          Log.e("Error", error.toString())
        }
      )
      MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest)


    }

    fun ObtenerMaterialReclamo(codigoReclamo: Any, cuadrilla: Any) {
      TODO("Not yet implemented")
    }

    fun insertarFotos(codigoReclamo: Any, size: Int) {
      TODO("Not yet implemented")
    }

    fun insertarReclamos(joReclamo: JSONArray, s: String) {
      try {
        //
        if (joReclamo.length() > 0) {
          arrayReclamo.clear();
          Log.e("mensaje de array Reclamo", joReclamo.length().toString())
          for (i in 0 until joReclamo.length()) {
            val item = joReclamo.getJSONObject(i)
            val reclamo = Reclamo(
              codigoReclamo = item.getString("codigoReclamo"),
              celular = item.getString("celular"),
              codigoDireccionElectrica = item.getString("codigoDireccionElectrica"),
              codigoEstadoReclamo = item.getString("codigoEstadoReclamo"),
              codigoRutaSuministro = item.getString("codigoRutaSuministro"),
              codigoSED = item.getString("codigoSED"),
              codigoSuministro = item.getString("codigoSuministro"),
              datoMCo = item.getString("datoMCo"),
              descripcionReclamo = item.getString("descripcionReclamo"),
              direccionElectrica = item.getString("direccionElectrica"),
              fechaEjecucionFin = item.getLong("fechaEjecucionFin"),
              fechaEjecucionInicio = item.getLong("fechaEjecucionInicio"),
              fechaEnCamino = item.getLong("fechaEnCamino"),
              fechaLimiteAtencion = item.getString("fechaLimiteAtencion"),
              fechaProgramacion = item.getLong("fechaProgramacion"),
              fechaRegistro = item.getString("fechaRegistro"),
              nombreClaseReclamo = item.getString("nombreClaseReclamo"),
              nombreSuministro = item.getString("nombreSuministro"),
              plazo = item.getString("plazo"),
              referenciaUbicacion = item.getString("referenciaUbicacion"),
              sectorTipico = item.getString("sectorTipico"),
              enviado = item.getInt("enviado"),
              latitud = item.getString("latitud"),
              longitud = item.getString("longitud"),
              tipoReclamo = item.getString("tipoReclamo"),
              tipolistaReclamo = item.getString("tipolistaReclamo")
            )
            arrayReclamo.add(reclamo)
            viewModel.addClaims(arrayReclamo)

            Log.e("CUADRILLA en reclamos lista activity", userState.cuadrilla)

            for (i in arrayReclamo.indices) {
              val item = arrayReclamo[i]
              if (tipoListaReclamos == "2") {
                //obtenerPuntosGPS(item);
                descargarDatosFichaTecnica(item.codigoReclamo)
                //ObtenerMaterialReclamo(item.codigoReclamo,)
                insertarFotos(item.codigoReclamo, arrayReclamo.size)
              }
            }

          }
        }
      } catch (e: Exception) {
        when (e) {
          is JSONException, is InterruptedException -> e.printStackTrace()
          else -> throw e
        }
      }

    }


    fun ObtenerReclamo(context: Context, viewModel: UserViewModel) {
      try {
        val url = SMConfig.HTTP_SCHEME + SMConfig.SERVER + "getClaim"
        val jsonObjectRequest = JsonObjectRequest(
          Request.Method.GET,
          url,
          JSONObject(),
          { response ->
            try {
              val respuesta = response.getJSONObject("Respuesta")

              if (respuesta.getInt("error") != 0) {
                Toast.makeText(context, respuesta.getString("message"), Toast.LENGTH_SHORT).show()
              } else {
                Log.e("else", respuesta.getString("message"))

                val jsonObject = respuesta.getJSONObject("body")
                val joReclamo = jsonObject.getJSONArray("Reclamo")

                insertarReclamos(joReclamo, "2")
              }
            } catch (ex: JSONException) {
              Log.e("error", ex.message.toString())
              Toast.makeText(context, ex.message, Toast.LENGTH_SHORT).show()
            }
          },
          { error ->
            Log.e("Error", error.toString())
          }
        )
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest)
      } catch (e: JSONException) {
        Log.e("error:", "error: $e")
      }
    }


  * */

  LazyColumn(modifier = Modifier.fillMaxWidth()) {
    item {
      TabRow(
        selectedTabIndex = tabIndex,
        modifier = Modifier,
        backgroundColor = colorResource(id = R.color.colorPrimary),
        contentColor = Color.White,
        indicator = { tabPositions ->
          TabRowDefaults.apply {
            Divider(
              Modifier
                .height(2.dp)
                .padding(horizontal = 16.dp)
                .tabIndicatorOffset(tabPositions[tabIndex]),
              color = MaterialTheme.colors.primary
            )
          }
        },
        divider = {}
      ) {
        tabs.forEachIndexed { index, title ->
          Tab(text = { Text(title) },
            modifier = Modifier,
            selected = tabIndex == index,
            onClick = { tabIndex = index },
            icon = {
              when (index) {
                0 -> Icon(imageVector = Icons.Default.Home, contentDescription = null)
                1 -> Icon(imageVector = Icons.Default.Settings, contentDescription = null)
              }
            }
          )
        }
      }

      when (tabIndex) {
        0 -> EjecutadoScreen(context, viewModel)
        1 -> ProgramadoScreen()
      }
      Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier
          .fillMaxWidth()
          .padding(4.dp)
      ) {
        BotonDefault(title = "Sincronizar") {
          //showDialog = true
          //if (isOnline(context)) {
          //
          //} else {
          context.toast("Para enviar los datos debe activar su conexión a internet")

          //}
          //}
          //if (showDialog) {
          // ProgressDialog(onDismissRequest = { showDialog = false })
        }
      }

    }
  }


}