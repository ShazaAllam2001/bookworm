package com.example.bookworm.feature.auth.ui.loggedin

import androidx.lifecycle.ViewModel
import com.example.bookworm.feature.auth.domain.model.auth.AuthResult
import com.example.bookworm.feature.auth.domain.usecase.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookworm.feature.auth.domain.model.preferences.PrefResult
import com.example.bookworm.feature.auth.domain.model.userData.UserDataResult
import com.example.bookworm.feature.auth.domain.usecase.EditCategoriesUseCase
import com.example.bookworm.feature.auth.domain.usecase.EditNameUseCase
import com.example.bookworm.feature.auth.domain.usecase.EditNotifyUseCase
import com.example.bookworm.feature.auth.domain.usecase.PreferencesUseCase
import com.example.bookworm.feature.auth.domain.usecase.UserDataUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoggedInViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val preferencesUseCase: PreferencesUseCase,
    private val userDataUseCase: UserDataUseCase,
    private val editNameUseCase: EditNameUseCase,
    private val editCategoriesUseCase: EditCategoriesUseCase,
    private val editNotifyUseCase: EditNotifyUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoggedInUiState())
    val uiState: StateFlow<LoggedInUiState> = _uiState.asStateFlow()

    fun signOut() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            when (val result = signOutUseCase()) {
                is AuthResult.Success -> {
                    _uiState.value = LoggedInUiState(
                        isSignedIn = false
                    )
                }
                is AuthResult.Error -> {
                    _uiState.value = LoggedInUiState(
                        errorMessage = result.message
                    )
                }
                AuthResult.Loading -> {}
            }
        }
    }

    fun checkPref() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            when (val result = preferencesUseCase()) {
                is PrefResult.Success -> {
                    val userData = _uiState.value.userData
                    _uiState.value = LoggedInUiState(
                        userData = userData,
                        userPref = result.user,
                        isSignedIn = true
                    )
                }
                is PrefResult.Error -> {
                    _uiState.value = LoggedInUiState(
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    fun getUserData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            when (val result = userDataUseCase()) {
                is UserDataResult.Success -> {
                    val userPref = _uiState.value.userPref
                    _uiState.value = LoggedInUiState(
                        userPref = userPref,
                        userData = result.user,
                        isSignedIn = true
                    )
                }
                is UserDataResult.Error -> {
                    _uiState.value = LoggedInUiState(
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    fun saveName(name: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            when (val result = editNameUseCase(name)) {
                is UserDataResult.Success -> {
                    val userPref = _uiState.value.userPref
                    _uiState.value = LoggedInUiState(
                        userPref = userPref,
                        userData = result.user,
                        isSignedIn = true
                    )
                }
                is UserDataResult.Error -> {
                    _uiState.value = LoggedInUiState(
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    fun saveCategories(categories: List<String>) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            when (val result = editCategoriesUseCase(categories)) {
                is UserDataResult.Success -> {
                    val userPref = _uiState.value.userPref
                    _uiState.value = LoggedInUiState(
                        userPref = userPref,
                        userData = result.user,
                        isSignedIn = true
                    )
                }
                is UserDataResult.Error -> {
                    _uiState.value = LoggedInUiState(
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    fun saveNotify(notify: Boolean) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            when (val result = editNotifyUseCase(notify)) {
                is UserDataResult.Success -> {
                    val userPref = _uiState.value.userPref
                    _uiState.value = LoggedInUiState(
                        userPref = userPref,
                        userData = result.user,
                        isSignedIn = true
                    )
                }
                is UserDataResult.Error -> {
                    _uiState.value = LoggedInUiState(
                        errorMessage = result.message
                    )
                }
            }
        }
    }
}