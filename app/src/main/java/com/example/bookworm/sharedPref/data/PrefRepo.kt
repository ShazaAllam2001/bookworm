package com.example.bookworm.sharedPref.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "user_settings")

class PrefRepo(private val context: Context) {
    val Context.dataStore by preferencesDataStore(name = "user_prefs")

    companion object {
        val USER_TOKEN = stringPreferencesKey("user_token")
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_EMAIL = stringPreferencesKey("user_email")
        val USER_PHOTO = stringPreferencesKey("user_photo")
    }

    val tokenFlow: Flow<String> = context.datastore.data
        .map { preferences ->
            preferences[USER_TOKEN] ?: ""
        }
    val nameFlow: Flow<String> =  context.datastore.data
        .map { preferences ->
            preferences[USER_NAME] ?: ""
        }
    val mailFlow: Flow<String> = context.datastore.data
        .map { preferences ->
            preferences[USER_EMAIL] ?: ""
        }
    val photoFlow: Flow<String> = context.datastore.data
        .map { preferences ->
            preferences[USER_PHOTO] ?: ""
        }

    suspend fun setToken(token: String) {
        context.datastore.edit { preferences ->
            preferences[USER_TOKEN] = token
        }
    }

    suspend fun setUserInfo(
        name: String,
        email: String,
        photo: String
    ) {
        context.datastore.edit { preferences ->
            preferences[USER_NAME] = name
            preferences[USER_EMAIL] = email
            preferences[USER_PHOTO] = photo
        }
    }
}