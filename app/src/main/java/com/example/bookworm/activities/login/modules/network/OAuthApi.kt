package com.example.bookworm.activities.login.modules.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


private const val BASE_URL = "https://oauth2.googleapis.com/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface OAuthApiService {
    @FormUrlEncoded
    @POST("token")
    suspend fun exchangeCode(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String?, // null for public clients
        @Field("code") code: String,
        @Field("code_verifier") codeVerifier: String,
        @Field("redirect_uri") redirectUri: String,
        @Field("grant_type") grantType: String = "authorization_code"
    ): TokenResponse
}

object OAuthApi {
    val retrofitService: OAuthApiService by lazy {
        retrofit.create(OAuthApiService::class.java)
    }
}
