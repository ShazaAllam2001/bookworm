package com.example.bookworm.activities.login.modules.data.preferences

import android.content.Context
import android.util.Log
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.first


private val Context.datastore by dataStore("user.json", UserPreferencesSerializer)

class PrefRepo(private val context: Context) {
    suspend fun savePreferences(
        uid: String,
        email: String,
        photoUrl: String,
        token: String,
        expirationTimeStamp: Long
    ) {
        context.datastore.updateData {
            it.copy(uid = uid, email = email, photoUrl = photoUrl, token = token, expirationTimeStamp = expirationTimeStamp)
        }
    }

    suspend fun readPreferences(): UserPref {
        val user: UserPref = context.datastore.data.first()
        Log.d("UserInfo Saved", user.toString())
        return user
    }
}