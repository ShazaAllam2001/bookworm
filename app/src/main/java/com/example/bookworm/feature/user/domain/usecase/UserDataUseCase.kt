package com.example.bookworm.feature.user.domain.usecase

import com.example.bookworm.feature.user.domain.model.userData.UserDataResult
import com.example.bookworm.modules.user.data.model.UserData
import com.example.bookworm.modules.user.domain.repository.UserDataRepository
import com.example.bookworm.modules.user.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class UserDataUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val userDataRepository: UserDataRepository
) {
    suspend operator fun invoke(): UserDataResult {
        val userPref = userPreferencesRepository.getUserPreferences().getOrNull()
        val uid = userPref?.uid
        if (uid != null) {
            val document = userDataRepository.readUser(uid)
            if (document.getOrNull() != null) {
                return UserDataResult.Success(document.getOrNull() ?: UserData())
            }
            else {
                val userData = UserData(displayName = userPref.displayName)
                userDataRepository.saveUser(
                    userId = uid,
                    userData = userData
                )
                return UserDataResult.Success(userData)
            }
        }
        return UserDataResult.Success(UserData())
    }
}