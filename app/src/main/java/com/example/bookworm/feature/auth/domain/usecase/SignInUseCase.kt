package com.example.bookworm.feature.auth.domain.usecase

import android.app.Activity
import com.example.bookworm.feature.auth.domain.model.AuthResult
import com.example.bookworm.feature.auth.domain.repository.AuthRepository
import com.example.bookworm.modules.user.data.model.UserPreferences
import com.example.bookworm.modules.user.domain.repository.UserPreferencesRepository
import javax.inject.Inject


class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) {
    suspend operator fun invoke(context: Activity): AuthResult {
        val result = authRepository.signIn(context)
        if (result.isSuccess) {
            val user = result.getOrNull()
            if (user != null) {
                val userPref = UserPreferences(
                    uid = user.uid, displayName = user.displayName, email = user.email,
                    photoUrl = user.photoUrl, token = user.token, expirationTimeStamp = user.expirationTimeStamp
                )
                userPreferencesRepository.saveUserPreferences(userPref)
                return AuthResult.Success(user)
            }
        }
        return AuthResult.Error(result.exceptionOrNull()?.message ?: "")
    }
}