package com.example.bookworm.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bookworm.data.BookInfo

@Composable
fun Book(
    modifier: Modifier = Modifier,
    bookInfo: BookInfo
) {
    Card(
        modifier = modifier.padding(5.dp)
    ) {
        Column {
            Image(
                modifier = Modifier.fillMaxWidth()
                    .weight(1f),
                painter = painterResource(bookInfo.image),
                contentDescription = bookInfo.title
            )
            Text(bookInfo.title)
            Text("by $bookInfo.author")
        }
    }
}