package com.example.bookworm.modules.explore.data.remote.service

import com.example.bookworm.common.constants.ApiPaths
import com.example.bookworm.common.constants.ApiParams
import com.example.bookworm.common.api.response.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ForYouBooksApiService {
    @GET(ApiPaths.VOLUMES)
    suspend fun getBooks(
        @Query(ApiParams.QUERY) searchTerms: String,
        @Query(ApiParams.API_KEY) apiKey: String
    ): BooksResponse
}
