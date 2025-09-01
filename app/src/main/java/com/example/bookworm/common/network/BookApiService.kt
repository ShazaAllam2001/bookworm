package com.example.bookworm.common.network

import com.example.bookworm.feature.books.domain.model.BooksResponse
import com.example.bookworm.feature.books.domain.model.BookItem
import com.example.bookworm.activities.main.modules.viewModel.libraries.ShelvesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://www.googleapis.com/books/v1/"

interface BooksApiService {
    @GET("volumes")
    suspend fun getBooks(
        @Query("q") searchTerms: String,
        @Query("maxResults") maxResults: Int = 10,
        @Query("key") apiKey: String
    ): BooksResponse

    @GET("volumes/{id}")
    suspend fun getBookById(
        @Path("id") bookId: String,
        @Query("key") apiKey: String
    ): BookItem

    @GET("mylibrary/bookshelves")
    suspend fun getMyBookshelves(
        @Header("Authorization") token: String,
        @Query("key") apiKey: String
    ): ShelvesResponse

    @GET("mylibrary/bookshelves/{shelf}/volumes")
    suspend fun getShelfBooks(
        @Path("shelf") shelfId: Int,
        @Header("Authorization") token: String,
        @Query("key") apiKey: String
    ): Response<BooksResponse>

    @POST("mylibrary/bookshelves/{shelf}/addVolume")
    suspend fun addBookToShelf(
        @Path("shelf") shelfId: Int,
        @Query("volumeId") volumeId: String,
        @Header("Authorization") token: String,
        @Query("key") apiKey: String
    ): Response<Unit>

    @POST("mylibrary/bookshelves/{shelf}/removeVolume")
    suspend fun removeBookFromShelf(
        @Path("shelf") shelfId: Int,
        @Query("volumeId") volumeId: String,
        @Header("Authorization") token: String,
        @Query("key") apiKey: String
    ): Response<Unit>

    @POST("mylibrary/bookshelves/{shelf}/clearVolumes")
    suspend fun removeAllBooksFromShelf(
        @Path("shelf") shelfId: Int,
        @Header("Authorization") token: String,
        @Query("key") apiKey: String
    ): Response<Unit>
}
