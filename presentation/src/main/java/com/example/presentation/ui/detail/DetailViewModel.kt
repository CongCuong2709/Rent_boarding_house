package com.example.presentation.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.features.detail.DetailUseCase
import com.example.domain.features.models.PostModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
	private val detailUseCase : DetailUseCase,
) : ViewModel(){
	private val _postDetail = MutableStateFlow<PostModel?>(null)
	val postDetail = _postDetail.asStateFlow()

	fun loadDetail(postId: String) {
		viewModelScope.launch {
			try {
				val post = detailUseCase.getDetailPost(postId)
				_postDetail.value = post
			} catch (e: Exception) {
				e.printStackTrace() // Xử lý lỗi
			}
		}
	}
}