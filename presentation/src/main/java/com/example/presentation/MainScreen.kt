package com.example.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.domain.features.models.PostModel
import com.example.presentation.common.NavigationBottomBar
import com.example.presentation.models.Screens
import com.example.presentation.ui.create_post.CreatePostScreen
import com.example.presentation.ui.create_post.CreatePostViewModel
import com.example.presentation.ui.detail.DetailScreen
import com.example.presentation.ui.detail.DetailViewModel
import com.example.presentation.ui.home.HomeScreen
import com.example.presentation.ui.home.HomeViewModel
import com.example.presentation.ui.personal.PersonalScreen
import com.example.presentation.ui.search.SearchScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel

@Composable
fun MainScreen(navController: NavHostController, screen : Screens ) {

	val homeViewModel : HomeViewModel = hiltViewModel()
	val detailViewModel: DetailViewModel = hiltViewModel()
	val createPostViewModel : CreatePostViewModel = hiltViewModel()


	Scaffold(
		bottomBar = { NavigationBottomBar(navController = navController) },

	){paddingValues ->
		Surface(modifier = Modifier.padding(paddingValues)) {
			NavHost(
				navController = navController,
				startDestination = screen.route
			) {
				composable(Screens.Home.route) {
					HomeScreen(viewModel = homeViewModel, navController = navController)
				}

				composable(Screens.Detail.route + "/{postId}") { backStackEntry ->
					val postId = backStackEntry.arguments?.getString("postId")
					postId?.let {

						LaunchedEffect(postId) {
							detailViewModel.loadDetail(postId)
						}
						DetailScreen(postId = postId, navController = navController, viewModel = detailViewModel)
					}
				}

//				composable(Screens.Detail.route + "/{post}"){navBackStackEntry ->
//					val gson : Gson = GsonBuilder().create()
//					val postJson = navBackStackEntry.arguments?.getString("post")
//					val postObject = gson.fromJson(postJson, PostModel::class.java)
////					DetailScreen(postId = postObject)
//				}

				composable(Screens.Search.route) {
					SearchScreen(navController = navController)
				}

				composable(Screens.Notification.route) {
//						NotificationScreen(navController)
				}
				composable(Screens.Person.route) {
//						ProfileScreen(navController)
					PersonalScreen()
				}

				composable(Screens.CreatePost.route) {
					//edit this
					CreatePostScreen(viewModel = createPostViewModel)
				}
				// Add other composable destinations as needed
			}
		}

	}
}

