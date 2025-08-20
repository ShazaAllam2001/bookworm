package com.example.bookworm.activities.main.modules.ui.myLibrary.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bookworm.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClearLibraryConfirm(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    BasicAlertDialog(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        onDismissRequest = { onDismiss() }
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(stringResource(R.string.are_you_sure_you_want_to_clear_the_library_from_books))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = {
                        onConfirm()
                        onDismiss()
                    }
                ) {
                    Text(stringResource(R.string.confirm))
                }
                TextButton(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text(stringResource(R.string.cancel))
                }
            }
        }
    }

}