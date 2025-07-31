package com.example.bookworm.modules.network

import com.example.bookworm.modules.viewModel.BooksResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.googleapis.com/books/v1/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface BooksApiService {
    @GET("volumes")
    suspend fun getBooks(
        @Query("q") searchTerms: String,
        @Query("maxResults") maxResults: Int = 10,
        @Query("langRestrict") lang: String,
        @Query("key") apiKey: String
    ): BooksResponse
}

object BooksApi {
    val retrofitService: BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }
}