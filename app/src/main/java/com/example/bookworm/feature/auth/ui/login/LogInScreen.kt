package com.example.bookworm.feature.auth.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bookworm.R
import com.example.bookworm.ui.theme.dimens

@Composable
fun LoginScreen(
    onNavigateToBrowser: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Book Worm \uD83D\uDCDA",
            style = MaterialTheme.typography.headlineLarge
        )
        HorizontalDivider(
            modifier = Modifier.padding(MaterialTheme.dimens.paddingLarge),
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.primary
        )
        Button(
            onClick = { onNavigateToBrowser() }
        ) {
            Row(
                modifier = Modifier.padding(MaterialTheme.dimens.paddingSmall),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.padding(end = MaterialTheme.dimens.paddingSmall),
                    painter = painterResource(R.drawable.icons8_google),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.login_with_google),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}