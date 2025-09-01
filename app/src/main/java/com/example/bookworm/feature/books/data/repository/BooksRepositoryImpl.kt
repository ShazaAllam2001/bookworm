package com.example.bookworm.feature.books.data.repository

import com.example.bookworm.BuildConfig
import com.example.bookworm.common.network.BooksApiService
import com.example.bookworm.feature.books.domain.model.BookIdResult
import com.example.bookworm.feature.books.domain.model.BooksResult
import com.example.bookworm.feature.books.domain.repository.BooksRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val KEY = BuildConfig.API_KEY

@Singleton
class BooksRepositoryImpl @Inject constructor(
    private val booksApi: BooksApiService
) : BooksRepository {

    override suspend fun fetchBooksForYou(categories: List<String>): BooksResult {
        return try {
            var query = ""
            categories.forEachIndexed { index, category ->
                query += "$category+subject"
                if (categories.lastIndex != index)
                    query += "|"
            }
            val listResult = booksApi.getBooks(
                searchTerms = query,
                maxResults = 40,
                apiKey = KEY
            )
            BooksResult.Success(listResult.items)
        } catch (e: Exception) {
            BooksResult.Error(e.message ?: "Sign in failed")
        }
    }

    override suspend fun searchBooks(searchTerms: String): BooksResult {
        return try {
            val listResult = booksApi.getBooks(
                searchTerms = searchTerms,
                apiKey = KEY
            )
            BooksResult.Success(listResult.items)
        } catch (e: IOException) {
            BooksResult.Error(e.message)
        } catch (e: HttpException) {
            BooksResult.Error(e.message)
        }
    }

    override suspend fun searchBookById(bookId: String): BookIdResult {
        return  try {
            val result = booksApi.getBookById(
                bookId = bookId,
                apiKey = KEY
            )
            BookIdResult.Success(result)
        } catch (e: IOException) {
            BookIdResult.Error(e.message)
        } catch (e: HttpException) {
            BookIdResult.Error(e.message)
        }
    }
}