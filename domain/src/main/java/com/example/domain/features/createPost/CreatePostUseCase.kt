package com.example.domain.features.createPost

import android.media.Image
import com.example.domain.features.models.PostModel
import javax.inject.Inject

class CreatePostUseCase @Inject constructor(
	private val createPostRepository: CreatePostRepository
){
	suspend fun execute(post: PostModel, images: List<Image>): Result<String> {
		// Validate the post
		val isValid = createPostRepository.validatePost(post)
		if (!isValid) {
			return Result.failure(Exception("Invalid post data"))
		}

		// Upload images and get URLs
		val uploadedImages: List<String>
		try {
			uploadedImages = createPostRepository.uploadImages(images)
		} catch (e: Exception) {
			return Result.failure(Exception("Image upload failed"))
		}

//		// Create the post with uploaded images
//		val postWithImages = post.copy(imageUrls = uploadedImages)
		val success = createPostRepository.createPost(post)

		return if (success) {
			Result.success("Post created successfully")
		} else {
			Result.failure(Exception("Failed to create post"))
		}
	}
}