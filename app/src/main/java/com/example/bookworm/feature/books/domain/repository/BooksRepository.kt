package com.example.bookworm.feature.books.domain.repository

import com.example.bookworm.feature.books.domain.model.BookIdResult
import com.example.bookworm.feature.books.domain.model.BooksResult

interface BooksRepository {
    suspend fun fetchBooksForYou(categories: List<String>): BooksResult
    suspend fun searchBooks(searchTerms: String): BooksResult
    suspend fun searchBookById(bookId: String): BookIdResult
}