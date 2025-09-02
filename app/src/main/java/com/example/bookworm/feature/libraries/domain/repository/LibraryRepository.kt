package com.example.bookworm.feature.libraries.domain.repository

interface LibraryRepository {
    suspend fun fetchLibraries()
    suspend fun getLibraryBooks(shelfId: Int)
    suspend fun addBookToShelf(shelfId: Int, volumeId: String)
    suspend fun removeBookFromShelf(shelfId: Int, volumeId: String)
    suspend fun removeAllBooksFromShelf(shelfId: Int)
}