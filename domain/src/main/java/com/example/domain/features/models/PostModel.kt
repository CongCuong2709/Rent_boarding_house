package com.example.domain.features.models

data class PostModel(
	val user_id : String,
	val id : String,
	val title : String,
	val price : String,
	val location : String,
	val typeInterior : String,
	val acreage : String,
	val description : String,
	val imageUrl : String
){
	constructor() : this(user_id = "",id = "",title = "", price = "",
		location = "", typeInterior = "", acreage = "",
		description = "", imageUrl = "")

	// Chuyển PostModel thành Map để lưu vào Firestore
	fun toMap(): Map<String, Any> {
		return mapOf(
			"user_id" to user_id,
			"id" to id,
			"title" to title,
			"price" to price,
			"location" to location,
			"typeInterior" to typeInterior,
			"acreage" to acreage,
			"description" to description,
			"imageUrl" to imageUrl
		)
	}
}