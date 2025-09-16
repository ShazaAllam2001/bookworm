package com.example.bookworm.feature.libraries.ui.composables.myLibrary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.R
import com.example.bookworm.common.ui.composables.loading.LoadingIndicator
import com.example.bookworm.feature.libraries.data.model.Shelf
import com.example.bookworm.feature.libraries.data.constants.LibrariesMap
import com.example.bookworm.feature.libraries.ui.viewModel.LibraryViewModel
import com.example.bookworm.ui.theme.dimens


@Composable
fun MyLibrary(
    viewModel: LibraryViewModel,
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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

@Composable
fun LibrariesList(
    navController: NavHostController,
    libraries: List<Shelf>
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
            .padding(MaterialTheme.dimens.paddingMedium),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.paddingMedium)
    ) {
        items(libraries, key = { it.id }) { library ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate("librarys/${library.id}")
                }
            ) {
                MyLibraryRow(
                    icon = LibrariesMap[library.id]?.first ?: R.drawable.book_2_64dp,
                    name = library.title,
                    numberOfBooks = library.volumeCount,
                )
            }
        }
    }
}
