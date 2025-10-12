package com.example.bookworm.feature.libraries.data.remote

import com.example.bookworm.BuildConfig
import com.example.bookworm.feature.books.data.model.BookItem
import com.example.bookworm.feature.libraries.data.model.Shelf
import javax.inject.Inject

private const val KEY = BuildConfig.API_KEY

class LibrariesDataSource @Inject constructor(
    private val librariesApi: LibrariesApiService
) {
    suspend fun fetchLibraries(token: String): Result<List<Shelf>> {
        return runCatching {
            val listResult = librariesApi.getMyBookshelves(
                token = "Bearer $token",
                apiKey = KEY
            )
            listResult.items
        }
    }

    suspend fun getLibraryBooks(token: String, shelfId: Int): Result<List<BookItem>> {
        return runCatching {
            val listResult = librariesApi.getShelfBooks(
                shelfId = shelfId,
                token = "Bearer $token",
                apiKey = KEY
            )
            listResult.body()?.items ?: emptyList()
        }
    }

    suspend fun addBookToShelf(token: String, shelfId: Int, volumeId: String): Result<Boolean> {
        return runCatching {
            val result = librariesApi.addBookToShelf(
                shelfId = shelfId,
                volumeId = volumeId,
                token = "Bearer $token",
                apiKey = KEY
            )
            result.isSuccessful
        }
    }

    suspend fun removeBookFromShelf(token: String, shelfId: Int, volumeId: String): Result<Boolean> {
        return runCatching {
            val result = librariesApi.removeBookFromShelf(
                shelfId = shelfId,
                volumeId = volumeId,
                token = "Bearer $token",
                apiKey = KEY
            )
            result.isSuccessful
        }
    }

    suspend fun removeAllBooksFromShelf(token: String, shelfId: Int): Result<Boolean> {
        return runCatching {
            val result = librariesApi.removeAllBooksFromShelf(
                shelfId = shelfId,
                token = "Bearer $token",
                apiKey = KEY
            )
            result.isSuccessful
        }
    }
}