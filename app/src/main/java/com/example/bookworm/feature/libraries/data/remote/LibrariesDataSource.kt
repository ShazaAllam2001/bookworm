package com.example.bookworm.feature.libraries.data.remote

import android.content.Context
import com.example.bookworm.BuildConfig
import com.example.bookworm.R
import com.example.bookworm.feature.auth.domain.repository.UserPreferencesRepository
import com.example.bookworm.feature.books.domain.model.BooksResult
import com.example.bookworm.feature.libraries.domain.model.LibraryResult
import com.example.bookworm.feature.libraries.domain.model.ModifyLibraryResult
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val KEY = BuildConfig.API_KEY

class LibrariesDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val librariesApi: LibrariesApiService,
    private val userPreferencesRepository: UserPreferencesRepository
) {
    suspend fun fetchLibraries(): LibraryResult {
        return runCatching {
            val token = userPreferencesRepository.getUserPreferences().token
            val listResult = librariesApi.getMyBookshelves(
                token = "Bearer $token",
                apiKey = KEY
            )
            LibraryResult.Success(listResult.items)
        }.getOrElse { e ->
            LibraryResult.Error(e.message)
        }
    }

    suspend fun getLibraryBooks(shelfId: Int): BooksResult {
        return runCatching {
            val token = userPreferencesRepository.getUserPreferences().token
            val listResult = librariesApi.getShelfBooks(
                shelfId = shelfId,
                token = "Bearer $token",
                apiKey = KEY
            )
            BooksResult.Success(listResult.body()?.items ?: emptyList())
        }.getOrElse { e ->
            BooksResult.Error(e.message)
        }
    }

    suspend fun addBookToShelf(shelfId: Int, volumeId: String): ModifyLibraryResult {
        return runCatching {
            val token = userPreferencesRepository.getUserPreferences().token
            val result = librariesApi.addBookToShelf(
                shelfId = shelfId,
                volumeId = volumeId,
                token = "Bearer $token",
                apiKey = KEY
            )
            return if (result.isSuccessful)
                ModifyLibraryResult.Success(result.isSuccessful)
            else
                ModifyLibraryResult.Error(context.getString(R.string.library_not_modified))
        }.getOrElse { e ->
            ModifyLibraryResult.Error(e.message)
        }
    }

    suspend fun removeBookFromShelf(shelfId: Int, volumeId: String): ModifyLibraryResult {
        return runCatching {
            val token = userPreferencesRepository.getUserPreferences().token
            val result = librariesApi.removeBookFromShelf(
                shelfId = shelfId,
                volumeId = volumeId,
                token = "Bearer $token",
                apiKey = KEY
            )
            return if (result.isSuccessful)
                ModifyLibraryResult.Success(result.isSuccessful)
            else
                ModifyLibraryResult.Error(context.getString(R.string.library_not_modified))
        }.getOrElse { e ->
            ModifyLibraryResult.Error(e.message)
        }
    }

    suspend fun removeAllBooksFromShelf(shelfId: Int): ModifyLibraryResult {
        return runCatching {
            val token = userPreferencesRepository.getUserPreferences().token
            val result = librariesApi.removeAllBooksFromShelf(
                shelfId = shelfId,
                token = "Bearer $token",
                apiKey = KEY
            )
            return if (result.isSuccessful)
                ModifyLibraryResult.Success(result.isSuccessful)
            else
                ModifyLibraryResult.Error(context.getString(R.string.library_not_modified))
        }.getOrElse { e ->
            ModifyLibraryResult.Error(e.message)
        }
    }
}