package com.example.presentation.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.domain.features.models.PostModel
import com.example.presentation.R
import com.example.presentation.commoncomposables.BoardingHouseCard
import com.example.presentation.commoncomposables.SearchTopBar
import com.example.presentation.ui.home.HomeViewModel
import com.example.presentation.ui.home.PropertyTypeSection
import com.example.presentation.ui.home.SuggestedAreas
import com.google.android.material.chip.Chip
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun SearchScreen(
	navController : NavController
){

	val showLocationFilter = remember { mutableStateOf(false) }
	val showPriceFilter = remember { mutableStateOf(false) }
	val showAdvancedFilter = remember { mutableStateOf(false) }
	val showSortOptions = remember { mutableStateOf(false) }

	Scaffold(
		modifier = Modifier,
//		snackbarHost = { SnackbarHost(snackbarHostState) },
	) {paddingValues ->

		Column(
			Modifier
				.fillMaxSize()
				.padding(paddingValues)
				.pointerInput(Unit) {
					detectTapGestures(
//						onTap = { focusManager.clearFocus() },
//						onPress = { focusManager.clearFocus() },
//						onLongPress = { focusManager.clearFocus() },
//						onDoubleTap = { focusManager.clearFocus() }
					)
				}
		) {
			SearchTopBar(navController = navController)

			Spacer(modifier = Modifier.height(8.dp))

			LocationChoice(onClick = { showLocationFilter.value = true })

			FilterBar(
				onPriceClick = { showPriceFilter.value = true },
				onAdvancedFilterClick = { showAdvancedFilter.value = true }
			)

			SortBar(onSortClick = { showSortOptions.value = true })

			LazyColumn {
				item {
					BoardingHouseCard(post = PostModel(), navController = navController)
				}

				item {
					BoardingHouseCard(post = PostModel(), navController = navController)
				}

				item {
					BoardingHouseCard(post = PostModel(), navController = navController)
				}

				item {
					BoardingHouseCard(post = PostModel(), navController = navController)
				}

				item {
					BoardingHouseCard(post = PostModel(), navController = navController)
				}
			}
		}

		// Hiển thị Bottom Sheet cho Location
		if (showLocationFilter.value) {
			Dialog(onDismissRequest = { showLocationFilter.value = false }) {
				Surface(
					shape = MaterialTheme.shapes.large,
//					elevation = 8.dp
				) {
					LocationFilterSheet(
						onApply = { /* Apply logic */ showLocationFilter.value = false },
						onClear = { /* Clear logic */ showLocationFilter.value = false }
					)
				}
			}
		}

		// Hiển thị Bottom Sheet cho Price
		if (showPriceFilter.value) {
			Dialog(onDismissRequest = { showPriceFilter.value = false }) {
				Surface(
					shape = MaterialTheme.shapes.large,
//					elevation = 8.dp
				) {
					PriceFilterSheet(
						onApply = { /* Apply logic */ showPriceFilter.value = false },
						onClear = { /* Clear logic */ showPriceFilter.value = false }
					)
				}
			}
		}

		// Hiển thị Bottom Sheet cho Advanced Filters
		if (showAdvancedFilter.value) {
			Dialog(onDismissRequest = { showAdvancedFilter.value = false }) {
				Surface(
					shape = MaterialTheme.shapes.large,
//					elevation = 8.dp
				) {
					AdvancedFilterSheet(
						onApply = { /* Apply logic */ showAdvancedFilter.value = false },
						onClear = { /* Clear logic */ showAdvancedFilter.value = false }
					)
				}
			}
		}

		// Hiển thị Bottom Sheet cho Sort Options
		if (showSortOptions.value) {
			Dialog(onDismissRequest = { showSortOptions.value = false }) {
				Surface(
					shape = MaterialTheme.shapes.large,
//					elevation = 8.dp
				) {
					SortOptionsSheet()
				}
			}
		}

	}
}

@Composable
fun LocationChoice(onClick: () -> Unit) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.fillMaxWidth()
			.padding(5.dp)
	) {
		Icon(
			painter = painterResource(id = R.drawable.check_mark_rounde),
			contentDescription = null,
			tint = Color.Gray
		)
		Text(
			text = "Khu vực:  ",
			modifier = Modifier.padding(start = 8.dp)
		)
		TextButton(onClick = onClick) {
			Text(text = "Toàn quốc v")
		}
	}
}

