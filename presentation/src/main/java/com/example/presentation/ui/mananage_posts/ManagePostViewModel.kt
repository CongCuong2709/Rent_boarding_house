package com.example.presentation.ui.mananage_posts

import androidx.lifecycle.ViewModel
import com.example.domain.features.managePosts.ManagePostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ManagePostViewModel @Inject constructor(
	private val managePostUseCase: ManagePostUseCase
) : ViewModel()
{

}