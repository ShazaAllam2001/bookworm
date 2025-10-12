package com.example.bookworm.modules.for_you.data.remote.service

import com.example.bookworm.common.constants.ApiPaths
import com.example.bookworm.common.constants.ApiParams
import com.example.bookworm.modules.for_you.data.remote.response.ForYouResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {
    @GET(ApiPaths.VOLUMES)
    suspend fun getBooks(
        @Query(ApiParams.QUERY) searchTerms: String,
        @Query(ApiParams.API_KEY) apiKey: String
    ): ForYouResponse
}
