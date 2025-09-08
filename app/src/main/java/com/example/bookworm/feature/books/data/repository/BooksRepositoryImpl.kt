package com.example.bookworm.feature.books.data.repository

import com.example.bookworm.feature.books.domain.model.BookIdResult
import com.example.bookworm.feature.books.data.remote.BooksDataSource
import com.example.bookworm.feature.books.domain.model.BooksResult
import com.example.bookworm.feature.books.domain.repository.BooksRepository
import com.example.bookworm.feature.libraries.domain.model.LibraryResult
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val booksRemoteDataSource: BooksDataSource
) : BooksRepository {

    override suspend fun fetchBooksForYou(categories: List<String>): BooksResult {
        val result = booksRemoteDataSource.fetchBooksForYou(categories)
        if (result.isSuccess)
            return BooksResult.Success(result.getOrDefault(emptyList()))
        return BooksResult.Error(result.exceptionOrNull()?.message)
    }

    override suspend fun searchBooks(searchTerms: String): BooksResult {
        val result = booksRemoteDataSource.searchBooks(searchTerms)
        if (result.isSuccess)
            return BooksResult.Success(result.getOrDefault(emptyList()))
        return BooksResult.Error(result.exceptionOrNull()?.message)
    }

    override suspend fun searchBookById(bookId: String): BookIdResult {
        val result = booksRemoteDataSource.searchBookById(bookId)
        if (result.isSuccess)
            return BookIdResult.Success(result.getOrNull())
        return BookIdResult.Error(result.exceptionOrNull()?.message)
    }
}