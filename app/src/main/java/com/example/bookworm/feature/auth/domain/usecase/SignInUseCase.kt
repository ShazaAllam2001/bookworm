package com.example.bookworm.feature.auth.domain.usecase

import android.app.Activity
import com.example.bookworm.feature.auth.domain.model.auth.AuthResult
import com.example.bookworm.feature.auth.domain.model.preferences.UserPreferences
import com.example.bookworm.feature.auth.domain.repository.AuthRepository
import com.example.bookworm.feature.auth.domain.repository.UserPreferencesRepository
import javax.inject.Inject


class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) {
    suspend operator fun invoke(context: Activity): AuthResult {
        return when (val result = authRepository.signIn(context)) {
            is AuthResult.Success -> {
                val user = result.user
                val userPref = UserPreferences(user.uid, user.email, user.photoUrl, user.token, user.expirationTimeStamp)
                userPreferencesRepository.saveUserPreferences(userPref)
                AuthResult.Success(user)
            }
            is AuthResult.Error -> result
            AuthResult.Loading -> result
        }
    }
}