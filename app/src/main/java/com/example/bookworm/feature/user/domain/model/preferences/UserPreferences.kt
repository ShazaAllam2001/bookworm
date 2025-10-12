package com.example.bookworm.feature.user.domain.model.preferences


data class UserPreferences(
    val uid: String = "",
    val displayName: String = "",
    val email: String = "",
    val photoUrl: String = "",
    val token: String = "",
    val expirationTimeStamp: Long = 0
)