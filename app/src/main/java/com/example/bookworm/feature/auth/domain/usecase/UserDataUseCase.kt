package com.example.bookworm.feature.auth.domain.usecase

import com.example.bookworm.common.data.mapper.UserMapper
import com.example.bookworm.feature.auth.domain.model.userData.UserData
import com.example.bookworm.feature.auth.domain.model.userData.UserDataResult
import com.example.bookworm.feature.settings.domain.repository.UserDataRepository
import com.example.bookworm.feature.auth.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class UserDataUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val userDataRepository: UserDataRepository,
    private val userMapper: UserMapper
) {
    suspend operator fun invoke(): UserDataResult {
        val userPref = userPreferencesRepository.getUserPreferences()
        val uid = userPref.uid
        val document = userDataRepository.readUser(uid)
        if (document.exists()) {
            val map = document.data
            return UserDataResult.Success(userMapper.mapToUserData(map))
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
}