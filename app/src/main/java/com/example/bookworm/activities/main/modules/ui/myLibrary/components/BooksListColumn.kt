package com.example.bookworm.activities.main.modules.ui.myLibrary.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bookworm.R
import com.example.bookworm.activities.main.modules.viewModel.books.BookItem
import com.example.bookworm.activities.main.modules.viewModel.libraries.Shelf

@Composable
fun BooksListColumn(
    books: List<BookItem>,
    onItemDismissed: (BookItem) -> Unit,
    library: Shelf,
    onClearLibrary: (Shelf) -> Unit,
    navController: NavHostController
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.padding(15.dp)
                .weight(0.85f),
        ) {
            items(books, key = { it.id }) { book ->
                SwipeBookListRow(
                    book = book,
                    onItemDismissed = onItemDismissed,
                    navController = navController
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 25.dp),
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.primary
        )
        IconButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { showDialog = true }
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(R.drawable.baseline_delete_48),
                    contentDescription = "Delete All Rows"
                )
                Text(
                    text = stringResource(R.string.clear_all_volumes),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        if (showDialog) {
            ClearLibraryConfirm(
                onDismiss = { showDialog = false },
                onConfirm = { onClearLibrary(library) }
            )
        }
    }
}