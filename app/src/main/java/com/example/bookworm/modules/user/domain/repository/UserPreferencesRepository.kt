package com.example.bookworm.modules.user.domain.repository

import com.example.bookworm.modules.user.data.model.UserPreferences

interface UserPreferencesRepository {
    suspend fun getUserPreferences(): Result<UserPreferences>
    suspend fun saveUserPreferences(userPref: UserPreferences)
}