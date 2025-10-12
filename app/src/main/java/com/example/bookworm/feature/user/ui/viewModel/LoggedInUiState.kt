package com.example.bookworm.feature.user.ui.viewModel

import com.example.bookworm.modules.user.data.model.UserData
import com.example.bookworm.modules.user.data.model.UserPreferences

data class LoggedInUiState(
    val isLoading: Boolean = false,
    val isSignedIn: Boolean = false,
    val userData: UserData? = null,
    val userPref: UserPreferences? = null,
    val errorMessage: String? = null
)