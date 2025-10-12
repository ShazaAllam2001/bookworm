package com.example.bookworm.modules.user.domain.repository

import com.example.bookworm.modules.user.data.model.UserData

interface UserDataRepository {
    companion object {
        const val COLLECTION = "user"
        const val NAME = "name"
        const val CATEGORIES = "categories"
        const val NOTIFY = "notify"
    }

    suspend fun saveUser(userId: String, userData: UserData)
    suspend fun readUser(userId: String): Result<UserData>
    suspend fun saveName(userId: String, name: String)
    suspend fun saveCategories(userId: String, categories: List<String>)
    suspend fun saveNotify(userId: String, notify: Boolean)
}