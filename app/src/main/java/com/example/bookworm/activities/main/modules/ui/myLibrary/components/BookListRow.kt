package com.example.bookworm.activities.main.modules.ui.myLibrary.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bookworm.R
import com.example.bookworm.activities.main.modules.viewModel.books.BookItem
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

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
                    .padding(horizontal = 25.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_delete_64),
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
        Spacer(modifier = Modifier.height(15.dp))
    }
}


@Composable
fun BookListRow(
    book: BookItem,
    navController: NavHostController
) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .fillMaxHeight(0.2f),
        onClick = {
            navController.navigate("books/${book.id}")
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    if (book.volumeInfo.categories.isNullOrEmpty()) "" else book.volumeInfo.categories[0],
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    book.volumeInfo.title,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    stringResource(
                        R.string.by,
                        if (book.volumeInfo.authors.isNullOrEmpty()) "" else book.volumeInfo.authors[0]
                    ),
                    style = MaterialTheme.typography.labelMedium
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
}