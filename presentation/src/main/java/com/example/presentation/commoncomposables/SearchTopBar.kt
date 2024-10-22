package com.example.presentation.commoncomposables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.presentation.R
import com.example.presentation.models.Screens


@Preview(showBackground = true)
@Composable
fun SearchTopBarPreview(){
	AppTheme {
//		SearchTopBar()

	}

}

@Composable
fun SearchTopBar(navController : NavController) {
	val focusRequester = remember { FocusRequester() }
	val focusManager = LocalFocusManager.current
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.background(color = Color.Yellow),
		horizontalArrangement = Arrangement.SpaceBetween, // Điều chỉnh khoảng cách đều giữa các thành phần
		verticalAlignment = Alignment.CenterVertically // Căn giữa theo chiều dọc
	) {

//		// Mũi tên quay về
//		IconButton(onClick = {
//			// Quay lại màn hình trước đó
//			navController.popBackStack()
//		}) {
//			Icon(
//				imageVector = Icons.Default.ArrowBack, // Sử dụng icon mặc định mũi tên quay về
//				contentDescription = "Back Arrow",
//				tint = Color.Gray // Màu của biểu tượng mũi tên
//			)
//		}

		Spacer(modifier = Modifier.width(8.dp))
		// Thanh tìm kiếm
		TextField(
			value = "",
			onValueChange = {},
			singleLine = true,
			placeholder = { Text(text = "Tìm kiếm") },
			leadingIcon = {
				Icon(
					painter = painterResource(id = R.drawable.search_icon),
					contentDescription = "Search Icon"
				)
			},
			keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
			colors = TextFieldDefaults.colors(
				focusedContainerColor = Color.White,
				unfocusedContainerColor = Color.White,
				disabledContainerColor = Color.White,
				cursorColor = Color.Gray,
				focusedIndicatorColor = Color.Transparent,   // Ẩn đường viền khi focus
				unfocusedIndicatorColor = Color.Transparent  // Ẩn đường viền khi không focus
			),
			modifier = Modifier
				.weight(1f) // Thanh tìm kiếm chiếm toàn bộ không gian còn lại
				.height(50.dp)
				.background(Color.White) // Nền trắng
				.focusRequester(focusRequester)
				.clickable {
					navController.navigate(Screens.Search.route)
				}
		)

		Spacer(modifier = Modifier.width(8.dp)) // Khoảng cách giữa thanh tìm kiếm và biểu tượng

		// Icon notification
		Box(
			modifier = Modifier
				.size(48.dp)
				.clip(CircleShape)
				.background(Color.White)
				.clickable {},
			contentAlignment = Alignment.Center
		) {
			Icon(
				painter = painterResource(id = R.drawable.cart_icon), // Đặt đúng icon notification
				contentDescription = "Notification Icon",
				tint = Color.Gray
			)
		}

		Spacer(modifier = Modifier.width(8.dp)) // Khoảng cách giữa 2 biểu tượng

		// Icon messages
		Box(
			modifier = Modifier
				.size(48.dp)
				.clip(CircleShape)
				.background(Color.White)
				.clickable {},
			contentAlignment = Alignment.Center
		) {
			Icon(
				painter = painterResource(id = R.drawable.search_icon),
				contentDescription = "Messages Icon",
				tint = Color.Gray
			)
		}
	}
}
