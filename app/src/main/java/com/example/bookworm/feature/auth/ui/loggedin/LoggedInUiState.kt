package com.example.bookworm.feature.auth.ui.loggedin

import com.example.bookworm.feature.auth.domain.model.userData.UserData
import com.example.bookworm.feature.auth.domain.model.preferences.UserPreferences

data class LoggedInUiState(
    val isLoading: Boolean = false,
    val userData: UserData? = null,
    val userPref: UserPreferences? = null,
    val errorMessage: String? = null,
    val isSignedIn: Boolean = false
)