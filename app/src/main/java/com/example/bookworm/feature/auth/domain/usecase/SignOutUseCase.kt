package com.example.bookworm.feature.auth.domain.usecase

import com.example.bookworm.feature.auth.domain.model.AuthResult
import com.example.bookworm.feature.auth.domain.model.User
import com.example.bookworm.feature.auth.domain.repository.AuthRepository
import com.example.bookworm.modules.user.data.model.UserPreferences
import com.example.bookworm.modules.user.domain.repository.UserPreferencesRepository
import javax.inject.Inject


class SignOutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) {
    suspend operator fun invoke(): AuthResult {
        val result = authRepository.signOut()
        if (result.isSuccess) {
            userPreferencesRepository.saveUserPreferences(UserPreferences())
            return AuthResult.Success(User.empty())
        }
        return AuthResult.Error(result.exceptionOrNull()?.message ?: "")
    }
}