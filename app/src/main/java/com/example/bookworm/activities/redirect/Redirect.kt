package com.example.bookworm.activities.redirect

import android.util.Log
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity

class OAuthRedirectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uri: Uri? = intent?.data
        if (uri != null) {
            val code = uri.getQueryParameter("code")
            val error = uri.getQueryParameter("error")

            if (error != null) {
                // Handle error
                Log.d("Auth Error", error)
            } else if (code != null) {
                // Proceed with token exchange
                Log.d("Auth Code", code)
            }
        }

        finish() // close the activity
    }
}
