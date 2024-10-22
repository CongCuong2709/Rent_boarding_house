package com.example.data.features.detail

import com.example.domain.features.detail.DetailRepository
import com.example.domain.features.models.PostModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
	private val firestore : FirebaseFirestore
) : DetailRepository {
	override suspend fun getDetail(postId : String) : PostModel {
		return try {
			val querySnapshot = firestore.collection("posts")
				.whereEqualTo("id", postId)
				.get()
				.await()

			val document = querySnapshot.documents.firstOrNull()

			document?.toObject(PostModel::class.java) ?: PostModel()
		} catch (e: Exception) {
			e.printStackTrace()
			PostModel()
		}
	}

}