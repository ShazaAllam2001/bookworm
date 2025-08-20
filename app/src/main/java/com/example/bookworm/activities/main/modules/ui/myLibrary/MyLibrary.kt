package com.example.bookworm.activities.main.modules.ui.myLibrary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.R
import com.example.bookworm.activities.main.modules.ui.myLibrary.components.MyLibraryRow
import com.example.bookworm.activities.main.modules.ui.loading.LoadingIndicator
import com.example.bookworm.activities.main.modules.viewModel.libraries.LibrariesUiState
import com.example.bookworm.activities.main.modules.viewModel.libraries.LibraryModel
import com.example.bookworm.activities.main.modules.viewModel.libraries.Shelf
import com.example.bookworm.activities.main.modules.viewModel.libraries.LibrariesMap


@Composable
fun MyLibrary(
    viewModel: LibraryModel,
    navController: NavHostController = rememberNavController()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth()
                .padding(15.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = stringResource(R.string.my_library),
            style = MaterialTheme.typography.titleLarge
        )
        when (viewModel.librariesUiState) {
            is LibrariesUiState.Loading ->
                LoadingIndicator()
            is LibrariesUiState.Success ->
                LibrariesList(
                    navController = navController,
                    libraries = (viewModel.librariesUiState as LibrariesUiState.Success).msg
                )
            is LibrariesUiState.Error ->
                Text((viewModel.librariesUiState as LibrariesUiState.Error).msg ?: "")
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
            .padding(10.dp)
    ) {
        items(libraries, key = { it.id }) { library ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate("librarys/${library.id}")
                }
            ) {
                MyLibraryRow(
                    icon = LibrariesMap[library.id] ?: R.drawable.book_2_64dp,
                    name = library.title,
                    numberOfBooks = library.volumeCount,
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
