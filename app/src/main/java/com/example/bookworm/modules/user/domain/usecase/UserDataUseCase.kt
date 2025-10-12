package com.example.bookworm.modules.user.domain.usecase

import android.util.Log
import com.example.bookworm.modules.user.data.model.UserData
import com.example.bookworm.modules.user.data.model.toDomain
import com.example.bookworm.modules.user.domain.repository.UserDataRepository
import com.example.bookworm.modules.user.domain.repository.UserPreferencesRepository
import com.example.bookworm.modules.user.domain.model.UserDataDomainModel
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val userDataRepository: UserDataRepository
) {
    suspend operator fun invoke(): Result<UserDataDomainModel> {
        return runCatching {
            val userPref = userPreferencesRepository.getUserPreferences().getOrNull()
            val uid = userPref?.uid
            val userData = userDataRepository.readUser(uid ?: "").getOrNull()
            Log.d("user", userData?.toDomain().toString())
            if (userData != null) {
                userData.toDomain()
            }
            else {
                val newUserData = UserData(displayName = userPref?.displayName ?: "")
                userDataRepository.saveUser(
                    userId = uid ?: "",
                    userData = newUserData
                )
                newUserData.toDomain()
            }
        }
    }
}