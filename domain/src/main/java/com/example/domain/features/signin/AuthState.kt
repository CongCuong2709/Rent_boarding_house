package com.example.domain.features.signin

sealed class AuthState {
	object Authenticated : AuthState()
	object Unauthenticated : AuthState()
	object Loading : AuthState()
	data class Error(val message: String) : AuthState()
}