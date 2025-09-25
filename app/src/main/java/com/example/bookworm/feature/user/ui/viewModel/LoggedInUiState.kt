package com.example.bookworm.feature.user.ui.viewModel

import com.example.bookworm.feature.user.domain.model.userData.UserData
import com.example.bookworm.feature.user.domain.model.preferences.UserPreferences

data class LoggedInUiState(
    val isLoading: Boolean = false,
    val isSignedIn: Boolean = false,
    val userData: UserData? = null,
    val userPref: UserPreferences? = null,
    val errorMessage: String? = null
)