package master_provider_else.reclamos.ui.theme.view.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import master_provider_else.reclamos.navigation.AppNavigation
import master_provider_else.reclamos.ui.theme.viewModel.UserViewModel

@AndroidEntryPoint
class InitialLoginScreen() : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val userViewModel: UserViewModel by viewModels()
      AppNavigation(userViewModel)
    }
  }
}