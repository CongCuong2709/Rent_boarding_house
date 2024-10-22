package com.example.domain.features.createPost

import android.media.Image
import com.example.domain.features.models.PostModel

interface CreatePostRepository {

	suspend fun validatePost(post: PostModel): Boolean
	suspend fun uploadImages(images: List<Image>): List<String> // Return image URLs after upload
	suspend fun createPost(post: PostModel): Boolean
}