package com.example.data.features.home

import com.example.domain.features.home.HomeRepository
import com.example.domain.features.models.PostModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
	private val firestore : FirebaseFirestore
) : HomeRepository{

	override suspend fun fetchHomeData() : List<PostModel> {
//		return try {
//			val snapshot = firestore.collection("posts").get().await()
//			snapshot.documents.mapNotNull { document ->
//				document.toObject(PostModel::class.java)
//			}
//		} catch (e: Exception) {
//			// Xử lý ngoại lệ và trả về danh sách rỗng nếu có lỗi
//			emptyList()
//		}

		val snapshot = firestore.collection("posts").get().await()
		return snapshot.documents.mapNotNull { document ->
			document.toObject(PostModel::class.java)
		}
	}

	override suspend fun fetchHomeDataByLocation(location : String) : List<PostModel> {
		val snapshot = firestore.collection("posts")
			.whereEqualTo("location", location)
			.get().await()
		return snapshot.documents.mapNotNull { document ->
			document.toObject(PostModel::class.java)
		}
	}
}