package com.example.bookworm.feature.libraries.data.repository

import com.example.bookworm.feature.user.domain.repository.UserPreferencesRepository
import com.example.bookworm.feature.books.domain.model.BooksResult
import com.example.bookworm.feature.libraries.data.remote.LibrariesDataSource
import com.example.bookworm.feature.libraries.domain.model.LibraryResult
import com.example.bookworm.feature.libraries.domain.model.ModifyLibraryResult
import com.example.bookworm.feature.libraries.domain.repository.LibraryRepository
import javax.inject.Inject

class LibraryRepositoryImpl @Inject constructor(
    private val librariesDataSource: LibrariesDataSource,
    private val userPreferencesRepository: UserPreferencesRepository
) : LibraryRepository {

    override suspend fun fetchLibraries(): LibraryResult {
        val token = userPreferencesRepository.getUserPreferences().token
        val result = librariesDataSource.fetchLibraries(token)
        if (result.isSuccess)
            return LibraryResult.Success(result.getOrDefault(emptyList()))
        return LibraryResult.Error(result.exceptionOrNull()?.message)
    }

    override suspend fun getLibraryBooks(shelfId: Int): BooksResult {
        val token = userPreferencesRepository.getUserPreferences().token
        val result = librariesDataSource.getLibraryBooks(token, shelfId)
        if (result.isSuccess)
            return BooksResult.Success(result.getOrDefault(emptyList()))
        return BooksResult.Error(result.exceptionOrNull()?.message)
    }

    override suspend fun addBookToShelf(shelfId: Int, volumeId: String): ModifyLibraryResult {
        val token = userPreferencesRepository.getUserPreferences().token
        val result = librariesDataSource.addBookToShelf(token, shelfId, volumeId)
        if (result.isSuccess)
            return ModifyLibraryResult.Success(result.getOrDefault(false))
        return ModifyLibraryResult.Error(result.exceptionOrNull()?.message)
    }

    override suspend fun removeBookFromShelf(shelfId: Int, volumeId: String): ModifyLibraryResult {
        val token = userPreferencesRepository.getUserPreferences().token
        val result = librariesDataSource.removeBookFromShelf(token, shelfId, volumeId)
        if (result.isSuccess)
            return ModifyLibraryResult.Success(result.getOrDefault(false))
        return ModifyLibraryResult.Error(result.exceptionOrNull()?.message)
    }

    override suspend fun removeAllBooksFromShelf(shelfId: Int): ModifyLibraryResult {
        val token = userPreferencesRepository.getUserPreferences().token
        val result = librariesDataSource.removeAllBooksFromShelf(token, shelfId)
        if (result.isSuccess)
            return ModifyLibraryResult.Success(result.getOrDefault(false))
        return ModifyLibraryResult.Error(result.exceptionOrNull()?.message)
    }
}