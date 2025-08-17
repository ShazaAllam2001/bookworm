package com.example.bookworm.sharedPref.data

import androidx.datastore.core.IOException
import androidx.datastore.core.Serializer
import java.io.OutputStream
import java.io.InputStream
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


object UserPreferencesSerializer : Serializer<User> {
    override val defaultValue: User
        get() = User()

    override suspend fun writeTo(t: User, output: OutputStream) {
        val jsonUser = Gson().toJson(t)
        withContext(Dispatchers.IO) {
            output.write(jsonUser.toByteArray())
        }
    }

    override suspend fun readFrom(input: InputStream): User {
        try {
            return Gson().fromJson(input.readBytes().decodeToString(), User::class.java)
        } catch (exception: IOException) {
            throw IOException("Cannot read Datastore!", exception)
        }
    }


}