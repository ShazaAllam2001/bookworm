package com.example.bookworm.feature.libraries.ui.composables.myLibrary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.bookworm.R
import com.example.bookworm.ui.theme.dimens

@Composable
fun MyLibraryRow(
    modifier: Modifier = Modifier,
    icon: Int,
    name: String,
    numberOfBooks: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(MaterialTheme.dimens.iconSize)
                .clip(RoundedCornerShape(MaterialTheme.dimens.roundCorner))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(MaterialTheme.dimens.paddingMedium),
            painter = painterResource(icon),
            contentDescription = "$name icon"
        )
        Spacer(modifier = Modifier.width(MaterialTheme.dimens.paddingMedium))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = name,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = stringResource(R.string.books, numberOfBooks),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}