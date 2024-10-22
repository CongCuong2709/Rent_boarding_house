package com.example.presentation.commoncomposables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.example.presentation.R
import com.example.presentation.ui.detail.SlideImage
import com.example.presentation.ui.home.SuggestedAreas

@Preview(showBackground = true)
@Composable
fun testpre(){
	AppTheme {
		SlideImage()
	}
}

@Composable
fun FilterBar() {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 0.dp, vertical = 8.dp),
		horizontalArrangement = Arrangement.SpaceBetween, // Căn đều hai bên
		verticalAlignment = Alignment.CenterVertically // Căn giữa theo chiều dọc
	) {
		Row(
//			horizontalArrangement = Arrangement.spacedBy(10.dp), // Khoảng cách giữa các nút
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(text = "Tin đăng dành cho bạn",
				fontWeight = FontWeight.Bold,
				modifier = Modifier.padding(start = 10.dp))
		}

		// Phần phải gồm nút "Gần bạn trước" và icon hình lưới
		Row(
			horizontalArrangement = Arrangement.spacedBy(16.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			// Icon lưới
			Icon(
				painter = painterResource(id = R.drawable.search_icon), // Thay thế bằng icon lưới của bạn
				contentDescription = "Grid View",
				tint = Color.Black,
				modifier = Modifier.size(24.dp)
					.padding(end = 10.dp)
			)
		}
	}
}



@Composable
fun SearchInputWithFilters() {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		// Search Text Field
		var query by remember { mutableStateOf("") }

		BasicTextField(
			value = query,
			onValueChange = { query = it },
			modifier = Modifier
				.weight(1f)
				.padding(8.dp),
			textStyle = TextStyle(fontSize = 18.sp),
			decorationBox = { innerTextField ->
				Box(
					Modifier
						.fillMaxWidth()
						.padding(8.dp)
						.background(Color(0xFFF5F5F5), CircleShape)
						.padding(12.dp)
				) {
					if (query.isEmpty()) {
						Text("Cho thuê Bất động sản", color = Color.Gray)
					}
					innerTextField()
				}
			}
		)

		// Bookmark Icon
		IconButton(onClick = { /* Bookmark logic */ }) {
			Icon(
				painter = painterResource(id = R.drawable.search_icon), // Đặt biểu tượng tương ứng
				contentDescription = "Bookmark",
				tint = Color.Gray
			)
		}

		// Notification Icon
		IconButton(onClick = { /* Notification logic */ }) {
			Icon(
				painter = painterResource(id = R.drawable.search_icon), // Đặt biểu tượng tương ứng
				contentDescription = "Notifications",
				tint = Color.Gray
			)
		}

		// Chat Icon
		IconButton(onClick = { /* Chat logic */ }) {
			Icon(
				painter = painterResource(id = R.drawable.search_icon), // Đặt biểu tượng tương ứng
				contentDescription = "Chat",
				tint = Color.Gray
			)
		}
	}
}