package com.example.bookworm.feature.auth.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.feature.auth.ui.loggedin.LoggedInViewModel
import com.example.bookworm.feature.auth.ui.navigation.AuthNavigation
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

@Composable
fun Login(onNavigateToBrowser: () -> Unit) {
    val navController = rememberNavController()

    Scaffold { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            AuthNavigation(
                navController = navController,
                onNavigateToBrowser = onNavigateToBrowser
            )
        }
    }
}