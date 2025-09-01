package com.example.bookworm.common.core

import android.util.Log
import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BookWorm : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        Log.d("BookWorm Application", "Application created")
    }
}