@Composable
fun FilterBar(onPriceClick: () -> Unit, onAdvancedFilterClick: () -> Unit) {
	Row(
		horizontalArrangement = Arrangement.SpaceBetween,
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp, vertical = 8.dp)
	) {
		TextButton(onClick = onPriceClick) {
			Text(text = "Giá thuê v")
		}
		TextButton(onClick = onAdvancedFilterClick) {
			Text(text = "Diện tích v")
		}
		TextButton(onClick = { /* Handle interior condition */ }) {
			Text(text = "Tình trạng nội thất v")
		}
	}
}

@Composable
fun SortBar(onSortClick: () -> Unit) {
	Row(
		horizontalArrangement = Arrangement.SpaceBetween,
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp, vertical = 8.dp)
	) {
		TextButton(onClick = onSortClick) {
			Text(text = "Tin mới trước v")
		}

		IconButton(onClick = { /* filter icon */ }) {
			Icon(
				painter = painterResource(id = R.drawable.bill_icon),
				contentDescription = "filter icon"
			)
		}
	}
}

@Composable
fun SortOptionsSheet() {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp)
	) {
		Text(
			text = "Sắp xếp",
			style = MaterialTheme.typography.labelLarge,
			modifier = Modifier.padding(bottom = 16.dp)
		)

		val options = listOf("Tin mới trước", "Giá thấp trước", "Giá cao trước", "Gần bạn trước")
		val selectedOption = remember { mutableStateOf("Tin mới trước") }

		options.forEach { option ->
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.fillMaxWidth()
					.clickable {
						selectedOption.value = option
					}
					.padding(vertical = 8.dp)
			) {
				Text(
					text = option,
					modifier = Modifier.weight(1f)
				)
				RadioButton(
					selected = selectedOption.value == option,
					onClick = { selectedOption.value = option }
				)
			}
		}
	}
}


@Composable
fun AdvancedFilterSheet(onApply: () -> Unit, onClear: () -> Unit) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp)
	) {
		Text(
			text = "Lọc nâng cao",
			style = MaterialTheme.typography.labelLarge,
			modifier = Modifier.padding(bottom = 16.dp)
		)

		// Diện tích
		Text(text = "Diện tích")
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.fillMaxWidth()
		) {
			BasicTextField(
				value = TextFieldValue(""),
				onValueChange = {},
				modifier = Modifier
					.weight(1f)
					.padding(8.dp)
					.background(Color.LightGray)
			)
			Text(text = "-")
			BasicTextField(
				value = TextFieldValue(""),
				onValueChange = {},
				modifier = Modifier
					.weight(1f)
					.padding(8.dp)
					.background(Color.LightGray)
			)
		}

		// Tình trạng nội thất
		Text(text = "Tình trạng nội thất")
		Row(
			horizontalArrangement = Arrangement.SpaceEvenly,
			modifier = Modifier.fillMaxWidth()
		) {
			Chip(text = "Nội thất cao cấp")
			Chip(text = "Nội thất đầy đủ")
			Chip(text = "Nhà trống")
		}

		// Tin có video
		Text(text = "Tin có video")
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.fillMaxWidth()
		) {
			Text(text = "Có video")
			Switch(checked = false, onCheckedChange = {})
		}

		// Đăng bởi
		Text(text = "Đăng bởi")
		Row(
			horizontalArrangement = Arrangement.SpaceEvenly,
			modifier = Modifier.fillMaxWidth()
		) {
			Chip(text = "Cá nhân")
			Chip(text = "Bán chuyên")
			Chip(text = "Đối Tác Nhà Tốt")
		}

		// Action Buttons
		Row(
			horizontalArrangement = Arrangement.SpaceBetween,
			modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
		) {
			Button(onClick = onClear) {
				Text(text = "Xoá lọc")
			}
			Button(onClick = onApply, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA000))) {
				Text(text = "Áp dụng", color = Color.White)
			}
		}
	}
}

@Composable
fun Chip(text: String) {
	Surface(
		shape = MaterialTheme.shapes.small,
		color = Color.LightGray,
		modifier = Modifier.padding(4.dp)
	) {
		Text(
			text = text,
			modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
		)
	}
}

