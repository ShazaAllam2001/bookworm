package com.example.bookworm.feature.auth.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookworm.activities.main.MainActivity
import com.example.bookworm.activities.main.modules.ui.loading.LoadingIndicator
import com.example.bookworm.feature.auth.ui.login.LoginViewModel
import com.example.bookworm.ui.theme.BookWormTheme
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class OAuthRedirectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookWormTheme {
                val viewModel: LoginViewModel = hiltViewModel()
                val uiState by viewModel.uiState.collectAsState()
                viewModel.signIn()
                if (uiState.isLoading) {
                    LoadingIndicator()
                } else {
                    if (uiState.isSignedIn) {
                        startActivity(Intent(this, MainActivity::class.java))
                        Log.d("test", uiState.user.toString())
                    }
                }
            }
        }
    }
}
