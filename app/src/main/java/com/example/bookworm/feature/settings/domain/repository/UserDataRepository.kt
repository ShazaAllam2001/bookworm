package com.example.bookworm.feature.settings.domain.repository

import com.example.bookworm.feature.auth.domain.model.userData.UserData
import com.google.firebase.firestore.DocumentSnapshot

interface UserDataRepository {
    companion object {
        const val TAG = "user"
        const val COLLECTION = "user"
        const val NAME = "name"
        const val CATEGORIES = "categories"
        const val NOTIFY = "notify"
    }

    suspend fun saveUser(userId: String, userData: UserData)
    suspend fun readUser(userId: String): DocumentSnapshot
    suspend fun saveName(userId: String, name: String)
    suspend fun saveCategories(userId: String, categories: List<String>)
    suspend fun saveNotify(userId: String, notify: Boolean)
}