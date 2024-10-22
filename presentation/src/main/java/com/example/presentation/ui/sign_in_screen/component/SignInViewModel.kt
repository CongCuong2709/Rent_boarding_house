package com.example.presentation.ui.sign_in_screen.component

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.features.signin.AuthState
import com.example.domain.features.signin.SignInRepository
import com.example.domain.features.signin.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor
	(private val signInUseCase : SignInUseCase) : ViewModel(){

	private val _authState = MutableLiveData<AuthState>()
	val authState : LiveData<AuthState> = _authState

	init{
		checkAuthStatus()
	}

	fun checkAuthStatus() {
		_authState.value = signInUseCase.checkAuthStatus()
	}

	fun login(email: String, password: String) {
		if (email.isEmpty() || password.isEmpty()) {
			_authState.value = AuthState.Error("Email or password can't be empty")
			return
		}
		_authState.value = AuthState.Loading
		viewModelScope.launch {
			val result = signInUseCase(email, password)
			_authState.value = result

			// Thêm log để kiểm tra trạng thái sau khi đăng nhập
			when (result) {
				is AuthState.Authenticated -> Log.d("LoginViewModel", "Login successful")
				is AuthState.Error -> Log.d("LoginViewModel", "Login failed: ${result.message}")
				else -> Log.d("LoginViewModel", "Login status: $result")
			}
		}
	}

	fun signup(email: String, password: String) {
		if (email.isEmpty() || password.isEmpty()) {
			_authState.value = AuthState.Error("Email or password can't be empty")
			return
		}
		_authState.value = AuthState.Loading
//		viewModelScope.launch {
//			var result = signInUseCase.signup(email, password)
//			_authState.value = authRepository.signup(email, password)
//		}
	}

	fun signout() {
		_authState.value = signInUseCase.signout()
	}

}