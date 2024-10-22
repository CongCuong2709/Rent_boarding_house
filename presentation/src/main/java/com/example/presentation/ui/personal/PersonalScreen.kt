package com.example.presentation.ui.personal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.commoncomposables.SearchTopBar

@Composable
fun PersonalScreen(){

	Scaffold(
		modifier = Modifier,
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
			AvatarBar()

			Text(text = "Quản lý")

			Text(text = "Tiện ích")

			ServiceSection()

			Text(text = "Khác")
		}
	}
}


@Composable
fun AvatarBar() {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier.padding(16.dp)
	) {
		// Avatar Image
		Image(
//			painter = rememberImagePainter(data = "https://via.placeholder.com/64"), // Thay bằng URL ảnh của bạn
			painter = painterResource(R.drawable.ic_launcher_background),
			contentDescription = null,
			modifier = Modifier
				.size(64.dp)
				.clip(CircleShape)
				.background(Color.Gray)
		)

		Spacer(modifier = Modifier.width(16.dp))

		Column {
			Text(
				text = "cường nguyễn",
				style = MaterialTheme.typography.bodyLarge
			)

			Spacer(modifier = Modifier.height(4.dp))

			Row(verticalAlignment = Alignment.CenterVertically) {
				Text(
					text = "0.0",
					style = MaterialTheme.typography.bodyLarge
				)

				Spacer(modifier = Modifier.width(4.dp))

				// Rating stars placeholder
				repeat(5) {
					Icon(
						imageVector = Icons.Default.Star,
						contentDescription = null,
						tint = Color.Gray,
						modifier = Modifier.size(16.dp)
					)
				}

				Spacer(modifier = Modifier.width(4.dp))

				Text(
					text = "(0)",
					style = MaterialTheme.typography.bodyLarge
				)
			}

			Spacer(modifier = Modifier.height(8.dp))

			Row {
				Text(
					text = "0 Người theo dõi",
					style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
				)

				Spacer(modifier = Modifier.width(8.dp))

				Divider(
					color = Color.Gray,
					modifier = Modifier
						.height(16.dp)
						.width(1.dp)
				)

				Spacer(modifier = Modifier.width(8.dp))

				Text(
					text = "0 Đang theo dõi",
					style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
				)
			}
		}
	}
}
@Composable
fun ServiceSection() {
	Column(
		modifier = Modifier.padding(16.dp)
	) {
		// Tiêu đề "Tiện ích"
		Text(
			text = "Tiện ích",
			fontWeight = FontWeight.Bold,
			fontSize = 18.sp,
			modifier = Modifier.padding(bottom = 8.dp)
		)

		// Các nút TextButton
		Column {
			ServiceButton(icon = Icons.Default.Favorite, text = "Tin đăng đã lưu")
			ServiceButton(icon = Icons.Default.Star, text = "Tìm kiếm đã lưu")
			ServiceButton(icon = Icons.Default.Star, text = "Đánh giá từ tôi")
		}

		Spacer(modifier = Modifier.height(16.dp))

		// Tiêu đề "Dịch vụ trả phí"
		Text(
			text = "Dịch vụ trả phí",
			fontWeight = FontWeight.Bold,
			fontSize = 18.sp,
			modifier = Modifier
				.padding(vertical = 8.dp)
				.background(Color(0xFFF0F0F0))
				.fillMaxWidth()
				.padding(8.dp)
		)

		ServiceButton(icon = Icons.Default.Star, text = "Đăng xuất")
	}
}

@Composable
fun ServiceButton(icon: ImageVector, text: String) {
	TextButton(
		onClick = { /* Handle click */ },
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 4.dp),
		contentPadding = PaddingValues(start = 0.dp) // Đảm bảo không có padding bên trái
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.fillMaxWidth()
		) {
			Icon(
				imageVector = icon,
				contentDescription = null,
				tint = Color.Red,
				modifier = Modifier
					.size(24.dp)
					.padding(start = 8.dp) // Padding bên trái cho icon
			)
			Spacer(modifier = Modifier.width(8.dp))
			Text(text = text, color = Color.Black)
		}
	}
}
@Preview(showBackground = true)
@Composable
fun PersonalScreenPreview(){
	PersonalScreen()
}