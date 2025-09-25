package com.example.bookworm.feature.books.data.remote

import com.example.bookworm.common.constants.ApiPaths
import com.example.bookworm.common.constants.ApiParams
import com.example.bookworm.feature.books.data.model.BooksResponse
import com.example.bookworm.feature.books.data.model.BookItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApiService {
    @GET(ApiPaths.VOLUMES)
    suspend fun getBooks(
        @Query(ApiParams.QUERY) searchTerms: String,
        @Query(ApiParams.API_KEY) apiKey: String
    ): BooksResponse

    @GET(ApiPaths.VOLUME_BY_ID)
    suspend fun getBookById(
        @Path(ApiParams.BOOK_ID) bookId: String,
        @Query(ApiParams.API_KEY) apiKey: String
    ): BookItem
}
