package com.example.bookworm.activities.main.modules.ui.settings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.bookworm.R
import com.example.bookworm.activities.main.modules.ui.loading.LoadingIndicator
import com.example.bookworm.sharedPref.viewModel.PrefViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage


@Composable
fun Profile(prefViewModel: PrefViewModel) {
    var photo by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var editName by rememberSaveable { mutableStateOf(false) }
    var email  by rememberSaveable { mutableStateOf("") }
    var preference by rememberSaveable { mutableStateOf("") }
    val preferences = remember { mutableStateListOf("") }
    var notify by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val user = prefViewModel.readPreferences()
        photo = user.photoUrl
        name = user.displayName
        email = user.email
        notify = user.notify
        preferences.clear()
        preferences.addAll(user.preferences)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CoilImage(
            modifier = Modifier.size(200.dp)
                .clip(CircleShape),
            imageModel = { photo },
            loading = { LoadingIndicator() },
            failure = { Text("Failed to load Profile Avatar") },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center
            )
        )
        TextField(
            modifier = Modifier.fillMaxWidth(0.5f),
            value = name,
            onValueChange = { name = it },
            singleLine = true,
            textStyle = MaterialTheme.typography.labelLarge,
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable{
                        editName = !editName
                        // edit name in the database
                    },
                    imageVector = if (editName) Icons.Filled.Done else Icons.Filled.Edit,
                    contentDescription = "Edit name"
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedContainerColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = RoundedCornerShape(50)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.email)) },
            readOnly = true,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                focusedContainerColor = MaterialTheme.colorScheme.onSecondary
            )
        )
        OutlinedTextField(
            value = preference,
            onValueChange = { preference = it },
            label = { Text(stringResource(R.string.preference)) },
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable{
                        preferences.add(preference)
                        // add preference in the database
                    },
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Add preference "
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                focusedContainerColor = MaterialTheme.colorScheme.onSecondary
            )
        )
        LazyColumn(
            modifier = Modifier.padding(top = 15.dp)
        ) {
            items(preferences.size) { index ->
                Text(
                    preferences[index],
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(R.string.enable_notifications),
                style = MaterialTheme.typography.labelMedium
            )
            Checkbox(
                checked = notify,
                onCheckedChange = {
                    notify = it
                }
            )
        }
    }
}
