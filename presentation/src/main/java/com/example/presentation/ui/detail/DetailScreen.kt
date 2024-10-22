package com.example.presentation.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.domain.features.models.PostModel
import com.example.presentation.R
import com.example.presentation.models.Screens
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel
import kotlin.math.max

@Composable
fun DetailScreen(
	postId : String,
	navController : NavController,
	viewModel: DetailViewModel = hiltViewModel()
){

	val postDetail by viewModel.postDetail.collectAsState()

	val scrollState = rememberScrollState()

	LaunchedEffect(Unit) {
		viewModel.loadDetail(postId)
	}

	Scaffold(
		modifier = Modifier,
		bottomBar = {
				BottomAppBar(
//					containerColor = Color.Green
					modifier = Modifier.height(70.dp) // Đảm bảo chiều cao đủ lớn
				){
					Row(
						horizontalArrangement = Arrangement.SpaceBetween,
						verticalAlignment = Alignment.CenterVertically,
						modifier = Modifier.padding(horizontal = 16.dp)
					){
						Row (
							modifier = Modifier
								.weight(1f)
								.padding(end = 8.dp), // Optional: Khoảng cách giữa các nút
							verticalAlignment = Alignment.CenterVertically
						){
							IconButton(onClick = { /* Handle call action */ }) {
								Icon(
									imageVector = Icons.Default.Call,
									contentDescription = "Call",
									tint = Color.Green
								)
							}
							Text(
								text = "Gọi điện",
								style = MaterialTheme.typography.bodyLarge,
								color = Color.Black,
							)
						}

						Row(
							modifier = Modifier
								.weight(0.5f)
								.padding(end = 8.dp),
							verticalAlignment = Alignment.CenterVertically
						){
							IconButton(onClick = { /* Handle message action */ }) {
								Icon(
									imageVector = Icons.Default.Menu,
									contentDescription = "Message",
									tint = Color.White
								)
							}
							Text(
								text = "Nhắ",
								style = MaterialTheme.typography.bodyLarge,
								color = Color.Black
							)
						}

						Row(
							modifier = Modifier
								.weight(0.5f),
							verticalAlignment = Alignment.CenterVertically
						){
							IconButton(onClick = { /* Handle chat action */ }) {
								Icon(
									imageVector = Icons.Default.Menu,
									contentDescription = "Chat",
									tint = Color.White
								)
							}
							Text(
								text = "Chat",
								style = MaterialTheme.typography.bodyLarge,
								color = Color.Black
							)
						}

					}

				}

		},

	) { paddingValues ->



		Column(
			modifier = Modifier.padding(paddingValues)
				.verticalScroll(scrollState)
		) {
			postDetail?.let {
				BackRowBar(post = it, navController = navController)

				SlideImage()

				RoomInformation(postDetail = it)

				Spacer(
					modifier = Modifier
						.fillMaxWidth()
						.height(8.dp)
						.background(Color.Gray)
				)

				DetailDescription(postDetail = it)

				Location()
			}

		}
	}
}

@Composable
fun BackRowBar(post : PostModel,
	navController : NavController){
	//Back row + title
	Row(
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Start,
		modifier = Modifier
			.height(50.dp)
			.fillMaxWidth()
			.background(Color.LightGray)
	) {
		IconButton(onClick = {
			navController.navigate(Screens.Home.route)
		}) {
			Icon(
				imageVector = Icons.Default.ArrowBack, // Biểu tượng Back
				contentDescription = "Back",
				tint = Color.Black // Màu biểu tượng
			)
		}
		Text(
			text = post.title,
			style = MaterialTheme.typography.bodyLarge,
			color = Color.Black,
			modifier = Modifier.padding(start = 8.dp), // Khoảng cách giữa icon và tiêu đề
			maxLines = 1,
			overflow = TextOverflow.Ellipsis,
		)
	}
}

@Composable
fun SlideImage() {
	val images = listOf(
		R.drawable.image_banner_3, // Replace with actual image resource ids
		R.drawable.image_banner_2,
		R.drawable.image_banner_3
	)

	var currentPage by remember { mutableStateOf(1) }

	// Slide bar image
	Box(
		modifier = Modifier
			.fillMaxWidth()
			.height(250.dp)
			.background(Color.Gray)
	) {
		LazyRow(
			modifier = Modifier.fillMaxSize(),
			contentPadding = PaddingValues(horizontal = 0.dp),
			horizontalArrangement = Arrangement.Center
		) {
			items(images.size) { index ->
				Image(
					painter = painterResource(images[index]),
					contentDescription = "Room Image",
					modifier = Modifier
						.fillMaxWidth()
						.clickable {
							// Handle image click
						},
					contentScale = ContentScale.Crop
				)
			}
		}

		// Page indicator
		Text(
			text = "$currentPage/${images.size}",
			color = Color.White,
			modifier = Modifier
				.align(Alignment.BottomCenter)
				.padding(8.dp)
				.background(Color.Black.copy(alpha = 0.7f))
				.padding(horizontal = 12.dp, vertical = 4.dp)
		)

		// Favorite and Share buttons
		Row(
			horizontalArrangement = Arrangement.SpaceBetween,
			modifier = Modifier
				.fillMaxWidth()
				.padding(16.dp)
				.align(Alignment.TopEnd)
		) {
			IconButton(onClick = { /* Handle favorite action */ }) {
				Icon(
					imageVector = Icons.Default.FavoriteBorder,
					contentDescription = "Favorite",
					tint = Color.White
				)
			}

			IconButton(onClick = { /* Handle share action */ }) {
				Icon(
					imageVector = Icons.Default.Share,
					contentDescription = "Share",
					tint = Color.White
				)
			}
		}

		// Navigation arrows
		IconButton(
			onClick = {
				if (currentPage > 1) currentPage--
			},
			modifier = Modifier.align(Alignment.CenterStart)
		) {
			Icon(
				imageVector = Icons.Default.ArrowBack,
				contentDescription = "Previous",
				tint = Color.White
			)
		}

		IconButton(
			onClick = {
				if (currentPage < images.size) currentPage++
			},
			modifier = Modifier.align(Alignment.CenterEnd)
		) {
			Icon(
				imageVector = Icons.Default.ArrowForward,
				contentDescription = "Next",
				tint = Color.White
			)
		}
	}
}


