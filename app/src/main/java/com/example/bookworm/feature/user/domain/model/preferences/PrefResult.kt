package com.example.bookworm.feature.user.domain.model.preferences

sealed class PrefResult {
    data class Success(val user: UserPreferences) : PrefResult()
    data class Error(val message: String) : PrefResult()
}