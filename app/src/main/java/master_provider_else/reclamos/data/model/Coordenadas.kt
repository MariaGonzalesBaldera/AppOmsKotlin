package master_provider_else.reclamos.data.model

import com.google.android.gms.maps.model.LatLng

data class Coordenadas(
  var latLng: LatLng = LatLng(0.0, 0.0),
  var TipoCoordenada: String = "",
  var CodigoNTCSE: String = ""
)