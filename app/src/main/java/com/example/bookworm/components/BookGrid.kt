package com.example.bookworm.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bookworm.data.bookList

@Composable
fun BookGrid(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp
        ),
        content = {
            items(bookList.size) { index ->
                Book(
                    modifier = Modifier.aspectRatio(0.65f),
                    navController = navController,
                    bookId = index
                )
            }
        }
    )
}