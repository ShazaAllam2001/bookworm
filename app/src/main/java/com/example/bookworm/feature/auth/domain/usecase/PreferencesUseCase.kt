package com.example.bookworm.feature.auth.domain.usecase

import android.util.Log
import com.example.bookworm.feature.auth.domain.model.preferences.PrefResult
import com.example.bookworm.feature.auth.domain.repository.UserPreferencesRepository
import javax.inject.Inject


class PreferencesUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) {
    suspend operator fun invoke(): PrefResult {
        val user = userPreferencesRepository.getUserPreferences()
        val currentTimeSeconds = System.currentTimeMillis() / 1000
        Log.d("Auth", "Current Time: $currentTimeSeconds, Expiration Time: ${user.expirationTimeStamp}")
        if (user.token != "" && currentTimeSeconds < user.expirationTimeStamp) {
            return PrefResult.Success(user)
        }
        return PrefResult.Error("User Preferences is empty!")
    }
}