package com.example.bookworm.feature.auth.domain.model


data class UserPreferences(
    val uid: String = "",
    val email: String = "",
    val photoUrl: String = "",
    val token: String = "",
    val expirationTimeStamp: Long = 0
)