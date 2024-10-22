package com.example.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eritlab.jexmon.presentation.screens.sign_in_screen.component.LoginScreen
import com.eritlab.jexmon.presentation.screens.sign_up_screen.component.SignUpScreen
import com.example.compose.AppTheme
import com.example.domain.features.signin.AuthState
import com.example.presentation.commoncomposables.SearchTopBar
import com.example.presentation.models.Screens
import com.example.presentation.ui.home.HomeScreen
import com.example.presentation.ui.sign_in_screen.component.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)

		setContent{
			AppTheme {
				val navController = rememberNavController()
				val signInViewModel : SignInViewModel = hiltViewModel()


				val authState = signInViewModel.authState.observeAsState()
				Scaffold(
					modifier = Modifier,
					containerColor = MaterialTheme.colorScheme.background,
				) {
					Surface(
						modifier = Modifier.padding(it),

					) {
						when(authState.value){
							is AuthState.Authenticated -> {
//								HomeScreen()
								MainScreen(navController, Screens.Home)
							}
							is AuthState.Unauthenticated -> {
//								LoginScreen(navController, signInViewModel)
								LoginScreen()
							}
							is AuthState.Loading -> {
//								LoadingScreen()
							}
							is AuthState.Error -> {
								// Xử lý trường hợp lỗi
//								ErrorScreen((authState.value as AuthState.Error).message)
							}
							else -> {
								// Trường hợp null hoặc không xác định
//								LoadingScreen()  // Bạn có thể thay thế bằng màn hình phù hợp
							}
						}
					}
				}
			}
		}
	}
}

