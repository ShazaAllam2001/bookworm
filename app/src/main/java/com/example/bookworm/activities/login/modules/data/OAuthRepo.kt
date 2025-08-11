package com.example.bookworm.activities.login.modules.data

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.Base64
import java.security.SecureRandom
import androidx.core.net.toUri
import com.example.bookworm.activities.login.modules.network.buildAuthorizationUrl
import com.example.bookworm.BuildConfig


class OAuthRepo(private val context: Context) {
    @RequiresApi(Build.VERSION_CODES.O)
    private fun generateCodeVerifier(): String {
        // random code_verifier with 43 characters
        val bytes = ByteArray(32)
        SecureRandom().nextBytes(bytes)
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun generateCodeChallenge(codeVerifier: String): String {
        // code_challenge = BASE64URL-ENCODE(SHA256(ASCII(code_verifier)))
        val bytes = codeVerifier.toByteArray(StandardCharsets.US_ASCII)
        val digest = MessageDigest.getInstance("SHA-256").digest(bytes)
        return Base64.getUrlEncoder().withoutPadding().encodeToString(digest)
    }

    private fun launchAuthBrowser(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        context.startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun codeAuth() {
        val codeVerifier = generateCodeVerifier()
        val codeChallenge = generateCodeChallenge(codeVerifier)

        val authUrl = buildAuthorizationUrl(
            clientId = BuildConfig.FIREBASE_CLIENT_ID,
            redirectUri = BuildConfig.FIREBASE_CLIENT_URI_REDIRECT,
            scope = "email profile openid https://www.googleapis.com/auth/books",
            codeChallenge = codeChallenge
        )

        launchAuthBrowser(context, authUrl)
    }


}