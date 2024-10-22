package com.example.presentation.ui.create_post

import android.media.Image
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.features.createPost.CreatePostUseCase
import com.example.domain.features.models.PostModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
	private val createPostUseCase : CreatePostUseCase
) : ViewModel(){
	val postCreationState = mutableStateOf<PostCreationState>(PostCreationState.Idle)

	fun createPost(post: PostModel, images: List<Image>) {
		viewModelScope.launch {
			postCreationState.value = PostCreationState.Loading
			val result = createPostUseCase.execute(post, images)
			postCreationState.value = when (result.isSuccess) {
				true -> PostCreationState.Success(result.getOrNull() ?: "")
				false -> PostCreationState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
			}
		}
	}
}

sealed class PostCreationState {
	object Idle : PostCreationState()
	object Loading : PostCreationState()
	data class Success(val message: String) : PostCreationState()
	data class Error(val message: String) : PostCreationState()
}