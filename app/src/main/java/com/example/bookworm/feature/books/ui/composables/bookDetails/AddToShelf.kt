package com.example.bookworm.feature.books.ui.composables.bookDetails

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bookworm.R
import com.example.bookworm.common.ui.composables.loading.LoadingIndicator
import com.example.bookworm.feature.libraries.data.constants.LibrariesMap
import com.example.bookworm.feature.libraries.data.constants.LibraryType
import com.example.bookworm.feature.libraries.ui.viewModel.LibraryViewModel
import com.example.bookworm.ui.theme.dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToShelf(
    onDismiss: () -> Unit,
    onAdd: (Map<Int, Boolean>) -> Unit,
    libraryViewModel: LibraryViewModel
) {
    val modifiedLibraries = remember { mutableStateMapOf<Int, Boolean>() }
    val uiState by libraryViewModel.uiState.collectAsStateWithLifecycle()

    BasicAlertDialog(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        onDismissRequest = { onDismiss() }
    ) {
        Column {
            if (uiState.isLoading) {
                LoadingIndicator()
            }
            else {
                if (uiState.libraries != null) {
                    val libraries = uiState.libraries!!
                    LazyColumn {
                        items(libraries, key = { it.id }) { library ->
                            if (LibrariesMap[library.id]?.second == LibraryType.ADD_REMOVE) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Checkbox(
                                        modifier = Modifier.padding(MaterialTheme.dimens.paddingExtraSmall),
                                        checked = modifiedLibraries[library.id] == true,
                                        onCheckedChange = { modifiedLibraries[library.id] = it }
                                    )
                                    Text(library.title)
                                }
                            }
                        }
                    }
                }
                else {
                    Text(uiState.errorMessage ?: "")
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = {
                        onAdd(modifiedLibraries)
                        onDismiss()
                    }
                ) {
                    Text(stringResource(R.string.add_to_shelf))
                }
                TextButton(
                    onClick = {
                        onDismiss()
                        modifiedLibraries.clear()
                    }
                ) {
                    Text(stringResource(R.string.cancel))
                }
            }
        }
    }

}