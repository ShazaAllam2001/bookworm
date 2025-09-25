package com.example.bookworm.feature.user.data.repository

import com.example.bookworm.feature.user.domain.model.userData.UserData
import com.example.bookworm.feature.user.data.remote.UserDataSource
import com.example.bookworm.feature.user.domain.repository.UserDataRepository
import com.google.firebase.firestore.DocumentSnapshot
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
): UserDataRepository {

    override suspend fun saveUser(userId: String, userData: UserData) {
        userDataSource.saveUser(userId, userData)
    }

    override suspend fun readUser(userId: String): DocumentSnapshot {
        return userDataSource.readUser(userId)
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