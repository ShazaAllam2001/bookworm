package com.example.bookworm.feature.books.data.repository

import com.example.bookworm.feature.books.domain.model.BookIdResult
import com.example.bookworm.feature.books.data.remote.BooksDataSource
import com.example.bookworm.feature.books.domain.model.BooksResult
import com.example.bookworm.feature.books.domain.repository.BooksRepository
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val booksRemoteDataSource: BooksDataSource
) : BooksRepository {

    override suspend fun fetchBooksForYou(categories: List<String>): BooksResult {
        return booksRemoteDataSource.fetchBooksForYou(categories)
    }

    override suspend fun searchBooks(searchTerms: String): BooksResult {
        return booksRemoteDataSource.searchBooks(searchTerms)
    }

    override suspend fun searchBookById(bookId: String): BookIdResult {
        return booksRemoteDataSource.searchBookById(bookId)
    }
}