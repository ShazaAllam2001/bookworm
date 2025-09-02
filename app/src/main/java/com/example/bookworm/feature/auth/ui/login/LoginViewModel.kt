package com.example.bookworm.feature.auth.ui.login

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.example.bookworm.feature.auth.domain.model.auth.AuthResult
import com.example.bookworm.feature.auth.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
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
}