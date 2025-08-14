package com.example.bookworm.sharedPref.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookworm.sharedPref.data.PrefRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class PrefViewModel(private val prefRepo: PrefRepo): ViewModel() {
    val token = prefRepo.tokenFlow
    val name = prefRepo.nameFlow
    val email = prefRepo.mailFlow
    val photo = prefRepo.photoFlow

    fun saveToken(token: String) {
        viewModelScope.launch {
            prefRepo.setToken(token)
        }
    }

    fun saveUser(
        name: String,
        email: String,
        photo: String
    ) {
        viewModelScope.launch {
            prefRepo.setUserInfo(name, email, photo)
        }
    }
}