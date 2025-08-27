package com.example.bookworm.activities.login

import android.util.Log
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.activities.login.modules.ui.LoginNavGraph
import com.example.bookworm.activities.main.MainActivity
import com.example.bookworm.activities.login.modules.data.preferences.PrefRepo
import com.example.bookworm.ui.theme.BookWormTheme
import kotlinx.coroutines.launch


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = this
        lifecycleScope.launch {
            val prefRepo = PrefRepo(context)
            val user = prefRepo.readPreferences()
            val currentTimeSeconds = System.currentTimeMillis() / 1000
            Log.d("Auth", "Current Time: $currentTimeSeconds, Expiration Time: ${user.expirationTimeStamp}")
            if (user.token != "" && currentTimeSeconds < user.expirationTimeStamp) {
                startActivity(Intent(context, MainActivity::class.java))
            }
        }

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