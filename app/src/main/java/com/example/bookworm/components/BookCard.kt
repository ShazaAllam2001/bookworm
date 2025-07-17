package com.example.bookworm.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bookworm.data.bookList

@Composable
fun Book(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    bookId: Int
) {
    Card(
        modifier = modifier.padding(5.dp),
        onClick = {
            navController.navigate("books/$bookId")
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.fillMaxWidth()
                    .weight(1f),
                painter = painterResource(bookList[bookId].image),
                contentDescription = bookList[bookId].title
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                bookList[bookId].title,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                "by ${bookList[bookId].author}",
                style = MaterialTheme.typography.labelSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}