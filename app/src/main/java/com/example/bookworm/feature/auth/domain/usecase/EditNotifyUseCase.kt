package com.example.bookworm.feature.auth.domain.usecase

import com.example.bookworm.common.data.mapper.UserMapper
import com.example.bookworm.feature.auth.domain.model.userData.UserDataResult
import com.example.bookworm.feature.settings.domain.repository.UserDataRepository
import com.example.bookworm.feature.auth.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class EditNotifyUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val userDataRepository: UserDataRepository,
    private val userMapper: UserMapper
) {
    suspend operator fun invoke(notify: Boolean): UserDataResult {
        val userPref = userPreferencesRepository.getUserPreferences()
        val uid = userPref.uid
        userDataRepository.saveNotify(uid, notify)
        val document = userDataRepository.readUser(uid)
        if (document.exists()) {
            val map = document.data
            return UserDataResult.Success(userMapper.mapToUserData(map))
        }
        return UserDataResult.Error("No Such Document Exist!")
    }
}