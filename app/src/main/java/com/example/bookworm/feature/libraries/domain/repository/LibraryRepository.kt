package com.example.bookworm.feature.libraries.domain.repository

import com.example.bookworm.feature.books.domain.model.BooksResult
import com.example.bookworm.feature.libraries.domain.model.LibraryResult
import com.example.bookworm.feature.libraries.domain.model.ModifyLibraryResult

interface LibraryRepository {
    suspend fun fetchLibraries(): LibraryResult
    suspend fun getLibraryBooks(shelfId: Int): BooksResult
    suspend fun addBookToShelf(shelfId: Int, volumeId: String): ModifyLibraryResult
    suspend fun removeBookFromShelf(shelfId: Int, volumeId: String): ModifyLibraryResult
    suspend fun removeAllBooksFromShelf(shelfId: Int): ModifyLibraryResult
}