package com.example.bookworm.feature.books.ui.bookGrid

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.bookworm.feature.books.data.model.BookItem
import com.example.bookworm.ui.theme.dimens

@Composable
fun BookGrid(
    navController: NavHostController,
    bookList: List<BookItem>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            start = MaterialTheme.dimens.paddingMedium2,
            end = MaterialTheme.dimens.paddingMedium2
        ),
        content = {
            items(bookList, key = { book -> book.id }) { item ->
                BookCard(
                    modifier = Modifier.aspectRatio(0.65f),
                    navController = navController,
                    book = item
                )
            }
        }
    )
}