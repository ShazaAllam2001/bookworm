package com.example.bookworm.modules.explore.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bookworm.common.utils.ObserveEvents
import com.example.bookworm.modules.explore.presentation.ui.components.ExploreContent
import com.example.bookworm.modules.explore.presentation.viewModel.ExploreViewModel
import com.example.bookworm.modules.explore.presentation.viewModel.events.ExploreViewModelEvents


@Composable
fun Explore(
    exploreViewModel: ExploreViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by exploreViewModel.state.collectAsStateWithLifecycle()

    ExploreContent(state = uiState)

    ObserveEvents(exploreViewModel.events) { event ->
        when (event) {
            is ExploreViewModelEvents.OpenBookDetails -> {
                navController.navigate("books/${event.id}")
            }
        }
    }
}
