package com.example.bookworm.feature.books.data.remote

import com.example.bookworm.BuildConfig
import com.example.bookworm.feature.books.data.model.BookItem
import javax.inject.Inject

private const val KEY = BuildConfig.API_KEY

class BooksDataSource @Inject constructor(
    private val booksApi: BooksApiService
) {
    suspend fun fetchBooksForYou(categories: List<String>): Result<List<BookItem>> {
        return runCatching {
            val listResult = mutableListOf<BookItem>()
            categories.forEach { category ->
                val result = booksApi.getBooks(
                    searchTerms = "$category+subject",
                    apiKey = KEY
                )
                listResult.addAll(result.items)
            }
            listResult.shuffled()
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