@Composable
fun RoomInformation(postDetail : PostModel) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp)
	) {
		Text(
			text = postDetail.title,
			style = MaterialTheme.typography.bodyLarge,
			maxLines = 1,
			overflow = TextOverflow.Ellipsis
		)

		Spacer(modifier = Modifier.height(4.dp))

		Text(
			text = postDetail.acreage + "m²",
			style = MaterialTheme.typography.bodyMedium,
			color = Color.Gray,
			fontSize = 14.sp
		)

		Text(
			text = postDetail.price + "Vnd",
			color = Color.Red,
			style = MaterialTheme.typography.bodyLarge,
			modifier = Modifier
				.fillMaxWidth()
				.padding(vertical = 8.dp)
				.background(Color.Gray.copy(alpha = 0.1f))
				.padding(horizontal = 8.dp, vertical = 4.dp)
		)

		Spacer(modifier = Modifier.height(4.dp))

		Text(
			text = postDetail.location,
			style = MaterialTheme.typography.bodyMedium,
			color = Color.Gray,
			fontSize = 14.sp
		)

		Text(
			text = "Cập nhật 4 giờ trước",
			style = MaterialTheme.typography.bodyMedium,
			color = Color.Gray,
			fontSize = 14.sp
		)
	}
}
@Composable
fun DetailDescription(postDetail : PostModel) {
	var isExpanded by remember { mutableStateOf(false) }

	// Detailed description content
	val description = """
        Nhà mới xây, nội thất full cao cấp, mới, có máy giặt riêng, 1 máy lạnh, tủ lạnh, 2 giường + nệm và tủ để đồ to.
        
        \n\nCác phí khác:
        \nĐiện = 3.800 VND/KW
        \nNước = 100k/ng
        \nPhí sinh hoạt chung = 150k/phòng
        \n
        \nQuản lý minh bạch, hỗ trợ 24/24. Giờ giấc tự do.
    """.trimIndent()

	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp)
	) {
		Text(
			text = "Mô tả chi tiết",
			style = MaterialTheme.typography.bodyLarge,
			color = Color.Black
		)

		Spacer(modifier = Modifier.height(8.dp))

		// Display long text with expand/collapse feature
		Text(
			text = if (isExpanded) postDetail.description else postDetail.description.take(100) + "...",
			style = MaterialTheme.typography.bodyMedium,
			color = Color.Black
		)

		Spacer(modifier = Modifier.height(8.dp))

		// Contact link (mock example)
		Text(
			text = "Liên hệ ngay: 089847****",
			style = MaterialTheme.typography.bodyMedium,
			color = MaterialTheme.colorScheme.primary,
			modifier = Modifier
				.clickable { /* Handle click */ }
				.padding(4.dp)
		)

		Spacer(modifier = Modifier.height(8.dp))
		// Expand/Collapse button
		TextButton(
			modifier = Modifier.align(Alignment.CenterHorizontally),
			onClick = { isExpanded = !isExpanded }
		) {
			Text(
				text = if (isExpanded) "Thu gọn" else "Xem thêm",
				style = MaterialTheme.typography.bodyMedium,
				color = MaterialTheme.colorScheme.primary
			)
		}



	}
}

@Composable
fun Location() {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp)
	) {
		// Tiêu đề "Vị trí bất động sản"
		Text(
			text = "Vị trí ",
			style = MaterialTheme.typography.bodyMedium,
			modifier = Modifier.padding(bottom = 8.dp)
		)

		// Ảnh bản đồ (thay cho Google Map)
		Image(
			painter = painterResource(id = R.drawable.cart_icon), // Thay 'sample_map' bằng ID ảnh bản đồ của bạn
			contentDescription = "Bản đồ",
			modifier = Modifier
				.fillMaxWidth()
				.aspectRatio(16 / 9f) // Tỉ lệ khung hình 16:9, có thể thay đổi tùy ý
				.padding(bottom = 16.dp)
		)
	}
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview(){
	AppTheme {
//		DetailScreen()
		DetailScreen(postId = "1", navController = NavController(LocalContext.current))

	}
}