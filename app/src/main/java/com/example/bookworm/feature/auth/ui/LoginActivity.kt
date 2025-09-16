package com.example.bookworm.feature.auth.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bookworm.feature.user.ui.viewModel.LoggedInViewModel
import com.example.bookworm.feature.auth.ui.composables.navigation.Login
import com.example.bookworm.feature.main.ui.MainActivity
import com.example.bookworm.ui.theme.BookWormTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            BookWormTheme {
                val viewModel: LoggedInViewModel = hiltViewModel()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                LaunchedEffect(Unit) {
                    viewModel.checkPref()
                }

                if (uiState.isSignedIn) {
                    LaunchedEffect(Unit) {
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        Log.d("Account Logged In", uiState.userPref.toString())
                    }
                    finish()
                }
                else {
                    Login(
                        onNavigateToBrowser = {
                            startActivity(Intent(this@LoginActivity, OAuthRedirectActivity::class.java))
                        }
                    )
                }
            }
        }
    }
}
