package com.example.bookworm.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.bookworm.data.bookList
import com.example.bookworm.data.librarysList

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
        Books(libraryId = libraryId)
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
    libraryId: Int
) {
    Text(
        "${librarysList[libraryId].numberOfBooks} books",
        style = MaterialTheme.typography.titleMedium
    )
    Spacer(modifier = Modifier.height(10.dp))
    LazyColumn {
        items(librarysList[libraryId].numberOfBooks) { index ->
            Row(
                modifier = Modifier.fillMaxWidth()
                  .fillMaxHeight(0.25f),
                horizontalArrangement = Arrangement.Center
            ) {
                Column {
                    Text(
                        bookList[index].genre,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        bookList[index].title,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        bookList[index].author,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                Image(
                    modifier = Modifier.size(150.dp),
                    painter = painterResource(bookList[index].image),
                    contentDescription = bookList[index].title
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}