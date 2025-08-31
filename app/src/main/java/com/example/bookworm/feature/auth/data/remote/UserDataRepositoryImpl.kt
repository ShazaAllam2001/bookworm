package com.example.bookworm.feature.auth.data.remote

import android.util.Log
import com.example.bookworm.feature.auth.domain.model.UserData
import com.example.bookworm.feature.auth.domain.repository.UserDataRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class UserDataRepositoryImpl: UserDataRepository {
    private val db = Firebase.firestore

    companion object {
        const val TAG = "user"
        const val COLLECTION = "user"
        const val NAME = "name"
        const val CATEGORIES = "categories"
        const val NOTIFY = "notify"
    }

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