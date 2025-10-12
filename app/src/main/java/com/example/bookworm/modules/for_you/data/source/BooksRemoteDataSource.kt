package com.example.bookworm.modules.for_you.data.source

import com.example.bookworm.BuildConfig
import com.example.bookworm.feature.books.data.remote.BooksDataSource
import com.example.bookworm.modules.for_you.data.remote.response.ForYouResponse
import com.example.bookworm.modules.for_you.data.remote.response.BookItem
import com.example.bookworm.modules.for_you.data.remote.service.BooksApiService
import com.example.bookworm.modules.for_you.domain.request.ForYouRequest
import javax.inject.Inject

private const val KEY = BuildConfig.API_KEY

class BooksRemoteDataSource @Inject constructor(
    private val booksApiService: BooksApiService
) {

    suspend fun fetchBooksForYou(request: ForYouRequest): Result<ForYouResponse> {
        return runCatching {
            when (request) {
                is ForYouRequest.BooksWithCategories -> {
                    val listResult = mutableListOf<BookItem>()
                    request.categories.forEach { category ->
                        val result = booksApiService.getBooks(
                            searchTerms = "$category+subject",
                            apiKey = KEY
                        )
                        listResult.addAll(result.items)
                    }
                    ForYouResponse(items = listResult.shuffled())
                }
            }
        }
    }
}