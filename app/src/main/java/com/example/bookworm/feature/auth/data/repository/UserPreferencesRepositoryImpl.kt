package com.example.bookworm.feature.auth.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.dataStore
import com.example.bookworm.feature.auth.data.local.UserPreferencesSerializer
import com.example.bookworm.feature.auth.domain.model.UserPreferences
import com.example.bookworm.feature.auth.domain.repository.UserPreferencesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesRepositoryImpl @Inject constructor(
   @ApplicationContext private val context: Context
) : UserPreferencesRepository {
    private val Context.datastore by dataStore("user.json", UserPreferencesSerializer)

    override suspend fun getUserPreferences(): UserPreferences {
        val user: UserPreferences = context.datastore.data.first()
        Log.d("UserInfo Saved", user.toString())
        return user
    }

    override suspend fun saveUserPreferences(userPref: UserPreferences) {
        context.datastore.updateData {
            it.copy(uid = userPref.uid, email = userPref.email, photoUrl = userPref.photoUrl,
                token = userPref.token, expirationTimeStamp = userPref.expirationTimeStamp)
        }
    }

}