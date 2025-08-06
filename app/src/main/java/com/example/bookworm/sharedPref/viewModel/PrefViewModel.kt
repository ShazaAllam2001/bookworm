package com.example.bookworm.sharedPref.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookworm.sharedPref.data.PrefRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class PrefViewModel(private val prefRepo: PrefRepo): ViewModel() {
    val token: StateFlow<String> = prefRepo.tokenFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        ""
    )

    fun saveToken(token: String) {
        viewModelScope.launch {
            prefRepo.setToken(token)
        }
    }
}