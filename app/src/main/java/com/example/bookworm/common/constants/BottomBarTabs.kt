package com.example.bookworm.common.constants

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.bookworm.R

enum class BottomBarTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String
) {
    ForYou(
        title = R.string.for_you,
        icon = R.drawable.baseline_home_24,
        route = "forYou"
    ),
    Explore(
        title = R.string.explore,
        icon = R.drawable.baseline_search_24,
        route = "explore"
    ),
    MyLibrary(
        title = R.string.my_library,
        icon = R.drawable.baseline_bookmark_24,
        route = "myLibrary"
    ),
    Settings(
        title = R.string.settings,
        icon = R.drawable.baseline_settings_24,
        route = "settings"
    )
}
