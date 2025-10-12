package com.example.bookworm.feature.auth.ui.composables.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

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