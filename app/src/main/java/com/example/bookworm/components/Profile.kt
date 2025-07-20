package com.example.bookworm.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookworm.R


@Composable
fun Profile() {
    /*var name by rememberSaveable { mutableStateOf("Alexander Hipp") }
    var email by rememberSaveable { mutableStateOf("alexandar@mail.com") }
    var password = TextFieldState("1234")
    var preferences by rememberSaveable { mutableStateOf(listOf<String>()) }
    var notify by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
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
                        // edit name in the database
                    },
                    imageVector = Icons.Filled.Edit,
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
            singleLine = true
        )
        SecureTextField(
            state = password,
            label = { Text("Password") },
        )
        OutlinedTextField(
            value = email,
            onValueChange = {},
            label = { Text("Email") },
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
                onCheckedChange = { notify = it }
            )
        }

        ElevatedButton(
            onClick = {

            }
        ) {
            Text("Save")
            Icon(
                imageVector = Icons.Filled.Done,
                contentDescription = "Search Icon"
            )
        }
    }*/
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun ProfilePreview() {
    Profile()
}