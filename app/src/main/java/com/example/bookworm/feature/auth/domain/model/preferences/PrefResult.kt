package com.example.bookworm.feature.auth.domain.model.preferences

sealed class PrefResult {
    data class Success(val user: UserPreferences) : PrefResult()
    data class Error(val message: String) : PrefResult()
    data object Loading : PrefResult()
}