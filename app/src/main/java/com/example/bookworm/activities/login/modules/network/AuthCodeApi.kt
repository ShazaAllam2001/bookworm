package com.example.bookworm.activities.login.modules.network

import androidx.core.net.toUri


fun buildAuthorizationUrl(
    clientId: String,
    redirectUri: String,
    scope: String,
    codeChallenge: String,
    state: String? = null
): String {

    val baseUrl = "https://accounts.google.com/o/oauth2/v2/auth"

    val uriBuilder = baseUrl.toUri().buildUpon()
        .appendQueryParameter("client_id", clientId)
        .appendQueryParameter("response_type", "code")
        .appendQueryParameter("redirect_uri", redirectUri)
        .appendQueryParameter("scope", scope)
        .appendQueryParameter("code_challenge", codeChallenge)
        .appendQueryParameter("code_challenge_method", "S256")
    state?.let {
        uriBuilder.appendQueryParameter("state", it)
    }

    return uriBuilder.build().toString()
}

