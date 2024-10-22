package com.example.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.features.home.HomeUseCase
import com.example.domain.features.models.PostModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val homeUseCase :  HomeUseCase
) : ViewModel(){

	private val _homeData = MutableLiveData<List<PostModel>>()
	val homeData: LiveData<List<PostModel>> get() = _homeData

	init {
		loadHomeData()
	}

	private fun loadHomeData(){
		viewModelScope.launch {
			val result = homeUseCase.getHomeData()
			_homeData.value = result
		}
	}

	// Function to filter data by location
	fun filterByLocation(location: String) {
		viewModelScope.launch {
			val result = homeUseCase.getHomeDataByLocation(location)
			_homeData.value = result
		}
	}
}