package com.example.bookworm.feature.auth.data.repository

import com.example.bookworm.feature.auth.data.local.UserPreferencesDataSource
import com.example.bookworm.feature.auth.domain.model.preferences.UserPreferences
import com.example.bookworm.feature.auth.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor(
    private val userPreferencesDataSource: UserPreferencesDataSource
) : UserPreferencesRepository {

    override suspend fun getUserPreferences(): UserPreferences {
        return userPreferencesDataSource.getUserPreferences()
    }

    override suspend fun saveUserPreferences(userPref: UserPreferences) {
        userPreferencesDataSource.saveUserPreferences(userPref)
    }

}
