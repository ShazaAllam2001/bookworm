package com.example.bookworm.activities.login.modules.data

import android.util.Log
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.navigation.NavHostController
import com.example.bookworm.R
import com.example.bookworm.activities.login.modules.ui.Screens
import com.example.bookworm.activities.main.MainActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GetTokenResult
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await


class UserRepo(private val context: Context) {

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

    suspend fun getFirebaseToken(): String? {
        val user = Firebase.auth.currentUser
        return try {
            user?.getIdToken(true)?.await()?.token
        }
        catch (e: Exception) {
            null
        }
    }

}