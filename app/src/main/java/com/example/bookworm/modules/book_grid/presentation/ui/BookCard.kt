package com.example.bookworm.modules.book_grid.presentation.ui

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
import com.example.bookworm.common.ui.model.BookUiModel
import com.example.bookworm.ui.theme.dimens

@Composable
fun BookCard(
    modifier: Modifier = Modifier,
    book: BookUiModel
) {
    Column(
        modifier = modifier.padding(MaterialTheme.dimens.paddingSmall)
            .clickable { book.onClick() },
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
                text = book.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = if (book.authors.isNullOrEmpty()) "" else book.authors[0],
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}