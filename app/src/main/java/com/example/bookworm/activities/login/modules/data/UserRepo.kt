package com.example.bookworm.activities.login.modules.data

import android.util.Log
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.navigation.NavHostController
import com.example.bookworm.BuildConfig
import com.example.bookworm.R
import com.example.bookworm.activities.login.modules.ui.Screens
import com.example.bookworm.activities.main.MainActivity
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException


const val firebaseClientId = BuildConfig.FIREBASE_CLIENT_ID

class UserRepo(private val context: Context) {
    private val credentialsManager = CredentialManager.create(context)
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun signup(email: String, password: String, navController: NavHostController) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context,
                        context.getString(R.string.sign_up_successful_please_login),
                        Toast.LENGTH_SHORT).show()
                    navController.navigate(Screens.Login.route) {
                        popUpTo(Screens.Signup.route) { inclusive = true }
                    }
                } else {
                    Toast.makeText(context,
                        task.exception?.message ?: context.getString(R.string.sign_up_failed),
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun login(email: String, password: String) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.login_successful),
                        Toast.LENGTH_SHORT
                    ).show()
                    context.startActivity(Intent(context, MainActivity::class.java))
                } else {
                    Toast.makeText(
                        context,
                        task.exception?.message ?: context.getString(R.string.login_failed),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun signOut() {
        Firebase.auth.signOut()
    }

    /* Signing in with Google */
    suspend fun loginWithGoogle(): Boolean {
        try {
            val result = getGoogleCredential()
            val loggedIn = handleGoogleSignIn(result)
            if (loggedIn) {
                context.startActivity(Intent(context, MainActivity::class.java))
                return true
            }
        }
        catch (e: Exception) {
            if (e is CancellationException) throw e
            Log.d("Google Signing In Exception", e.message?:"")
        }
        return false
    }

    suspend fun signOutWithGoogle() {
        credentialsManager.clearCredentialState(
            ClearCredentialStateRequest()
        )
        firebaseAuth.signOut()
    }

    suspend fun getIdToken(): String? {
        val user = Firebase.auth.currentUser
        return user?.getIdToken(true)?.await()?.token
    }

    private suspend fun getGoogleCredential(): GetCredentialResponse {
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(
                GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(firebaseClientId)
                    .setAutoSelectEnabled(false)
                    .build()
            )
            .build()
        return credentialsManager.getCredential(context = context, request = request)
    }

    private suspend fun handleGoogleSignIn(request: GetCredentialResponse): Boolean {
        val credential = request.credential
        if (credential is CustomCredential &&
            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            try {
                val tokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val authCredential = GoogleAuthProvider.getCredential(tokenCredential.idToken, null)
                val authResult = firebaseAuth.signInWithCredential(authCredential).await()
                return authResult != null
            }
            catch(e: GoogleIdTokenParsingException) {
                Log.d("GoogleIdTokenParsingException", e.message?:"")
                return false
            }
        }
        else {
            Log.d("GoogleIdTokenParsingException", "Credential is not GoogleIdTokenCredential")
            return false
        }
    }
}