package com.example.domain.features.detail

import com.example.domain.features.models.PostModel

interface DetailRepository {
	suspend fun getDetail(postId : String) : PostModel
}