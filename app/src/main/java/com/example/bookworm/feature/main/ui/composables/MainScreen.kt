package com.example.bookworm.feature.main.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.feature.main.ui.composables.navigation.BottomBar
import com.example.bookworm.feature.main.ui.composables.navigation.BottomNavGraph

@Composable
fun MainScreen(
    onNavigateToLogin: () -> Unit
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            BottomNavGraph(
                navController = navController,
                onNavigateToLogin = onNavigateToLogin
            )
        }
    }
}
