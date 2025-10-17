package com.example.bookworm.modules.for_you.data.source

import com.example.bookworm.BuildConfig
import com.example.bookworm.common.api.response.BookItem
import com.example.bookworm.common.api.response.BooksResponse
import com.example.bookworm.modules.explore.data.remote.service.ForYouBooksApiService
import com.example.bookworm.modules.for_you.domain.request.ForYouRequest
import javax.inject.Inject

private const val KEY = BuildConfig.API_KEY

class ForYouBooksRemoteDataSource @Inject constructor(
    private val booksApiService: ForYouBooksApiService
) {

    suspend fun fetchBooksForYou(request: ForYouRequest): Result<BooksResponse> {
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
                    BooksResponse(items = listResult.shuffled())
                }
            }
        }
    }
}