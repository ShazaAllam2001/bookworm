package com.example.bookworm.feature.books.ui.composables.bookDetails

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.bookworm.R
import com.example.bookworm.ui.theme.dimens

@Composable
fun BookTopBar(
    navController: NavHostController
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = MaterialTheme.dimens.paddingSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_back_64dp),
                contentDescription = "Back to books list"
            )
        }
    }
}