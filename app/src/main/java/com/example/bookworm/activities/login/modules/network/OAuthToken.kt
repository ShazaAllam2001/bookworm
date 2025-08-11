package com.example.bookworm.activities.login.modules.network

import com.google.gson.annotations.SerializedName


data class TokenResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("expires_in") val expiresIn: Long,
    @SerializedName("refresh_token") val refreshToken: String?,
    @SerializedName("scope") val scope: String?,
    @SerializedName("token_type") val tokenType: String
)

