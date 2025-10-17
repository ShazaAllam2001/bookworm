package com.example.bookworm.modules.user.presentation.model

data class UserPreferencesUiModel(
    val uid: String = "",
    val displayName: String = "",
    val email: String = "",
    val photoUrl: String = "",
    val token: String = "",
    val expirationTimeStamp: Long = 0
)