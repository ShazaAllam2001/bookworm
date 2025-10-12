package com.example.bookworm.feature.libraries.data.repository

import com.example.bookworm.feature.books.domain.model.BooksResult
import com.example.bookworm.feature.libraries.data.remote.LibrariesDataSource
import com.example.bookworm.feature.libraries.domain.model.LibraryResult
import com.example.bookworm.feature.libraries.domain.model.ModifyLibraryResult
import com.example.bookworm.feature.libraries.domain.repository.LibraryRepository
import com.example.bookworm.modules.user.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class LibraryRepositoryImpl @Inject constructor(
    private val librariesDataSource: LibrariesDataSource,
    private val userPreferencesRepository: UserPreferencesRepository
) : LibraryRepository {

    override suspend fun fetchLibraries(): LibraryResult {
        val userPref = userPreferencesRepository.getUserPreferences().getOrNull()
        val result = librariesDataSource.fetchLibraries(userPref?.token ?: "")
        if (result.isSuccess)
            return LibraryResult.Success(result.getOrDefault(emptyList()))
        return LibraryResult.Error(result.exceptionOrNull()?.message)
    }

    override suspend fun getLibraryBooks(shelfId: Int): BooksResult {
        val userPref = userPreferencesRepository.getUserPreferences().getOrNull()
        val result = librariesDataSource.getLibraryBooks(userPref?.token ?: "", shelfId)
        if (result.isSuccess)
            return BooksResult.Success(result.getOrDefault(emptyList()))
        return BooksResult.Error(result.exceptionOrNull()?.message)
    }

    override suspend fun addBookToShelf(shelfId: Int, volumeId: String): ModifyLibraryResult {
        val userPref = userPreferencesRepository.getUserPreferences().getOrNull()
        val result = librariesDataSource.addBookToShelf(userPref?.token ?: "", shelfId, volumeId)
        if (result.isSuccess)
            return ModifyLibraryResult.Success(result.getOrDefault(false))
        return ModifyLibraryResult.Error(result.exceptionOrNull()?.message)
    }

    override suspend fun removeBookFromShelf(shelfId: Int, volumeId: String): ModifyLibraryResult {
        val userPref = userPreferencesRepository.getUserPreferences().getOrNull()
        val result = librariesDataSource.removeBookFromShelf(userPref?.token ?: "", shelfId, volumeId)
        if (result.isSuccess)
            return ModifyLibraryResult.Success(result.getOrDefault(false))
        return ModifyLibraryResult.Error(result.exceptionOrNull()?.message)
    }

    override suspend fun removeAllBooksFromShelf(shelfId: Int): ModifyLibraryResult {
        val userPref = userPreferencesRepository.getUserPreferences().getOrNull()
        val result = librariesDataSource.removeAllBooksFromShelf(userPref?.token ?: "", shelfId)
        if (result.isSuccess)
            return ModifyLibraryResult.Success(result.getOrDefault(false))
        return ModifyLibraryResult.Error(result.exceptionOrNull()?.message)
    }
}