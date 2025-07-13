package com.example.bookworm

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    data object ForYou : BottomBarScreen(
        title = "For You",
        icon = Icons.Filled.Home,
        route = "forYou"
    )
    data object Explore : BottomBarScreen(
        title = "Explore",
        icon = Icons.Filled.Search,
        route = "explore"
    )
    data object MyLibrary : BottomBarScreen(
        title = "My Library",
        icon = Icons.Filled.Favorite,
        route = "forYou"
    )
    data object Settings : BottomBarScreen(
        title = "Settings",
        icon = Icons.Filled.Settings,
        route = "settings"
    )
}