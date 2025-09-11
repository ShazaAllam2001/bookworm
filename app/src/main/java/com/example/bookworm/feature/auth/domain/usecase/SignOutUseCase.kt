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
        return when (val result = authRepository.signOut()) {
            is AuthResult.Success -> {
                val userPref = UserPreferences("","","","","",0)
                userPreferencesRepository.saveUserPreferences(userPref)
                AuthResult.Success(User.empty())
            }
            is AuthResult.Error -> result
            AuthResult.Loading -> result
        }
    }
}