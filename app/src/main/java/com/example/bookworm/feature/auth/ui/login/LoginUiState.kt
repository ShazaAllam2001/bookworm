package com.example.bookworm.feature.auth.ui.login

import com.example.bookworm.feature.auth.domain.model.auth.User

data class LoginUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val errorMessage: String? = null,
    val isSignedIn: Boolean = false
)