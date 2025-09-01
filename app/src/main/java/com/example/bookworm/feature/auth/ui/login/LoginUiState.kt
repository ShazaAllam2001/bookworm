package com.example.bookworm.feature.auth.ui.login

import com.example.bookworm.feature.auth.domain.model.User
import com.example.bookworm.feature.auth.domain.model.UserPreferences

data class LoginUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val userPref: UserPreferences? = null,
    val errorMessage: String? = null,
    val isSignedIn: Boolean = false
)