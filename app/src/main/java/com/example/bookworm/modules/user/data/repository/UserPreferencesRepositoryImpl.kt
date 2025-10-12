package com.example.bookworm.modules.user.data.repository

import com.example.bookworm.modules.user.data.local.UserPreferencesDataSource
import com.example.bookworm.modules.user.data.model.UserPreferences
import com.example.bookworm.modules.user.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor(
    private val userPreferencesDataSource: UserPreferencesDataSource
) : UserPreferencesRepository {

    override suspend fun getUserPreferences(): Result<UserPreferences> {
        return runCatching {
            userPreferencesDataSource.getUserPreferences()
        }
    }

    override suspend fun saveUserPreferences(userPref: UserPreferences) {
        userPreferencesDataSource.saveUserPreferences(userPref)
    }

}
