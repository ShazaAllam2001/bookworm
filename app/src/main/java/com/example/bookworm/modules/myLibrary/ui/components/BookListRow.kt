package com.example.bookworm.modules.myLibrary.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bookworm.R
import com.example.bookworm.modules.viewModel.BookItem
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun BookListRow(
    navController: NavHostController,
    book: BookItem
) {
    Card(
        onClick = {
            navController.navigate("books/${book.id}")
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(0.25f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    if (book.volumeInfo.categories.isNullOrEmpty()) "" else book.volumeInfo.categories[0],
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    book.volumeInfo.title,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    stringResource(
                        R.string.by,
                        if (book.volumeInfo.authors.isNullOrEmpty()) "" else book.volumeInfo.authors[0]
                    ),
                    style = MaterialTheme.typography.titleSmall
                )
            }
            CoilImage(
                modifier = Modifier.fillMaxWidth()
                    .weight(1f),
                imageModel = { book.volumeInfo.imageLinks?.smallThumbnail },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                )
            )
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
}