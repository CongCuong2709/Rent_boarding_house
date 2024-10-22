package com.example.domain.features.search

import com.example.domain.features.models.FilterCriteria
import com.example.domain.features.models.PostModel
import javax.inject.Inject

class SearchUseCase @Inject constructor(
	private val searchRepository: SearchRepository

){
	suspend fun search(key: String): List<PostModel> {
		return searchRepository.search(key)
	}

	suspend fun searchByLocation(location: String): List<PostModel> {
		return searchRepository.searchByLocation(location)
	}

	suspend fun searchWithFilter(criteria: FilterCriteria): List<PostModel> {
		return searchRepository.searchWithFilter(criteria)
	}
}