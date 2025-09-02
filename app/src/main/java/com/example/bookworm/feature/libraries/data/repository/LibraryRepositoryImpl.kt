package com.example.bookworm.feature.libraries.data.repository

import com.example.bookworm.BuildConfig
import com.example.bookworm.common.network.BooksApiService
import com.example.bookworm.feature.auth.domain.repository.UserPreferencesRepository
import com.example.bookworm.feature.books.domain.model.BooksResult
import com.example.bookworm.feature.libraries.domain.model.LibraryResult
import com.example.bookworm.feature.libraries.domain.model.ModifyLibraryResult
import com.example.bookworm.feature.libraries.domain.repository.LibraryRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val KEY = BuildConfig.API_KEY

@Singleton
class LibraryRepositoryImpl @Inject constructor(
    private val booksApi: BooksApiService,
    private val userPreferencesRepository: UserPreferencesRepository
) : LibraryRepository {

    override suspend fun fetchLibraries(): LibraryResult {
        try {
            val token = userPreferencesRepository.getUserPreferences().token
            val listResult = booksApi.getMyBookshelves(
                token = "Bearer $token",
                apiKey = KEY
            )
            return LibraryResult.Success(listResult.items)
        } catch (e: IOException) {
            return LibraryResult.Error(e.message)
        } catch (e: HttpException) {
            return LibraryResult.Error(e.message)
        }
    }

    override suspend fun getLibraryBooks(shelfId: Int): BooksResult {
        try {
            val token = userPreferencesRepository.getUserPreferences().token
            val listResult = booksApi.getShelfBooks(
                shelfId = shelfId,
                token = "Bearer $token",
                apiKey = KEY
            )
            return BooksResult.Success(listResult.body()?.items ?: emptyList())
        } catch (e: IOException) {
            return BooksResult.Error(e.message)
        } catch (e: HttpException) {
            return BooksResult.Error(e.message)
        }
    }

    override suspend fun addBookToShelf(shelfId: Int, volumeId: String): ModifyLibraryResult {
        try {
            val token = userPreferencesRepository.getUserPreferences().token
            val result = booksApi.addBookToShelf(
                shelfId = shelfId,
                volumeId = volumeId,
                token = "Bearer $token",
                apiKey = KEY
            )
            return if (result.isSuccessful)
                ModifyLibraryResult.Success(result.isSuccessful)
            else
                ModifyLibraryResult.Error("Library Not Modified")
        } catch (e: IOException) {
            return ModifyLibraryResult.Error(e.message)
        } catch (e: HttpException) {
            return ModifyLibraryResult.Error(e.message)
        }
    }

    override suspend fun removeBookFromShelf(shelfId: Int, volumeId: String): ModifyLibraryResult {
        try {
            val token = userPreferencesRepository.getUserPreferences().token
            val result = booksApi.removeBookFromShelf(
                shelfId = shelfId,
                volumeId = volumeId,
                token = "Bearer $token",
                apiKey = KEY
            )
            return if (result.isSuccessful)
                ModifyLibraryResult.Success(result.isSuccessful)
            else
                ModifyLibraryResult.Error("Library Not Modified")
        } catch (e: IOException) {
            return ModifyLibraryResult.Error(e.message)
        } catch (e: HttpException) {
            return ModifyLibraryResult.Error(e.message)
        }
    }

    override suspend fun removeAllBooksFromShelf(shelfId: Int): ModifyLibraryResult {
        try {
            val token = userPreferencesRepository.getUserPreferences().token
            val result = booksApi.removeAllBooksFromShelf(
                shelfId = shelfId,
                token = "Bearer $token",
                apiKey = KEY
            )
            return if (result.isSuccessful)
                ModifyLibraryResult.Success(result.isSuccessful)
            else
                ModifyLibraryResult.Error("Library Not Modified")
        } catch (e: IOException) {
            return ModifyLibraryResult.Error(e.message)
        } catch (e: HttpException) {
            return ModifyLibraryResult.Error(e.message)
        }
    }
}