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


class PrefRepo(private val context: Context) {
    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "user_settings")

    companion object {
        val USER_TOKEN = stringPreferencesKey("user_token")
    }


    val tokenFlow: Flow<String> = context.datastore.data
        .map { preferences ->
            preferences[USER_TOKEN] ?: ""
        }

    suspend fun setToken(token: String) {
        context.datastore.edit { preferences ->
            preferences[USER_TOKEN] = token
        }
    }
}