package com.example.bookworm.sharedPref.data

import android.content.Context
import android.util.Log
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach


private val Context.datastore by dataStore("user.json", UserPreferencesSerializer)

class PrefRepo(private val context: Context) {
    suspend fun savePreferences(
        uid: String,
        displayName: String,
        email: String,
        photoUrl: String,
        token: String
    ) {
        context.datastore.updateData {
            it.copy(uid = uid, displayName = displayName, email = email, photoUrl = photoUrl, token = token)
        }
    }

    suspend fun readPreferences(): User {
        val user: User = context.datastore.data.first()
        Log.d("UserInfo Saved", user.toString())
        return user
    }
}