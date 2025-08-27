package com.example.bookworm.activities.login.modules.data.firestore

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore


class DataRepo {
    private val db = Firebase.firestore

    companion object {
        const val NAME = "name"
        const val CATEGORIES = "categories"
        const val NOTIFY = "notify"
    }

    fun saveUser(userId: String, userData: UserData) {
        val user = mapOf(
            NAME to userData.displayName,
            CATEGORIES to userData.categories,
            NOTIFY to userData.notify
        )
        db.collection("user").document(userId)
            .set(user)
            .addOnSuccessListener {
                Log.d("Firestore", "Document successfully written!")
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error writing document!", e)
            }
    }

    fun readUser(userId: String): Task<DocumentSnapshot> {
        return db.collection("user").document(userId).get()
    }

    fun saveName(userId: String, name: String) {
        db.collection("user").document(userId)
            .update(NAME, name)
            .addOnSuccessListener {
                Log.d("Firestore", "Document successfully updated!")
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error updating document", e)
            }
    }

    fun saveCategories(userId: String, categories: List<String>) {
        db.collection("user").document(userId)
            .update(CATEGORIES, categories)
            .addOnSuccessListener {
                Log.d("Firestore", "Document successfully updated!")
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error updating document", e)
            }
    }

    fun saveNotify(userId: String, notify: Boolean) {
        db.collection("user").document(userId)
            .update(NOTIFY, notify)
            .addOnSuccessListener {
                Log.d("Firestore", "Document successfully updated!")
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error updating document", e)
            }
    }
}