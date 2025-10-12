package com.example.bookworm.feature.user.domain.usecase

import com.example.bookworm.feature.user.data.mapper.UserMapper
import com.example.bookworm.feature.user.domain.model.userData.UserDataResult
import com.example.bookworm.feature.user.domain.repository.UserDataRepository
import com.example.bookworm.feature.user.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class EditCategoriesUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val userDataRepository: UserDataRepository,
    private val userMapper: UserMapper
) {
    suspend operator fun invoke(categories: List<String>): UserDataResult {
        val userPref = userPreferencesRepository.getUserPreferences()
        val uid = userPref.uid
        userDataRepository.saveCategories(uid, categories)
        val document = userDataRepository.readUser(uid)
        if (document.exists()) {
            val map = document.data
            return UserDataResult.Success(userMapper.mapToUserData(map))
        }
        return UserDataResult.Error("No Such Document Exist!")
    }
}