package com.example.bookworm.feature.user.data.local

import android.util.Log
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.bookworm.feature.user.domain.model.preferences.UserPreferences
import kotlinx.coroutines.flow.first
import javax.inject.Inject

val Context.datastore by dataStore("user.json", UserPreferencesSerializer)

class UserPreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<UserPreferences>
) {

    suspend fun getUserPreferences(): UserPreferences {
        val user: UserPreferences = dataStore.data.first()
        Log.d("UserInfo Saved", user.toString())
        return user
    }

    suspend fun saveUserPreferences(userPref: UserPreferences) {
        dataStore.updateData { userPref }
    }
}