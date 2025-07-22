package com.example.bookworm.modules.myLibrary.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.R
import com.example.bookworm.modules.bookGrid.data.bookList
import com.example.bookworm.modules.myLibrary.data.librarysList

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
    Text(
        "${librarysList[libraryId].numberOfBooks} books",
        style = MaterialTheme.typography.titleMedium
    )
    LazyColumn(
        modifier = Modifier.padding(15.dp),
    ) {
        items(librarysList[libraryId].numberOfBooks) { index ->
            BookRowCard(
                navController = navController,
                bookId = index
            )
        }
    }
}

@Composable
fun BookRowCard(
    navController: NavHostController,
    bookId: Int
) {
    Card(
        onClick = {
            navController.navigate("books/$bookId")
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
                    bookList[bookId].genre,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    bookList[bookId].title,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    bookList[bookId].author,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Image(
                modifier = Modifier.size(150.dp),
                painter = painterResource(bookList[bookId].image),
                contentDescription = bookList[bookId].title
            )
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
}