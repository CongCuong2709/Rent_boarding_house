package com.example.data.features.signin

import com.example.domain.features.signin.AuthState
import com.example.domain.features.signin.SignInRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRepositoryImpl @Inject constructor
	(private val auth : FirebaseAuth) : SignInRepository {
	override suspend fun login(email : String, password : String) : AuthState {
		return try{
			auth.signInWithEmailAndPassword(email, password).await()
			AuthState.Authenticated
		}catch (e : Exception){
			AuthState.Error(e.message ?: "login fail")
		}
	}

	override suspend fun signup(email : String, password : String) : AuthState {
		return try {
			auth.createUserWithEmailAndPassword(email, password).await()
			AuthState.Authenticated
		}catch (e : Exception){
			AuthState.Error(e.message ?: "signup fail")
		}
	}

	override fun signout() : AuthState {
		auth.signOut()
		return AuthState.Unauthenticated
	}

	override fun checkAuthStatus() : AuthState {
		return if (auth.currentUser == null) {
			AuthState.Unauthenticated
		} else {
			AuthState.Authenticated
		}
	}




}