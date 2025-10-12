package com.example.bookworm.modules.user.data.model

import com.example.bookworm.modules.user.ui.model.UserPreferencesUiModel

data class UserPreferences(
    val uid: String = "",
    val displayName: String = "",
    val email: String = "",
    val photoUrl: String = "",
    val token: String = "",
    val expirationTimeStamp: Long = 0
)

fun UserPreferences.toDomain(): UserPreferencesUiModel {
    return UserPreferencesUiModel(
        uid = uid,
        displayName = displayName,
        email = email,
        photoUrl = photoUrl,
        token = token,
        expirationTimeStamp = expirationTimeStamp
    )
}
