package com.example.bookworm.feature.settings.data.remote

import com.example.bookworm.feature.auth.domain.model.userData.UserData
import com.example.bookworm.feature.settings.domain.repository.UserDataRepository.Companion.CATEGORIES
import com.example.bookworm.feature.settings.domain.repository.UserDataRepository.Companion.COLLECTION
import com.example.bookworm.feature.settings.domain.repository.UserDataRepository.Companion.NAME
import com.example.bookworm.feature.settings.domain.repository.UserDataRepository.Companion.NOTIFY
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserDataSource @Inject constructor() {
    private val db = Firebase.firestore

    suspend fun saveUser(userId: String, userData: UserData) {
        val user = mapOf(
            NAME to userData.displayName,
            CATEGORIES to userData.categories,
            NOTIFY to userData.notify
        )
        db.collection(COLLECTION).document(userId)
            .set(user)
            .await()
    }

    suspend fun readUser(userId: String): DocumentSnapshot {
        return db.collection(COLLECTION).document(userId).get().await()
    }

    suspend fun saveName(userId: String, name: String) {
        db.collection(COLLECTION).document(userId)
            .update(NAME, name)
            .await()
    }

    suspend fun saveCategories(userId: String, categories: List<String>) {
        db.collection(COLLECTION).document(userId)
            .update(CATEGORIES, categories)
            .await()
    }

    suspend fun saveNotify(userId: String, notify: Boolean) {
        db.collection(COLLECTION).document(userId)
            .update(NOTIFY, notify)
            .await()
    }
}