package com.example.bookworm.feature.auth.domain.repository

import com.example.bookworm.feature.auth.domain.model.UserData
import com.google.firebase.firestore.DocumentSnapshot

interface UserDataRepository {
    suspend fun saveUser(userId: String, userData: UserData)
    suspend fun readUser(userId: String): DocumentSnapshot
    suspend fun saveName(userId: String, name: String)
    suspend fun saveCategories(userId: String, categories: List<String>)
    suspend fun saveNotify(userId: String, notify: Boolean)
}