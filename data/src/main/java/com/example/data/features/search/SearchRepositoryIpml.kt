package com.example.data.features.search

import com.example.domain.features.models.FilterCriteria
import com.example.domain.features.models.PostModel
import com.example.domain.features.search.SearchRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
	private val firestore: FirebaseFirestore // inject Firestore instance
) : SearchRepository {
	override suspend fun search(key: String): List<PostModel> {
		// Thực hiện tìm kiếm theo key
		return withContext(Dispatchers.IO) {
			val querySnapshot = firestore.collection("posts")
				.whereEqualTo("title", key) // hoặc các điều kiện khác
				.get()
				.await()

			querySnapshot.toObjects(PostModel::class.java)
		}
	}

	override suspend fun searchByLocation(location: String): List<PostModel> {
		// Thực hiện tìm kiếm theo vị trí
		return withContext(Dispatchers.IO) {
			val querySnapshot = firestore.collection("posts")
				.whereEqualTo("location", location)
				.get()
				.await()

			querySnapshot.toObjects(PostModel::class.java)
		}
	}

	override suspend fun searchWithFilter(criteria: FilterCriteria): List<PostModel> {
		// Thực hiện tìm kiếm với các tiêu chí lọc
		return withContext(Dispatchers.IO) {
			var query = firestore.collection("posts").limit(100)

			// Thêm điều kiện lọc
			if (criteria.location.isNotEmpty()) {
				query = query.whereEqualTo("location", criteria.location)
			}
			if (criteria.minPrice > 0) {
				query = query.whereGreaterThanOrEqualTo("price", criteria.minPrice)
			}
			if (criteria.maxPrice < 100_000_000) {
				query = query.whereLessThanOrEqualTo("price", criteria.maxPrice)
			}
			if (criteria.areaRange.first > 0 || criteria.areaRange.second < 1000) {
				query = query.whereGreaterThanOrEqualTo("acreage", criteria.areaRange.first)
					.whereLessThanOrEqualTo("acreage", criteria.areaRange.second)
			}
			if (criteria.interiorCondition.isNotEmpty()) {
				query = query.whereEqualTo("typeInterior", criteria.interiorCondition)
			}
			if (criteria.hasVideo) {
				query = query.whereEqualTo("hasVideo", true)
			}
			if (criteria.postedBy.isNotEmpty()) {
				query = query.whereEqualTo("postedBy", criteria.postedBy)
			}

			val querySnapshot = query.get().await()
			querySnapshot.toObjects(PostModel::class.java)
		}
	}
}