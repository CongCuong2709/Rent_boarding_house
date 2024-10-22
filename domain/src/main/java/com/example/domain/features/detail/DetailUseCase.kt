package com.example.domain.features.detail

import com.example.domain.features.models.PostModel
import javax.inject.Inject

class DetailUseCase @Inject constructor(
	private val detailRepository : DetailRepository,
) {
	suspend fun getDetailPost(postId: String): PostModel {
		return detailRepository.getDetail(postId)
	}
}