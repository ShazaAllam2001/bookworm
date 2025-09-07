package com.example.bookworm.feature.books.data.remote

import com.example.bookworm.BuildConfig
import com.example.bookworm.feature.books.data.model.BookItem
import com.example.bookworm.feature.books.domain.model.BookIdResult
import com.example.bookworm.feature.books.domain.model.BooksResult
import javax.inject.Inject

private const val KEY = BuildConfig.API_KEY

class BooksDataSource @Inject constructor(
    private val booksApi: BooksApiService
) {
    suspend fun fetchBooksForYou(categories: List<String>): BooksResult {
        return runCatching {
            val listResult = mutableSetOf<BookItem>()
            categories.forEach { category ->
                val result = booksApi.getBooks(
                    searchTerms = "$category+subject",
                    apiKey = KEY
                )
                listResult.addAll(result.items)
            }
            BooksResult.Success(listResult.toList().shuffled())
        }.getOrElse { e ->
            BooksResult.Error(e.message)
        }
    }

    suspend fun searchBooks(searchTerms: String): BooksResult {
        return runCatching {
            val listResult = booksApi.getBooks(
                searchTerms = searchTerms,
                apiKey = KEY
            )
            BooksResult.Success(listResult.items)
        }.getOrElse { e ->
            BooksResult.Error(e.message)
        }
    }

    suspend fun searchBookById(bookId: String): BookIdResult {
        return runCatching {
            val result = booksApi.getBookById(
                bookId = bookId,
                apiKey = KEY
            )
            BookIdResult.Success(result)
        }.getOrElse { e ->
            BookIdResult.Error(e.message)
        }
    }
}
