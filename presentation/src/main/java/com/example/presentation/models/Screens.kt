package com.example.presentation.models

sealed class Screens(val route : String){
	object Login : Screens("login_screen")
	object Home : Screens("home_Screen")
	object Person : Screens("person_Screen")
	object CreatePost : Screens("create_post_Screen")
	object Notification : Screens("Notification_Screen")
	object Detail : Screens("Detail_Screen") {
		fun createRoute(postId: String) = "Detail_Screen/$postId"
	}
	object Search : Screens("Search_Screen")
}




