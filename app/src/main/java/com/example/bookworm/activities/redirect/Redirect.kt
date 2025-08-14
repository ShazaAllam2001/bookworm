package com.example.bookworm.activities.redirect

import android.util.Log
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse
import com.example.bookworm.activities.login.modules.data.OAuthRepo


class OAuthRedirectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val oAuthRepo = OAuthRepo(this)
        val authResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val data = result.data
            if (result.resultCode == RESULT_OK && data != null) {
                val response = AuthorizationResponse.fromIntent(data)
                val ex = AuthorizationException.fromIntent(data)

                if (response != null) {
                    oAuthRepo.performTokenRequest(response)
                } else {
                    Log.e("AppAuth", "Authorization failed: $ex")
                }
            }
        }
        authResultLauncher.launch(intent)
    }
}
