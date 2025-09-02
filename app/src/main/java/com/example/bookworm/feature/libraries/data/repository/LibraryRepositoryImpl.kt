package com.example.bookworm.feature.libraries.data.repository

import com.example.bookworm.common.network.BooksApiService
import com.example.bookworm.feature.libraries.domain.repository.LibraryRepository
import javax.inject.Inject

class LibraryRepositoryImpl @Inject constructor(
    private val booksApi: BooksApiService
) : LibraryRepository {
    override suspend fun fetchLibraries() {
        TODO("Not yet implemented")
    }

    override suspend fun getLibraryBooks(shelfId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun addBookToShelf(shelfId: Int, volumeId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun removeBookFromShelf(shelfId: Int, volumeId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun removeAllBooksFromShelf(shelfId: Int) {
        TODO("Not yet implemented")
    }
}