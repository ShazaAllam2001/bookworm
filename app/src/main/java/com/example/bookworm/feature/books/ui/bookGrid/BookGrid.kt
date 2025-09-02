package com.example.bookworm.feature.books.ui.bookGrid

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bookworm.feature.books.domain.model.BookItem

@Composable
fun BookGrid(
    navController: NavHostController,
    bookList: List<BookItem>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp
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