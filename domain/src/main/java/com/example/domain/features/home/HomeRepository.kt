package com.example.domain.features.home

import com.example.domain.features.models.PostModel

interface HomeRepository {
	suspend fun fetchHomeData() : List<PostModel>

	suspend fun fetchHomeDataByLocation(location : String) : List<PostModel>
}