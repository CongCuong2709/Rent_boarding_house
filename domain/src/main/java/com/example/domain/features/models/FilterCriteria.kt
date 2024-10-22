package com.example.domain.features.models

data class FilterCriteria(
	var location: String = "",
	var minPrice: Int = 0,
	var maxPrice: Int = 100_000_000, // Ví dụ giá tối đa
	var areaRange: Pair<Int, Int> = Pair(0, 1000), // Diện tích từ 0 đến 1000m²
	var interiorCondition: String = "",
	var hasVideo: Boolean = false,
	var postedBy: String = ""
)