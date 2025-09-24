package com.example.bookworm.feature.main.ui.composables.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bookworm.common.constants.BottomBarTabs
import com.example.bookworm.ui.theme.dimens

@Composable
fun NavHostController.shouldShowBottomBar(): Boolean {
    val bottomBarRoutes = BottomBarTabs.entries.map { it.route }
    return currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes
}

@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    AnimatedVisibility(
        visible = navController.shouldShowBottomBar(),
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        Column {
            HorizontalDivider(
                thickness = MaterialTheme.dimens.thicknessExtraSmall,
                color = MaterialTheme.colorScheme.secondary
            )
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.background
            ) {
                BottomBarTabs.entries.forEach { screen ->
                    NavigationBarItem(
                        label = {
                            Text(
                                text = stringResource(screen.title),
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary,
                            )
                        },
                        icon = {
                            if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                                Icon(
                                    painter = painterResource(screen.selectedIcon),
                                    contentDescription = "Navigation Icon"
                                )
                            }
                            else {
                                Icon(
                                    painter = painterResource(screen.unselectedIcon),
                                    contentDescription = "Navigation Icon"
                                )
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.secondary,
                            indicatorColor = MaterialTheme.colorScheme.background
                        ),
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    }
}