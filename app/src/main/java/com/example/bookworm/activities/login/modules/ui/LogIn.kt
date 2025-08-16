package com.example.bookworm.activities.login.modules.ui

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.example.bookworm.R
import com.example.bookworm.activities.login.modules.viewModel.UserViewModel


@Composable
fun LogIn(
    userViewModel: UserViewModel
) {
    val scope = rememberCoroutineScope()

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
            modifier = Modifier.padding(25.dp),
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.primary
        )
        Button(
            onClick = {
                scope.launch {
                    userViewModel.launchAuthBrowser()
                }
            }
        ) {
            Row(
                modifier = Modifier.padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.padding(end = 5.dp),
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