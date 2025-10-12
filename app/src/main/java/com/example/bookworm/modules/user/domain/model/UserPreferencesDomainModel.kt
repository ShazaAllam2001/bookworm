package com.example.bookworm.modules.user.domain.model

data class UserPreferencesDomainModel(
    val uid: String = "",
    val displayName: String = "",
    val email: String = "",
    val photoUrl: String = "",
    val token: String = "",
    val expirationTimeStamp: Long = 0
)