package com.example.bookworm.feature.auth.domain.usecase

import com.example.bookworm.feature.auth.domain.model.auth.AuthResult
import com.example.bookworm.feature.auth.domain.model.auth.User
import com.example.bookworm.feature.auth.domain.model.preferences.UserPreferences
import com.example.bookworm.feature.auth.domain.repository.AuthRepository
import com.example.bookworm.feature.auth.domain.repository.UserPreferencesRepository
import javax.inject.Inject


class SignOutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) {
    suspend operator fun invoke(): AuthResult {
        val result = authRepository.signOut()
        if (result.isSuccess) {
            val userPref = UserPreferences("","","","","",0)
            userPreferencesRepository.saveUserPreferences(userPref)
            return AuthResult.Success(User.empty())
        }
        return AuthResult.Error(result.exceptionOrNull()?.message ?: "")
    }
}