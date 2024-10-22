package com.example.data.features.createPost

import android.media.Image
import com.example.domain.features.createPost.CreatePostRepository
import com.example.domain.features.models.PostModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CreatePostRepositoryImpl @Inject constructor(
	private val database : FirebaseFirestore) : CreatePostRepository
{
	override suspend fun validatePost(post : PostModel) : Boolean {
//		return post.title.isNotBlank() && post.price > 0 && post.location.isNotBlank()
		return true
	}

	override suspend fun uploadImages(images : List<Image>) : List<String> {

		val imageUrls = mutableListOf<String>()
		for (image in images) {
//			val imageUrl = apiService.uploadImage(image)
//			imageUrls.add(imageUrl)
		}
		return imageUrls
	}

	override suspend fun createPost(post : PostModel) : Boolean {
		//Cần viết lại
		return try {
			// Thêm bài đăng mới vào collection "posts"
			database.collection("posts")
				.add(post.toMap()) // Chuyển đối tượng PostModel thành Map để lưu vào Firestore
				.await() // Sử dụng await để chờ kết quả của Firestore
			true // Thành công
		} catch (e: Exception) {
			e.printStackTrace() // In lỗi ra console
			false // Thất bại
		}
	}


}