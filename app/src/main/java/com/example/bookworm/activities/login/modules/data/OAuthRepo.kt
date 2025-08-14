package com.example.bookworm.activities.login.modules.data

import android.content.Context
import android.util.Log
import androidx.core.net.toUri
import com.example.bookworm.BuildConfig
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ResponseTypeValues


class OAuthRepo(private val context: Context) {
    private lateinit var authService: AuthorizationService
    private lateinit var authRequest: AuthorizationRequest

    private fun configAuth() {
        val serviceConfig = AuthorizationServiceConfiguration(
            "https://accounts.google.com/o/oauth2/v2/auth".toUri(),
            "https://oauth2.googleapis.com/token".toUri()
        )

        authRequest = AuthorizationRequest.Builder(
            serviceConfig,
            BuildConfig.FIREBASE_CLIENT_ID,
            ResponseTypeValues.CODE,
            BuildConfig.FIREBASE_CLIENT_URI_REDIRECT.toUri()
        )
            .setScopes("email", "profile", "openid", "https://www.googleapis.com/auth/books")
            .build()

        authService = AuthorizationService(context)
    }

    fun performTokenRequest(response: AuthorizationResponse) {
        authService.performTokenRequest(
            response.createTokenExchangeRequest()
        ) { tokenResponse, ex ->
            if (tokenResponse != null) {
                val accessToken = tokenResponse.accessToken
                Log.d("AppAuth", "Access Token: $accessToken")
                // Use token with Google Books API
            } else {
                Log.e("AppAuth", "Token exchange failed: $ex")
            }
        }
    }

    fun launchAuthBrowser() {
        configAuth()
        val authIntent = authService.getAuthorizationRequestIntent(authRequest)
        context.startActivity(authIntent)
    }
}