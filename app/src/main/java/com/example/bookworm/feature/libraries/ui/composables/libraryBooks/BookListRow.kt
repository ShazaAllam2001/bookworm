package com.example.bookworm.feature.libraries.ui.composables.libraryBooks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.bookworm.R
import com.example.bookworm.feature.books.data.model.BookItem
import com.example.bookworm.feature.books.ui.composables.bookCover.BookCover
import com.example.bookworm.ui.theme.dimens

@Composable
fun SwipeBookListRow(
    book: BookItem,
    onItemDismissed: (BookItem) -> Unit,
    navController: NavHostController
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.EndToStart) {
                onItemDismissed(book)
                true
            }
            else {
                false
            }
        }
    )
    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(
                        if (dismissState.targetValue == SwipeToDismissBoxValue.EndToStart) Color.Red
                        else Color.Transparent
                    )
                    .padding(horizontal = MaterialTheme.dimens.paddingLarge2)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.delete_64),
                        contentDescription = "Delete"
                    )
                }
            }
        }
    ) {
        BookListRow(
            navController = navController,
            book = book
        )
    }
}


@Composable
fun BookListRow(
    book: BookItem,
    navController: NavHostController
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .clickable {
                navController.navigate("books/${book.id}")
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(6f)
        ) {
            Text(
                modifier = Modifier.padding(vertical = MaterialTheme.dimens.paddingExtraSmall),
                text = if (book.volumeInfo.categories.isNullOrEmpty()) "" else book.volumeInfo.categories[0],
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                modifier = Modifier.padding(vertical = MaterialTheme.dimens.paddingExtraSmall),
                text = book.volumeInfo.title,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                modifier = Modifier.padding(vertical = MaterialTheme.dimens.paddingExtraSmall),
                text = stringResource(
                    R.string.by,
                    if (book.volumeInfo.authors.isNullOrEmpty()) "" else book.volumeInfo.authors[0]
                ),
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.labelMedium
            )
        }
        BookCover(
            modifier = Modifier.weight(4f)
                .fillMaxSize()
                .clip(RoundedCornerShape(MaterialTheme.dimens.roundCornerSmall)),
            bookRatio = 0.6f,
            book = book
        )
    }
}