package com.example.bookworm.feature.user.domain.repository

import com.example.bookworm.feature.user.domain.model.preferences.UserPreferences

interface UserPreferencesRepository {
    suspend fun getUserPreferences(): UserPreferences
    suspend fun saveUserPreferences(userPref: UserPreferences)
}