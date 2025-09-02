package com.example.bookworm.feature.auth.domain.usecase

import com.example.bookworm.feature.auth.domain.model.userData.UserData
import com.example.bookworm.feature.auth.domain.model.userData.UserDataResult
import com.example.bookworm.feature.auth.domain.repository.UserDataRepository
import com.example.bookworm.feature.auth.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class EditNameUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val userDataRepository: UserDataRepository
) {
    suspend operator fun invoke(name: String): UserDataResult {
        val userPref = userPreferencesRepository.getUserPreferences()
        val uid = userPref.uid
        userDataRepository.saveName(uid, name)
        val document = userDataRepository.readUser(uid)
        if (document.exists()) {
            val map = document.data
            val userData =  UserData(
                displayName = map?.get(UserDataRepository.NAME) as String,
                categories = map[UserDataRepository.CATEGORIES] as List<String>,
                notify = map[UserDataRepository.NOTIFY] as Boolean
            )
            return UserDataResult.Success(userData)
        }
        return UserDataResult.Error("No Such Document Exist!")
    }
}