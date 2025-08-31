package com.example.bookworm.feature.auth.domain.usecase

import com.example.bookworm.feature.auth.domain.model.AuthResult
import com.example.bookworm.feature.auth.domain.repository.AuthRepository
import com.example.bookworm.feature.auth.domain.repository.UserPreferencesRepository
import javax.inject.Inject


class SignOutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) {
    suspend operator fun invoke(): AuthResult {
        return when (val result = authRepository.signIn()) {
            is AuthResult.Success -> {
                // ✅ Business logic in domain layer
                val user = result.user
                /*val existingPrefs = userPreferencesRepository.getUserPreferences()
                val displayName = existingPrefs.displayName.ifEmpty { user.displayName }

                userPreferencesRepository.saveUserPreferences(
                    user.copy(displayName = displayName)
                )*/
                AuthResult.Success(user)
            }
            is AuthResult.Error -> result
            AuthResult.Loading -> result
        }
    }
}