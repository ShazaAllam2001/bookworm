package com.example.bookworm.feature.libraries.ui.composables.libraryBooks

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.bookworm.R
import com.example.bookworm.feature.libraries.data.model.Shelf
import com.example.bookworm.ui.theme.dimens

@Composable
fun BookListTopBar(
    library: Shelf,
    navController: NavHostController
) {
    Surface(
        modifier = Modifier.padding(MaterialTheme.dimens.paddingMedium2),
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back_64),
                    contentDescription = "Back to libraries"
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = library.title,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}