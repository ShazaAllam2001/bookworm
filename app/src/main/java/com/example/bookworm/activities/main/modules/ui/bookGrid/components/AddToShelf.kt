package com.example.bookworm.activities.main.modules.ui.bookGrid.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bookworm.R
import com.example.bookworm.activities.main.modules.ui.loading.LoadingIndicator
import com.example.bookworm.activities.main.modules.viewModel.libraries.LibrariesMap
import com.example.bookworm.activities.main.modules.viewModel.libraries.LibrariesUiState
import com.example.bookworm.activities.main.modules.viewModel.libraries.LibraryModel
import com.example.bookworm.activities.main.modules.viewModel.libraries.LibraryType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToShelf(
    onDismiss: () -> Unit,
    onAdd: (Map<Int, Boolean>) -> Unit,
    libraryViewModel: LibraryModel
) {
    val preferences = remember { mutableStateMapOf<Int, Boolean>() }

    BasicAlertDialog(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        onDismissRequest = { onDismiss() }
    ) {
        Column {
            when (libraryViewModel.librariesUiState) {
                is LibrariesUiState.Loading ->
                    LoadingIndicator()
                is LibrariesUiState.Success -> {
                    val libraries = (libraryViewModel.librariesUiState as LibrariesUiState.Success).msg
                    LazyColumn {
                        items(libraries, key = { it.id }) { library ->
                            if (LibrariesMap[library.id]?.second == LibraryType.ADD_REMOVE) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Checkbox(
                                        modifier = Modifier.padding(2.dp),
                                        checked = preferences[library.id] == true,
                                        onCheckedChange = { preferences[library.id] = it }
                                    )
                                    Text(library.title)
                                }
                            }
                        }
                    }
                }
                is LibrariesUiState.Error ->
                    Text((libraryViewModel.librariesUiState as LibrariesUiState.Error).msg ?: "")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = {
                        onAdd(preferences)
                        onDismiss()
                    }
                ) {
                    Text(stringResource(R.string.add_to_shelf))
                }
                TextButton(
                    onClick = {
                        onDismiss()
                        preferences.clear()
                    }
                ) {
                    Text(stringResource(R.string.cancel))
                }
            }
        }
    }

}