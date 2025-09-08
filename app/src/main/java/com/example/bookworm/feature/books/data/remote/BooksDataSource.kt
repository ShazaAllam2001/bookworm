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
    suspend fun fetchBooksForYou(categories: List<String>): Result<List<BookItem>> {
        return runCatching {
            val listResult = mutableSetOf<BookItem>()
            categories.forEach { category ->
                val result = booksApi.getBooks(
                    searchTerms = "$category+subject",
                    apiKey = KEY
                )
                listResult.addAll(result.items)
            }
            listResult.toList().shuffled()
        }
    }

    suspend fun searchBooks(searchTerms: String): Result<List<BookItem>> {
        return runCatching {
            val listResult = booksApi.getBooks(
                searchTerms = searchTerms,
                apiKey = KEY
            )
            listResult.items
        }
    }

    suspend fun searchBookById(bookId: String): Result<BookItem> {
        return runCatching {
            val result = booksApi.getBookById(
                bookId = bookId,
                apiKey = KEY
            )
            result
        }
    }
}
