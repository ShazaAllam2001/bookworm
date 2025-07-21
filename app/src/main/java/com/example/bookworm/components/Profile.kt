package com.example.bookworm.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookworm.R


@Composable
fun Profile() {
    var name by rememberSaveable { mutableStateOf("Alexander Hipp") }
    var editName by rememberSaveable { mutableStateOf(false) }
    var email by rememberSaveable { mutableStateOf("alexandar@mail.com") }
    var password by rememberSaveable { mutableStateOf("1234") }
    var showPassword by rememberSaveable { mutableStateOf(false) }
    var preference by rememberSaveable { mutableStateOf("") }
    val preferences = remember { mutableStateListOf("Fiction", "Thriller") }
    var notify by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape),
            painter = painterResource(R.drawable.alexander_unsplash),
            contentDescription = "Profile Avatar"
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
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.password)) },
            singleLine = true,
            visualTransformation = if(showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable{
                        showPassword = !showPassword
                    },
                    painter = if (showPassword) painterResource(R.drawable.visibility_28dp) else painterResource(R.drawable.visibility_off_28dp),
                    contentDescription = "Password Visibility"
                )
            },
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
                onCheckedChange = { notify = it }
            )
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun ProfilePreview() {
    Profile()
}