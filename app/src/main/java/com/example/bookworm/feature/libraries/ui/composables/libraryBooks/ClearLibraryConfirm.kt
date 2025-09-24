package com.example.bookworm.feature.libraries.ui.composables.libraryBooks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.example.bookworm.R
import com.example.bookworm.ui.theme.dimens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClearLibraryConfirm(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    BasicAlertDialog(
        modifier = Modifier.clip(RoundedCornerShape(MaterialTheme.dimens.roundCorner))
            .background(MaterialTheme.colorScheme.surfaceContainer),
        onDismissRequest = { onDismiss() }
    ) {
        Column(
            modifier = Modifier.padding(MaterialTheme.dimens.paddingMedium)
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