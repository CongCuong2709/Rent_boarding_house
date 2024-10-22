package com.example.presentation.commoncomposables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.compose.AppTheme
import com.example.domain.features.models.PostModel
import com.example.presentation.R
import com.example.presentation.models.Screens
import com.example.presentation.ui.home.HomeScreen

@Composable
fun BoardingHouseCard(
	post : PostModel,
	navController : NavController
){
	Card(
		modifier = Modifier.fillMaxWidth()
			.padding(0.dp)
			.height(135.dp)
			.clickable {
				navController.navigate(Screens.Detail.createRoute(postId = post.id))
			},
		elevation = CardDefaults.cardElevation(4.dp),
	){
		Row (
			modifier = Modifier
				.fillMaxWidth()
			.padding(bottom = 15.dp),
//				.background(color = Color.Yellow),
			horizontalArrangement = Arrangement.spacedBy(5.dp)
		){

			Image(
				painter = rememberAsyncImagePainter(post.imageUrl),
				contentDescription = null,
				modifier = Modifier
					.weight(1f)
					.aspectRatio(1f)
					.clip(RoundedCornerShape(8.dp))
					.background(Color.LightGray) // Màu nền nếu chưa có hình
			)

			Spacer(modifier = Modifier.width(8.dp))

			Column(modifier = Modifier.weight(2f)) {
				// Tiêu đề
				Text(
					modifier = Modifier.weight(2f),
					text = post.title,
					style = MaterialTheme.typography.bodyLarge,
					maxLines = 2,
					overflow = TextOverflow.Ellipsis,
				)

				// Trang thai noi that
				Text(
					modifier = Modifier.weight(1f),
					text = post.typeInterior,
					style = MaterialTheme.typography.bodyMedium,
					color = Color.Gray,
					maxLines = 1,
					overflow = TextOverflow.Ellipsis
				)



				// Giá thuê
				Text(
					modifier = Modifier.weight(1f),
					text = post.price,
					style = MaterialTheme.typography.bodyMedium,
					color = Color.Red
				)

				Text(
//					modifier = Modifier.weight(1f),
					text = post.location,
					style = MaterialTheme.typography.bodyMedium,
					color = Color.Gray,
					maxLines = 1,
					overflow = TextOverflow.Ellipsis
				)
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun BoardingHouseCardPreview(){
	AppTheme {
//		BoardingHouseCard(PostModel("Số 9")) 
	}
}