package com.example.bookworm.activities.main.modules.myLibrary.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.R
import com.example.bookworm.activities.main.modules.myLibrary.ui.components.MyLibraryRow
import com.example.bookworm.activities.main.modules.network.LoadingIndicator
import com.example.bookworm.activities.main.modules.viewModel.libraries.LibrariesUiState
import com.example.bookworm.activities.main.modules.viewModel.libraries.LibraryModel
import com.example.bookworm.activities.main.modules.viewModel.libraries.Shelf
import com.example.bookworm.activities.main.modules.viewModel.libraries.LibrariesMap


@Composable
fun MyLibrary(
    viewModel: LibraryModel = LibraryModel(),
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
        LibrariesList(
            viewModel = viewModel,
            navController = navController
        )
    }
}

@Composable
fun LibrariesList(
    viewModel: LibraryModel = LibraryModel(),
    navController: NavHostController
) {
    when (viewModel.librariesUiState) {
        is LibrariesUiState.Loading ->
            LoadingIndicator()
        is LibrariesUiState.Success ->
            LibrariesList(
                navController = navController,
                list = (viewModel.librariesUiState as LibrariesUiState.Success).msg
            )
        is LibrariesUiState.Error ->
            Text((viewModel.librariesUiState as LibrariesUiState.Error).msg ?: "")
    }
}

@Composable
fun LibrariesList(
    navController: NavHostController,
    list: List<Shelf>
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
            .padding(10.dp)
    ) {
        items(list.size) { index ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate("librarys/${index}")
                }
            ) {
                LibrariesMap[list[index].id]?.let {
                    MyLibraryRow(
                        icon = it,
                        name = list[index].title,
                        numberOfBooks = list[index].volumeCount,
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyLibraryPreview() {
    MyLibrary()
}
