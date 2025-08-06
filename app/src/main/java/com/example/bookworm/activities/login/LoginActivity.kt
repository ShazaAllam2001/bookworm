package com.example.bookworm.activities.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.activities.login.modules.ui.LoginNavGraph
import com.example.bookworm.ui.theme.BookWormTheme


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookWormTheme {
                LoginScreen()
            }
        }
    }
}

@Composable
fun LoginScreen() {
    val navController = rememberNavController()

    Scaffold { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            LoginNavGraph(navController = navController)
        }
    }
}