package com.example.bookworm.feature.libraries.ui.composables.myLibrary

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.bookworm.R
import com.example.bookworm.feature.libraries.data.constants.LibrariesMap
import com.example.bookworm.feature.libraries.data.model.Shelf
import com.example.bookworm.ui.theme.dimens

@Composable
fun LibrariesList(
    navController: NavHostController,
    libraries: List<Shelf>
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
            .padding(MaterialTheme.dimens.paddingMedium),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.paddingMedium)
    ) {
        items(libraries, key = { it.id }) { library ->
            MyLibraryRow(
                modifier = Modifier.fillMaxWidth()
                    .clickable {
                        navController.navigate("librarys/${library.id}")
                    },
                icon = LibrariesMap[library.id]?.first ?: R.drawable.book_2_64,
                name = library.title,
                numberOfBooks = library.volumeCount,
            )
        }
    }
}