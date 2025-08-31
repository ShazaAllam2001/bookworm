package com.example.bookworm.feature.auth.domain.repository

import com.example.bookworm.feature.auth.domain.model.UserPreferences

interface UserPreferencesRepository {
    suspend fun getUserPreferences(): UserPreferences
    suspend fun saveUserPreferences(userPref: UserPreferences)
}