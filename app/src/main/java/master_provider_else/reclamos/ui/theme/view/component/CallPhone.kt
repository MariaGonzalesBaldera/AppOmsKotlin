package master_provider_else.reclamos.ui.theme.view.component

import android.content.Context
import android.content.Intent
import android.net.Uri

fun CallPhone(context:Context,phone:String) {

  val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
  context.startActivity(intent)
}