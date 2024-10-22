package com.example.presentation.ui.mananage_posts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.domain.features.models.PostModel
import com.example.presentation.commoncomposables.BoardingHouseCard


@Composable
fun ManagePostScreen(

) {
	Scaffold {
		paddingValues ->

		Column(
			modifier = Modifier.padding(paddingValues)
		) {
			LazyRow {
				item {
					TextButton(onClick = {}) {
						Text(text = "Đang hiển thị (1)")
					}
				}

				item {
					TextButton(onClick = {}) {
						Text(text = "Hết hạn (2)" )
					}
				}

				item {
					TextButton(onClick = {}) {
						Text(text = "Bị từ chối (3)")
					}
				}

				item {
					TextButton(onClick = {}) {
						Text(text = "Tin nháp (2)")
					}
				}

				item {
					TextButton(onClick = {}) {
						Text(text = "Chờ duyệt (0)")
					}
				}

				item {
					TextButton(onClick = {}) {
						Text(text = "Bị ẩn (2)")
					}
				}
			}

			LazyColumn {
				item {
					BoardingHouseCard(post = PostModel(), navController = NavController(LocalContext.current))
				}

				item {
					BoardingHouseCard(post = PostModel(), navController = NavController(LocalContext.current))
				}

				item {
					BoardingHouseCard(post = PostModel(), navController = NavController(LocalContext.current))
				}

				item {
					BoardingHouseCard(post = PostModel(), navController = NavController(LocalContext.current))
				}

				item {
					BoardingHouseCard(post = PostModel(), navController = NavController(LocalContext.current))
				}

				item {
					BoardingHouseCard(post = PostModel(), navController = NavController(LocalContext.current))
				}
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun ManagePostScreenPreview() {
	AppTheme {
		ManagePostScreen()
	}
}