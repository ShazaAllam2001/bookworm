package com.example.bookworm.feature.settings.data.repository

import android.util.Log
import com.example.bookworm.feature.auth.domain.model.userData.UserData
import com.example.bookworm.feature.settings.domain.repository.UserDataRepository
import com.example.bookworm.feature.settings.domain.repository.UserDataRepository.Companion.CATEGORIES
import com.example.bookworm.feature.settings.domain.repository.UserDataRepository.Companion.NOTIFY
import com.example.bookworm.feature.settings.domain.repository.UserDataRepository.Companion.NAME
import com.example.bookworm.feature.settings.domain.repository.UserDataRepository.Companion.TAG
import com.example.bookworm.feature.settings.domain.repository.UserDataRepository.Companion.COLLECTION
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(): UserDataRepository {
    private val db = Firebase.firestore

    override suspend fun saveUser(userId: String, userData: UserData) {
        val user = mapOf(
            NAME to userData.displayName,
            CATEGORIES to userData.categories,
            NOTIFY to userData.notify
        )
        db.collection(COLLECTION).document(userId)
            .set(user)
            .addOnSuccessListener {
                Log.d(TAG, "Document successfully written!")
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Error writing document!", e)
            }
    }

    override suspend fun readUser(userId: String): DocumentSnapshot {
        return db.collection(COLLECTION).document(userId).get().await()
    }

    override suspend fun saveName(userId: String, name: String) {
        db.collection(COLLECTION).document(userId)
            .update(NAME, name)
            .addOnSuccessListener {
                Log.d(TAG, "Document successfully updated!")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error updating document", e)
            }
    }

    override suspend fun saveCategories(userId: String, categories: List<String>) {
        db.collection(COLLECTION).document(userId)
            .update(CATEGORIES, categories)
            .addOnSuccessListener {
                Log.d(TAG, "Document successfully updated!")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error updating document", e)
            }
    }

    override suspend fun saveNotify(userId: String, notify: Boolean) {
        db.collection(COLLECTION).document(userId)
            .update(NOTIFY, notify)
            .addOnSuccessListener {
                Log.d(TAG, "Document successfully updated!")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error updating document", e)
            }
    }

}