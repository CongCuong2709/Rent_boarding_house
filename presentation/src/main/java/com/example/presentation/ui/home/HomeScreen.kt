package com.example.presentation.ui.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection.Companion.Content
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.presentation.R
import com.example.presentation.commoncomposables.BoardingHouseCard
import com.example.presentation.commoncomposables.FilterBar
import com.example.presentation.commoncomposables.SearchTopBar
import com.example.presentation.models.Screens
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
	viewModel : HomeViewModel = hiltViewModel(),
	navController : NavController,
) {
	val boardingHouseListState = rememberLazyListState()
	val homeData by viewModel.homeData.observeAsState(initial = emptyList())

	val coroutineScope = rememberCoroutineScope()
	val filterSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

	// State to control the visibility of the filter sheet
	var isFilterSheetVisible by remember { mutableStateOf(false) }


	// Show the bottom sheet when isFilterSheetVisible is true
//	LaunchedEffect(isFilterSheetVisible) {
//		if (isFilterSheetVisible) {
//			filterSheetState.show()  // Hiện bottom sheet
//		} else {
//			filterSheetState.hide()  // Ẩn bottom sheet
//		}
//	}

	Scaffold(
		modifier = Modifier,
	) { innerPadding ->
		LazyColumn(
			state = boardingHouseListState,
			contentPadding = innerPadding,
			verticalArrangement = Arrangement.spacedBy(0.dp)
		) {
			item {
				SearchTopBar(navController = navController)
			}

			item {
				PropertyTypeSection()
			}


			item {
				SuggestedAreas(
					viewModel = viewModel,
					onFilterSheetVisibleChange = {
						isVisible -> isFilterSheetVisible = isVisible }
				)
			}

			item {
				FilterBar()
			}

			items(homeData) { post ->
				Log.d("HomeScreen", "Post title: ${post.title}")
				BoardingHouseCard(post = post, navController = navController)
			}
		}
	}

	// Bottom sheet cho bộ lọc
//	ModalBottomSheet(
//		onDismissRequest = { isFilterSheetVisible = false },
//		sheetState = filterSheetState
//	) {
//		FilterBottomSheet(
//			onApplyFilters = {
//				// Xử lý áp dụng bộ lọc
//				isFilterSheetVisible = true // Đóng bottom sheet sau khi áp dụng
//			},
//			onClearFilters = {
//				// Xử lý xóa bộ lọc
//				isFilterSheetVisible = false
//			},
//			onDismiss = {
//				isFilterSheetVisible = false // Đóng bottom sheet
//			}
//		)
//	}
}

