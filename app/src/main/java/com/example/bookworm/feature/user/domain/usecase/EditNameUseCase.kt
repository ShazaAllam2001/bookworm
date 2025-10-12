package com.example.bookworm.feature.user.domain.usecase

import com.example.bookworm.feature.user.domain.model.userData.UserDataResult
import com.example.bookworm.modules.user.data.model.UserData
import com.example.bookworm.modules.user.domain.repository.UserDataRepository
import com.example.bookworm.modules.user.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class EditNameUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val userDataRepository: UserDataRepository
) {
    suspend operator fun invoke(name: String): UserDataResult {
        val userPref = userPreferencesRepository.getUserPreferences().getOrNull()
        val uid = userPref?.uid
        if (uid != null) {
            userDataRepository.saveName(uid, name)
            val document = userDataRepository.readUser(uid)
            return UserDataResult.Success(document.getOrNull() ?: UserData())
        }
        return UserDataResult.Error("No Such Document Exist!")
    }
}