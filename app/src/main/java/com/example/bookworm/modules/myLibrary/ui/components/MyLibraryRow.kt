package com.example.bookworm.modules.myLibrary.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bookworm.R

@Composable
fun MyLibraryRow(
    icon: Int,
    name: Int,
    numberOfBooks: Int
) {
    Row(
        modifier = Modifier.padding(10.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = "$name icon"
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                stringResource(name),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                stringResource(R.string.books, numberOfBooks),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}