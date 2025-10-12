package com.example.bookworm.feature.settings.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.bookworm.R
import com.example.bookworm.feature.user.ui.viewModel.LoggedInViewModel
import com.example.bookworm.feature.notifications.ui.viewModel.NotifyViewModel
import com.example.bookworm.ui.theme.dimens


@Composable
fun Settings(
    notifyViewModel: NotifyViewModel,
    loggedInViewModel: LoggedInViewModel,
    onNavigateToLogin: () -> Unit,
    updateForYou: () -> Unit
) {
    val uiState by loggedInViewModel.uiState.collectAsState()
    if (!uiState.isSignedIn) {
        onNavigateToLogin()
    }
    else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Text(
                modifier = Modifier.weight(0.1f)
                    .fillMaxWidth()
                    .padding(MaterialTheme.dimens.paddingMedium2),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = stringResource(R.string.settings),
                style = MaterialTheme.typography.titleLarge
            )
            Profile(
                modifier = Modifier.weight(0.8f),
                notifyViewModel = notifyViewModel,
                loggedInViewModel = loggedInViewModel,
                updateForYou = updateForYou
            )
            Row(
                modifier = Modifier.weight(0.1f)
                    .fillMaxWidth()
                    .padding(MaterialTheme.dimens.paddingMedium2),
                horizontalArrangement = Arrangement.Center
            ) {
                ElevatedButton(
                    border = BorderStroke(
                        MaterialTheme.dimens.thicknessExtraSmall,
                        MaterialTheme.colorScheme.onBackground
                    ),
                    onClick = {
                        loggedInViewModel.signOut()
                    }
                ) {
                    Text(stringResource(R.string.sign_out))
                }
            }
        }
    }
}

