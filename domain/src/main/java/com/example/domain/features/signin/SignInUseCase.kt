package com.example.domain.features.signin

import javax.inject.Inject

class SignInUseCase @Inject constructor(
	private val authRepository : SignInRepository)
{
	suspend operator fun invoke(email : String, password : String) : AuthState{
		return authRepository.login(email, password)
	}

	fun checkAuthStatus(): AuthState {
		return authRepository.checkAuthStatus()
	}

	fun signout(): AuthState {
		return authRepository.signout()
	}
}

