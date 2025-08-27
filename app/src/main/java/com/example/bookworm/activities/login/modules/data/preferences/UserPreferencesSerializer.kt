package com.example.bookworm.activities.login.modules.data.preferences

import androidx.datastore.core.IOException
import androidx.datastore.core.Serializer
import java.io.OutputStream
import java.io.InputStream
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


object UserPreferencesSerializer : Serializer<UserPref> {
    override val defaultValue: UserPref
        get() = UserPref()

    override suspend fun writeTo(t: UserPref, output: OutputStream) {
        val jsonUser = Gson().toJson(t)
        withContext(Dispatchers.IO) {
            output.write(jsonUser.toByteArray())
        }
    }

    override suspend fun readFrom(input: InputStream): UserPref {
        try {
            return Gson().fromJson(input.readBytes().decodeToString(), UserPref::class.java)
        } catch (exception: IOException) {
            throw IOException("Cannot read Datastore!", exception)
        }
    }


}