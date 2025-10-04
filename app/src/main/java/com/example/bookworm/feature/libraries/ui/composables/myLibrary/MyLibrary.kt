package com.example.bookworm.feature.libraries.ui.composables.myLibrary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.R
import com.example.bookworm.common.ui.composables.loading.LoadingIndicator
import com.example.bookworm.feature.libraries.ui.viewModel.LibraryViewModel
import com.example.bookworm.ui.theme.dimens


@Composable
fun MyLibrary(
    libraryViewModel: LibraryViewModel = hiltViewModel(),
    updateLibrary: Boolean,
    onChangeUpdateLibrary: (Boolean) -> Unit,
    navController: NavHostController = rememberNavController()
) {
    val uiState by libraryViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (updateLibrary) {
            libraryViewModel.fetchLibraries()
            onChangeUpdateLibrary(false)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth()
                .padding(MaterialTheme.dimens.paddingMedium),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = stringResource(R.string.my_library),
            style = MaterialTheme.typography.titleLarge
        )
        if (uiState.isLoading) {
            LoadingIndicator()
        }
        else if (uiState.libraries != null) {
            LibrariesList(
                navController = navController,
                libraries = uiState.libraries!!
            )
        }
        else {
            Text(uiState.errorMessage ?: "")
        }
    }
}