@Composable
fun PriceFilterSheet(onApply: () -> Unit, onClear: () -> Unit) {
	var sliderPosition by remember { mutableStateOf(0f) }
	var minPrice by remember { mutableStateOf(TextFieldValue("")) }
	var maxPrice by remember { mutableStateOf(TextFieldValue("")) }

	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp)
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween,
			modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
		) {
			Text(text = "Giá thuê", style = MaterialTheme.typography.labelLarge)
			TextButton(onClick = onClear) {
				Text(text = "Xoá lọc", color = Color.Blue)
			}
		}

		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
		) {
			Text(text = "0")
			Slider(
				value = sliderPosition,
				onValueChange = { sliderPosition = it },
				valueRange = 0f..100f,
				modifier = Modifier.weight(1f),
				colors = SliderDefaults.colors(
					activeTrackColor = Color(0xFFFFA000),
					thumbColor = Color(0xFFFFA000)
				)
			)
			Text(text = "100 triệu")
		}

		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
		) {
			BasicTextField(
				value = minPrice,
				onValueChange = { minPrice = it },
				modifier = Modifier
					.weight(1f)
					.padding(8.dp)
					.background(Color.LightGray)
			)
			Text(text = "-")
			BasicTextField(
				value = maxPrice,
				onValueChange = { maxPrice = it },
				modifier = Modifier
					.weight(1f)
					.padding(8.dp)
					.background(Color.LightGray)
			)
		}

		Button(
			onClick = onApply,
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = 16.dp),
			colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
		) {
			Text(text = "Áp dụng", color = Color.White)
		}
	}
}

@Composable
fun LocationFilterSheet(onApply: () -> Unit, onClear: () -> Unit) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp)
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween,
			modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
		) {
			Text(text = "Khu vực", style = MaterialTheme.typography.bodyLarge)
			TextButton(onClick = onClear) {
				Text(text = "Xoá Lọc", color = Color.Blue)
			}
		}

		// Search Nearby
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
		) {
			Icon(
				painter = painterResource(id = R.drawable.arrow_right),
				contentDescription = null,
				tint = Color.Gray
			)
			Spacer(modifier = Modifier.width(8.dp))
			Text(text = "Tìm kiếm quanh bạn", modifier = Modifier.weight(1f))
			IconButton(onClick = {}) {
				Icon(
					painter = painterResource(id = R.drawable.arrow_right),
					contentDescription = null,
					tint = Color.Gray
				)
			}
		}

		Text(
			text = "Ngõ 879 La Thành 10km",
			color = Color(0xFFFFA000),
			modifier = Modifier.padding(vertical = 8.dp)
		)

		Divider()

		// Popular Locations
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
		) {
			Icon(
				painter = painterResource(id = R.drawable.arrow_right),
				contentDescription = null,
				tint = Color.Gray
			)
			Spacer(modifier = Modifier.width(8.dp))
			Text(text = "Tìm theo khu vực", modifier = Modifier.weight(1f))
		}

		Row(
			horizontalArrangement = Arrangement.SpaceEvenly,
			modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
		) {
			Chip(text = "TP Hồ Chí Minh")
			Chip(text = "Hà Nội")
			Chip(text = "Đà Nẵng")
		}

		// Location Selection
		DropdownOption("Chọn tỉnh thành")
		DropdownOption("Chọn quận huyện")
		DropdownOption("Chọn phường xã")

		// Action Buttons
		Row(
			horizontalArrangement = Arrangement.SpaceBetween,
			modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
		) {
			Button(onClick = onClear, colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)) {
				Text(text = "Xoá Lọc", color = Color.White)
			}
			Button(onClick = onApply, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA000))) {
				Text(text = "Áp dụng", color = Color.White)
			}
		}
	}
}

@Composable
fun DropdownOption(text: String) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.fillMaxWidth()
			.background(Color.LightGray)
			.padding(12.dp)
	) {
		Text(text = text, modifier = Modifier.weight(1f))
		Icon(
			painter = painterResource(id = R.drawable.arrow_right),
			contentDescription = null,
			tint = Color.Gray
		)
	}
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview(){
	AppTheme {
		SearchScreen(navController = NavController(LocalContext.current))
	}
}

@Preview(showBackground = true)
@Composable
fun Test1(){
	AppTheme {
		LocationFilterSheet(onApply = {}, onClear = {})
	}
}


@Preview(showBackground = true)
@Composable
fun Test2(){
	AppTheme {
		PriceFilterSheet(onApply = {}, onClear = {})
	}
}

