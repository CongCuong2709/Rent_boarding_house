package com.example.presentation.models

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.presentation.R

enum class BottomNavigationItem(
	val route : Screens,
	val icon : ImageVector,
	@StringRes val title : Int,
) {
	Home(
		route = Screens.Home,
		icon = Icons.Filled.Home,
		title = R.string.Home,
	),

	CreatePost(
		route = Screens.CreatePost,
		icon = Icons.Filled.Create,
		title = R.string.CreatePost,
	),

	Notification(
		route = Screens.Notification,
		icon = Icons.Filled.Star,
		title = R.string.Notification,
	),

	Person(
		route = Screens.Person,
		icon = Icons.Filled.Person,
		title = R.string.Person,
	),




}

