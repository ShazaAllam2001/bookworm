package com.example.bookworm.modules.user.data.local

import androidx.datastore.core.IOException
import androidx.datastore.core.Serializer
import com.example.bookworm.modules.user.data.model.UserPreferences
import java.io.OutputStream
import java.io.InputStream
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


object UserPreferencesSerializer : Serializer<UserPreferences> {
    override val defaultValue: UserPreferences
        get() = UserPreferences()

    override suspend fun writeTo(t: UserPreferences, output: OutputStream) {
        val jsonUser = Gson().toJson(t)
        withContext(Dispatchers.IO) {
            output.write(jsonUser.toByteArray())
        }
    }

    override suspend fun readFrom(input: InputStream): UserPreferences {
        try {
            return Gson().fromJson(input.readBytes().decodeToString(), UserPreferences::class.java)
        } catch (exception: IOException) {
            throw IOException("Cannot read Datastore!", exception)
        }
    }


}