package com.example.bookworm.feature.libraries.data.repository

import com.example.bookworm.feature.books.domain.model.BooksResult
import com.example.bookworm.feature.libraries.data.remote.LibrariesDataSource
import com.example.bookworm.feature.libraries.domain.model.LibraryResult
import com.example.bookworm.feature.libraries.domain.model.ModifyLibraryResult
import com.example.bookworm.feature.libraries.domain.repository.LibraryRepository
import javax.inject.Inject

class LibraryRepositoryImpl @Inject constructor(
    private val librariesDataSource: LibrariesDataSource
) : LibraryRepository {

    override suspend fun fetchLibraries(): LibraryResult {
        return librariesDataSource.fetchLibraries()
    }

    override suspend fun getLibraryBooks(shelfId: Int): BooksResult {
        return librariesDataSource.getLibraryBooks(shelfId)
    }

    override suspend fun addBookToShelf(shelfId: Int, volumeId: String): ModifyLibraryResult {
        return librariesDataSource.addBookToShelf(shelfId, volumeId)
    }

    override suspend fun removeBookFromShelf(shelfId: Int, volumeId: String): ModifyLibraryResult {
        return librariesDataSource.removeBookFromShelf(shelfId, volumeId)
    }

    override suspend fun removeAllBooksFromShelf(shelfId: Int): ModifyLibraryResult {
        return librariesDataSource.removeAllBooksFromShelf(shelfId)
    }
}