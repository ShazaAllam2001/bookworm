package com.example.bookworm.activities.main.modules.ui.settings

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookworm.R
import com.example.bookworm.activities.main.modules.ui.settings.components.Profile
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@Composable
fun Settings() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = stringResource(R.string.settings),
            style = MaterialTheme.typography.titleLarge
        )
        Profile()
        ElevatedButton(
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
            onClick = {
                Firebase.auth.signOut()
            }
        ) {
            Text(stringResource(R.string.sign_out))
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun SettingsPreview() {
    Settings()
}
