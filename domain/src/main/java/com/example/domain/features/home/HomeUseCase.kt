package com.example.domain.features.home

import com.example.domain.features.models.PostModel
import javax.inject.Inject

class HomeUseCase @Inject constructor(
	private val homeRepository : HomeRepository
){
	suspend fun getHomeData() : List<PostModel>{
		return homeRepository.fetchHomeData()
	}

	// Fetch data by location
	suspend fun getHomeDataByLocation(location: String): List<PostModel> {
		return homeRepository.fetchHomeDataByLocation(location)
	}
}