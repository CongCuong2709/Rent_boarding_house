package com.example.domain.features.signin

interface SignInRepository {
	suspend fun login(email: String, password: String): AuthState
	suspend fun signup(email: String, password: String): AuthState
	fun signout(): AuthState
	fun checkAuthStatus(): AuthState
}