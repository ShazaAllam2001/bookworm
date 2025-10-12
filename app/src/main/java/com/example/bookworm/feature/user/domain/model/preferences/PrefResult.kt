package com.example.bookworm.feature.user.domain.model.preferences

import com.example.bookworm.modules.user.data.model.UserPreferences

sealed class PrefResult {
    data class Success(val user: UserPreferences) : PrefResult()
    data class Error(val message: String) : PrefResult()
}