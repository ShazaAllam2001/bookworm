package com.example.bookworm.feature.books.ui.composables.bookGrid

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import com.example.bookworm.feature.books.data.model.BookItem
import com.example.bookworm.feature.books.ui.composables.bookCover.BookCover
import com.example.bookworm.ui.theme.dimens

@Composable
fun BookCard(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    book: BookItem
) {
    Column(
        modifier = modifier.padding(MaterialTheme.dimens.paddingSmall)
            .clickable {
                navController.navigate("books/${book.id}")
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BookCover(
            modifier = Modifier.weight(1f)
                .fillMaxSize()
                .clip(RoundedCornerShape(MaterialTheme.dimens.roundCornerSmall)),
            book = book
        )
        Column(
                modifier = Modifier.padding(MaterialTheme.dimens.paddingSmall),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = book.volumeInfo.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = if (book.volumeInfo.authors.isNullOrEmpty()) "" else book.volumeInfo.authors[0],
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}