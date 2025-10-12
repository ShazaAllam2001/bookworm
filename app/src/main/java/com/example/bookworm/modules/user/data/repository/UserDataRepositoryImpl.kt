package com.example.bookworm.modules.user.data.repository

import com.example.bookworm.modules.user.data.remote.UserDataSource
import com.example.bookworm.modules.user.data.model.UserData
import com.example.bookworm.modules.user.data.model.toUserData
import com.example.bookworm.modules.user.domain.repository.UserDataRepository
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
): UserDataRepository {

    override suspend fun saveUser(userId: String, userData: UserData) {
        if (userId != "")
            userDataSource.saveUser(userId, userData)
    }

    override suspend fun readUser(userId: String): Result<UserData> {
        return runCatching {
            userDataSource.readUser(userId).toUserData()
        }
    }

    override suspend fun saveName(userId: String, name: String) {
        userDataSource.saveName(userId, name)
    }

    override suspend fun saveCategories(userId: String, categories: List<String>) {
        userDataSource.saveCategories(userId, categories)
    }

    override suspend fun saveNotify(userId: String, notify: Boolean) {
        userDataSource.saveNotify(userId, notify)
    }

}