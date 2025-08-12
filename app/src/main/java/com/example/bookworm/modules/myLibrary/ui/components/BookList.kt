package com.example.bookworm.modules.myLibrary.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.R
import com.example.bookworm.modules.bookGrid.data.BookInfo
import com.example.bookworm.modules.bookGrid.data.bookList
import com.example.bookworm.modules.bookGrid.data.bookListAR
import com.example.bookworm.modules.myLibrary.data.librarysList
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun BookList(
    navController: NavHostController = rememberNavController(),
    libraryId: Int
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BookListTopBar(
            navController = navController,
            libraryId = libraryId
        )
        Books(
            navController = navController,
            libraryId = libraryId
        )
    }
}

@Composable
fun BookListTopBar(
    navController: NavHostController,
    libraryId: Int
) {
    Surface(
        modifier = Modifier.padding(15.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back_64dp),
                    contentDescription = "Back to libraries"
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(librarysList[libraryId].name),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun Books(
    navController: NavHostController,
    libraryId: Int
) {
    var bookList = bookList
    if (LocalConfiguration.current.locales[0].language == "ar")
        bookList = bookListAR

    Text(
        stringResource(R.string.books, librarysList[libraryId].numberOfBooks),
        style = MaterialTheme.typography.titleMedium
    )
    LazyColumn(
        modifier = Modifier.padding(15.dp),
    ) {
        items(librarysList[libraryId].numberOfBooks) { index ->
            BookRowCard(
                navController = navController,
                book = bookList[index]
            )
        }
    }
}

@Composable
fun BookRowCard(
    navController: NavHostController,
    book: BookInfo
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
                    book.genre,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    book.title,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    book.author,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            CoilImage(
                modifier = Modifier.fillMaxWidth()
                    .weight(1f),
                imageModel = { book.image.toInt() }, // loading a network image or local resource using an URL.
                previewPlaceholder = book.image.toInt(),
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                )
            )
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
}