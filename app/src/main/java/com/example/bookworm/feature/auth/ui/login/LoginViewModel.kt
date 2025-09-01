package com.example.bookworm.feature.auth.ui.login

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.example.bookworm.feature.auth.domain.model.AuthResult
import com.example.bookworm.feature.auth.domain.usecase.SignInUseCase
import com.example.bookworm.feature.auth.domain.usecase.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookworm.feature.auth.domain.model.PrefResult
import com.example.bookworm.feature.auth.domain.usecase.TokenUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val tokenUseCase: TokenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun signIn(context: Activity) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            when (val result = signInUseCase(context)) {
                is AuthResult.Success -> {
                    _uiState.value = LoginUiState(
                        user = result.user,
                        isSignedIn = true
                    )
                }
                is AuthResult.Error -> {
                    _uiState.value = LoginUiState(
                        errorMessage = result.message
                    )
                }
                AuthResult.Loading -> { }
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            when (val result = signOutUseCase()) {
                is AuthResult.Success -> {
                    _uiState.value = LoginUiState(
                        user = result.user,
                        isSignedIn = false
                    )
                }
                is AuthResult.Error -> {
                    _uiState.value = LoginUiState(
                        errorMessage = result.message
                    )
                }
                AuthResult.Loading -> { }
            }
        }
    }

    fun checkToken() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            when (val result = tokenUseCase()) {
                is PrefResult.Success -> {
                    _uiState.value = LoginUiState(
                        userPref = result.user,
                        isSignedIn = false
                    )
                }
                is PrefResult.Error -> {
                    _uiState.value = LoginUiState(
                        errorMessage = result.message
                    )
                }
                PrefResult.Loading -> { }
            }
        }
    }
}