@Composable
fun PropertyTypeSection() {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.horizontalScroll(rememberScrollState()),
//			.padding(horizontal = 16.dp),
		horizontalArrangement = Arrangement.SpaceEvenly
	) {
		listOf(
			"Trọ gần tôi",
			"Tìm ở ghép",
			"Dịch vụ",
			"So sánh",
			"Quản lý",
			"So sánh",
			"Quản lý"
		).forEach { type ->
			Box(
				modifier = Modifier
					.width(80.dp) // Đặt chiều rộng cố định cho từng thành phần
					.padding(8.dp) // Khoảng cách giữa các thành phần
					.clickable { /* Navigate to property type */ },
				contentAlignment = Alignment.Center // Căn giữa nội dung bên trong Box
			) {
				Column(
					horizontalAlignment = Alignment.CenterHorizontally // Căn giữa theo chiều ngang
				) {
					// Icon
					Icon(
						painter = painterResource(id = R.drawable.camera_icon), // Thay thế bằng icon thực tế
						contentDescription = type,
						tint = Color.Black,
						modifier = Modifier.size(40.dp)
					)

					// Text hiển thị tên loại bất động sản
					Text(
						text = type,
						textAlign = TextAlign.Center,
						maxLines = 2, // Cho phép văn bản dãn xuống tối đa 2 dòng
						overflow = TextOverflow.Ellipsis, // Cắt văn bản nếu quá dài
						modifier = Modifier.padding(top = 4.dp) // Khoảng cách giữa icon và text
					)
				}
			}
		}
	}
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuggestedAreas(
	viewModel : HomeViewModel,
	onFilterSheetVisibleChange : (Boolean) -> Unit // Callback để thay đổi trạng thái hiển thị
) {
	Column(

	) {
		Text(
			text = "Gợi ý khu vực",
			modifier = Modifier.padding(10.dp),
			style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), // Kiểu chữ đậm
		)
		// Horizontal list of suggested areas
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(start = 10.dp)
				.horizontalScroll(rememberScrollState()),
			horizontalArrangement = Arrangement.spacedBy(8.dp)
		) {

			Button(
				onClick = { /* Xử lý khu vực gần tôi */ },
				colors = ButtonDefaults.buttonColors(
					containerColor = Color.LightGray, // Nền xám
					contentColor = Color.Black         // Chữ đen
				)
			) {
				Text("Khu vực gần tôi")
			}
			Button(
				onClick = { viewModel.filterByLocation("TP Hồ Chí Minh") },
				colors = ButtonDefaults.buttonColors(
					containerColor = Color.LightGray, // Nền xám
					contentColor = Color.Black         // Chữ đen
				)
			) {
				Text("TP Hồ Chí Minh")
			}
			Button(
				onClick = { viewModel.filterByLocation("Hà Nội") },
				colors = ButtonDefaults.buttonColors(
					containerColor = Color.LightGray, // Nền xám
					contentColor = Color.Black         // Chữ đen
				)
			) {
				Text("Hà Nội")
			}
			Button(
				onClick = { viewModel.filterByLocation("Đà Nẵng") },
				colors = ButtonDefaults.buttonColors(
					containerColor = Color.LightGray, // Nền xám
					contentColor = Color.Black         // Chữ đen
				)
			) {
				Text("Đà Nẵng")
			}
		}

		TextButton(onClick = {
			// Hiển thị BottomSheet bộ lọc
			onFilterSheetVisibleChange(true) // Gọi callback để thay đổi trạng thái
		}
		) {

			Text(
				buildAnnotatedString {
					append("Xem thêm ")
					withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
						append(">")
					}
				},
				style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray) // Kiểu chữ xám
			)
		}

	}
}


@Composable
fun FilterBottomSheet(
	onApplyFilters : () -> Unit,
	onClearFilters : () -> Unit,
	onDismiss : () -> Unit
) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp)
	) {
		// Tiêu đề
		Text(
			text = "Bộ lọc",
			style = MaterialTheme.typography.bodyLarge,
			modifier = Modifier.padding(bottom = 16.dp)
		)

		// Các trường nhập ví dụ
		OutlinedTextField(
			value = "",
			onValueChange = { /* Xử lý thay đổi giá trị */ },
			label = { Text("Giá từ") },
			modifier = Modifier
				.fillMaxWidth()
				.padding(vertical = 8.dp)
		)

		OutlinedTextField(
			value = "",
			onValueChange = { /* Xử lý thay đổi giá trị */ },
			label = { Text("Giá đến") },
			modifier = Modifier
				.fillMaxWidth()
				.padding(vertical = 8.dp)
		)

		OutlinedTextField(
			value = "",
			onValueChange = { /* Xử lý thay đổi giá trị */ },
			label = { Text("Diện tích") },
			modifier = Modifier
				.fillMaxWidth()
				.padding(vertical = 8.dp)
		)

		// Các nút hành động
		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			Button(
				onClick = onClearFilters,
				modifier = Modifier
					.weight(1f)
					.padding(end = 8.dp)
			) {
				Text("Xóa lọc")
			}

			Button(
				onClick = {
					onApplyFilters()
					onDismiss() // Đóng bottom sheet
				},
				modifier = Modifier.weight(1f)
			) {
				Text("Áp dụng")
			}
		}
	}
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
	AppTheme {
		FilterBottomSheet(onApplyFilters = {}, onClearFilters = {}, onDismiss = {})
	}
}