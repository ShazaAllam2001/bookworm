package com.example.bookworm.feature.books.ui.bookGrid.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bookworm.R
import com.example.bookworm.feature.books.domain.model.BookItem
import com.example.bookworm.common.ui.loading.LoadingIndicator
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun BookCard(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    book: BookItem
) {
    Card(
        modifier = modifier.padding(5.dp),
        onClick = {
            navController.navigate("books/${book.id}")
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CoilImage(
                modifier = Modifier.fillMaxWidth()
                    .weight(1f),
                imageModel = { book.volumeInfo.imageLinks?.smallThumbnail },
                loading = { LoadingIndicator() },
                failure = { Text("Failed to load image") },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                )
            )
            Column(
                modifier = Modifier.padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    book.volumeInfo.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    stringResource(R.string.by,
                        if (book.volumeInfo.authors.isNullOrEmpty()) "" else book.volumeInfo.authors[0]
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}