package com.example.bookworm.common.constants

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.bookworm.R

enum class BottomBarTabs(
    @StringRes val title: Int,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val route: String
) {
    ForYou(
        title = R.string.for_you,
        selectedIcon = R.drawable.home_24,
        unselectedIcon = R.drawable.home_24_outlined,
        route = "forYou"
    ),
    Explore(
        title = R.string.explore,
        selectedIcon = R.drawable.search_check_24,
        unselectedIcon = R.drawable.search_24,
        route = "explore"
    ),
    MyLibrary(
        title = R.string.my_library,
        selectedIcon = R.drawable.bookmark_24,
        unselectedIcon = R.drawable.bookmark_24_outlined,
        route = "myLibrary"
    ),
    Settings(
        title = R.string.settings,
        selectedIcon = R.drawable.settings_24,
        unselectedIcon = R.drawable.settings_24_outlineed,
        route = "settings"
    )
}
