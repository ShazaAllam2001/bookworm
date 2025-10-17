package com.example.bookworm.modules.explore.data.source

import com.example.bookworm.BuildConfig
import com.example.bookworm.common.api.response.BookItem
import com.example.bookworm.common.api.response.BooksResponse
import com.example.bookworm.modules.explore.data.remote.service.ExploreBooksApiService
import com.example.bookworm.modules.explore.domain.request.ExploreRequest
import javax.inject.Inject

private const val KEY = BuildConfig.API_KEY

class ExploreBooksRemoteDataSource @Inject constructor(
    private val booksApiService: ExploreBooksApiService
) {

    suspend fun fetchBooksExplore(request: ExploreRequest): Result<BooksResponse> {
        return runCatching {
            when (request) {
                is ExploreRequest.BooksWithCategories -> {
                    val listResult = mutableListOf<BookItem>()
                    request.categories.forEach { category ->
                        val result = booksApiService.getBooks(
                            searchTerms = "$category+subject",
                            apiKey = KEY
                        )
                        listResult.addAll(result.items)
                    }
                    BooksResponse(items = listResult.shuffled())
                }
                is ExploreRequest.BooksWithSearchTerms -> {
                    BooksResponse(items = emptyList())
                }
            }
        }
    }

    suspend fun searchBooks(request: ExploreRequest): Result<BooksResponse> {
        return runCatching {
            when (request) {
                is ExploreRequest.BooksWithCategories -> {
                    BooksResponse(items = emptyList())
                }
                is ExploreRequest.BooksWithSearchTerms -> {
                    val result = booksApiService.getBooks(
                        searchTerms = request.searchTerms,
                        apiKey = KEY
                    )
                    BooksResponse(items = result.items)
                }
            }
        }
    }
}