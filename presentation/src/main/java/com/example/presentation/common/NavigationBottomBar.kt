package com.example.presentation.common

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.presentation.models.BottomNavigationItem
import com.example.presentation.models.Screens

@Composable
fun NavigationBottomBar( navController : NavController){

	val currentDestination = navController.currentBackStackEntry?.destination?.route
	NavigationBar(
//		containerColor = Color.Blue
	) {
		BottomNavigationItem.entries.forEach { item ->
			NavigationBarItem(
				icon = { Icon(item.icon, contentDescription = stringResource(id = item.title)) },
				label = { Text(stringResource(id = item.title)) },
				selected = currentDestination == item.route.route,
//				colors = NavigationBarItemDefaults.colors(
//					selectedIconColor = Color.Red,
//					indicatorColor = Color.Red
//				),
				onClick = {
					if (currentDestination != item.route.route) {
						navController.navigate(item.route.route){
							launchSingleTop = true
							restoreState = true
						}
					}
				}
			)
		}
	}
}

@Preview
@Composable
fun NavigationBottomBarPreview(){
	val navController = rememberNavController()  // Tạo NavController tạm thời cho Preview
	NavigationBottomBar(navController = navController)
}


