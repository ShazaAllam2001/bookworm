package com.example.bookworm.feature.libraries.data.remote

import com.example.bookworm.common.constants.ApiPaths
import com.example.bookworm.common.constants.ApiParams
import com.example.bookworm.feature.books.data.model.BooksResponse
import com.example.bookworm.feature.libraries.data.model.ShelvesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface LibrariesApiService {
    @GET(ApiPaths.BOOKSHELVES)
    suspend fun getMyBookshelves(
        @Header(ApiParams.AUTH) token: String,
        @Query(ApiParams.API_KEY) apiKey: String
    ): ShelvesResponse

    @GET(ApiPaths.SHELF_BOOKS)
    suspend fun getShelfBooks(
        @Path(ApiParams.SHELF) shelfId: Int,
        @Header(ApiParams.AUTH) token: String,
        @Query(ApiParams.API_KEY) apiKey: String
    ): Response<BooksResponse>

    @POST(ApiPaths.ADD_VOLUME)
    suspend fun addBookToShelf(
        @Path(ApiParams.SHELF) shelfId: Int,
        @Query(ApiParams.VOLUME_ID) volumeId: String,
        @Header(ApiParams.AUTH) token: String,
        @Query(ApiParams.API_KEY) apiKey: String
    ): Response<Unit>

    @POST(ApiPaths.REMOVE_VOLUME)
    suspend fun removeBookFromShelf(
        @Path(ApiParams.SHELF) shelfId: Int,
        @Query(ApiParams.VOLUME_ID) volumeId: String,
        @Header(ApiParams.AUTH) token: String,
        @Query(ApiParams.API_KEY) apiKey: String
    ): Response<Unit>

    @POST(ApiPaths.CLEAR_VOLUMES)
    suspend fun removeAllBooksFromShelf(
        @Path(ApiParams.SHELF) shelfId: Int,
        @Header(ApiParams.AUTH) token: String,
        @Query(ApiParams.API_KEY) apiKey: String
    ): Response<Unit>
}
