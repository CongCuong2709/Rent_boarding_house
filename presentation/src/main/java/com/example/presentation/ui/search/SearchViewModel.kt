package com.example.presentation.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.features.models.FilterCriteria
import com.example.domain.features.models.PostModel
import com.example.domain.features.search.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
	private val searchUseCase : SearchUseCase
) : ViewModel(){
	private val _searchResults = MutableLiveData<List<PostModel>>()
	val searchResults: LiveData<List<PostModel>> get() = _searchResults

	private val _filterCriteria = MutableLiveData<FilterCriteria>(FilterCriteria())
	val filterCriteria: LiveData<FilterCriteria> get() = _filterCriteria

	fun updateFilterCriteria(criteria: FilterCriteria) {
		_filterCriteria.value = criteria
	}

	fun search(key: String) {
		viewModelScope.launch {
			val results = searchUseCase.search(key)
			_searchResults.value = results
		}
	}

	fun searchByLocation(location: String) {
		viewModelScope.launch {
			val results = searchUseCase.searchByLocation(location)
			_searchResults.value = results
		}
	}

	fun searchWithFilters() {
		viewModelScope.launch {
			// Lấy giá trị filterCriteria và truyền vào searchWithFilter
			val criteria = _filterCriteria.value ?: FilterCriteria()
			val results = searchUseCase.searchWithFilter(criteria)
			_searchResults.value = results
		}
	}
}