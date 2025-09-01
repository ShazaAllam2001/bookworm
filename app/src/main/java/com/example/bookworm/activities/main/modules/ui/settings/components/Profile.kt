package com.example.bookworm.activities.main.modules.ui.settings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import com.example.bookworm.R
import com.example.bookworm.activities.login.modules.viewModel.UserViewModel
import com.example.bookworm.common.ui.loading.LoadingIndicator
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage


@Composable
fun Profile(
    userViewModel: UserViewModel,
    updateForYou: () -> Unit
) {
    var uid by rememberSaveable { mutableStateOf("") }
    var photo by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var editName by rememberSaveable { mutableStateOf(false) }
    var email  by rememberSaveable { mutableStateOf("") }
    var category by rememberSaveable { mutableStateOf("") }
    val categories = remember { mutableStateListOf("") }
    var notify by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(userViewModel.userData) {
        val user = userViewModel.readPreferences()
        photo = user.photoUrl
        email = user.email
        uid = user.uid
        if (userViewModel.userData == null) {
            userViewModel.readUser(user.uid)
        }
        else {
            name = userViewModel.userData!!.displayName
            notify = userViewModel.userData!!.notify
            categories.clear()
            categories.addAll(userViewModel.userData!!.categories)
        }
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
                        if (uid != "")
                            userViewModel.saveName(uid, name)
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
            value = category,
            onValueChange = { category = it },
            label = { Text(stringResource(R.string.preference)) },
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable{
                        categories.add(category)
                        category = ""
                        if (uid != "")
                            userViewModel.saveCategories(uid, categories)
                        updateForYou()
                    },
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Add Category"
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                focusedContainerColor = MaterialTheme.colorScheme.onSecondary
            )
        )
        PreferencesColumn(
            categories = categories,
            onItemDismissed = { category ->
                categories.remove(category)
                if (uid != "")
                    userViewModel.saveCategories(uid, categories)
                updateForYou()
            }
        )
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
                    if (uid != "")
                        userViewModel.saveNotify(uid, notify)
                }
            )
        }
    }
}