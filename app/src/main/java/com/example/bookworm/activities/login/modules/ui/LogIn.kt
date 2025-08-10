package com.example.bookworm.activities.login.modules.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import com.example.bookworm.R
import com.example.bookworm.sharedPref.viewModel.PrefViewModel
import com.example.bookworm.activities.login.modules.viewModel.UserViewModel


@Composable
fun LogIn(
    navController: NavHostController = rememberNavController(),
    userViewModel: UserViewModel,
    prefViewModel: PrefViewModel
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var showPassword by rememberSaveable { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.email)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_email_24),
                    contentDescription = "Username"
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
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.password)) },
            singleLine = true,
            visualTransformation = if(showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_lock_24),
                    contentDescription = "Username"
                )
            },
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable{
                        showPassword = !showPassword
                    },
                    painter = if (showPassword) painterResource(R.drawable.visibility_28dp) else painterResource(
                        R.drawable.visibility_off_28dp),
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
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedButton(
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
            onClick = {
                if (email != "" && password != "") {
                    userViewModel.login(email, password)
                }
            }
        ) {
            Text(
                stringResource(R.string.log_in),
                style = MaterialTheme.typography.labelLarge
            )
        }
        HorizontalDivider(
            modifier = Modifier.padding(20.dp),
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.primary
        )
        Button(
            onClick = {
                scope.launch{
                    val logged = userViewModel.loginWithGoogle()
                    if (logged) {
                        val token = userViewModel.getToken()
                        Log.d("token, google", token?:"null")
                        /*if (token != null) {
                            prefViewModel.saveToken(token)
                        }*/
                    }
                }
            }
        ) {
            Row(
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
        Spacer(modifier = Modifier.height(20.dp))
        GoToSignUp(navController = navController)
    }
}

@Composable
fun GoToSignUp(navController: NavHostController) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            stringResource(R.string.don_t_have_an_account),
            style = MaterialTheme.typography.labelSmall
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            modifier = Modifier.clickable {
                navController.navigate(Screens.Signup.route) {
                    popUpTo(Screens.Signup.route)
                    launchSingleTop = true
                }
            },
            text = stringResource(R.string.sign_up),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelSmall
        )
    }
}