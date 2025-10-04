package com.example.bookworm.modules.for_you.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.common.utils.ObserveEvents
import com.example.bookworm.modules.for_you.presentation.ui.components.ForYouContent
import com.example.bookworm.modules.for_you.presentation.viewModel.ForYouViewModel
import com.example.bookworm.modules.for_you.presentation.viewModel.events.ForYouViewModelEvents


@Composable
fun ForYou(
    forYouViewModel: ForYouViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by forYouViewModel.state.collectAsStateWithLifecycle()

    ForYouContent(state = uiState)

    ObserveEvents(forYouViewModel.events) { event ->
        when (event) {
            is ForYouViewModelEvents.OpenBookDetails -> {
                navController.navigate("books/${event.id}")
            }
        }
    }
}
