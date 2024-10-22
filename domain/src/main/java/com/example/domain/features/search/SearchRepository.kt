package com.example.domain.features.search

import com.example.domain.features.models.FilterCriteria
import com.example.domain.features.models.PostModel

interface SearchRepository {
	suspend fun search(key: String): List<PostModel>

	suspend fun searchByLocation(location: String): List<PostModel>

	suspend fun searchWithFilter(criteria: FilterCriteria): List<PostModel>

}