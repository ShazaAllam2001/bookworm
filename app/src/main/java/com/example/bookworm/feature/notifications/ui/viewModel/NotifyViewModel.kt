package com.example.bookworm.feature.notifications.ui.viewModel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.bookworm.feature.user.domain.usecase.UserDataUseCase
import com.example.bookworm.feature.books.domain.model.BooksResult
import com.example.bookworm.feature.books.domain.usecase.SearchBooksUseCase
import com.example.bookworm.feature.user.domain.model.userData.UserDataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotifyViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val notificationBuilder: NotificationCompat.Builder,
    private val notificationManager: NotificationManagerCompat,
    private val userDataUseCase: UserDataUseCase,
    private val searchBooksUseCase: SearchBooksUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(NotifyState())
    val uiState: StateFlow<NotifyState> = _uiState.asStateFlow()

    fun getRecommendation() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = userDataUseCase()) {
                is UserDataResult.Success -> {
                    val category = result.user.categories.random()
                    when (val books = searchBooksUseCase("$category+subject")) {
                        is BooksResult.Success -> {
                            val book = books.message.random()
                            _uiState.value = NotifyState(
                                text = book.volumeInfo.description,
                                bookCover = book.volumeInfo.imageLinks?.smallThumbnail
                            )
                            showRecommendation()
                        }
                        is BooksResult.Error -> {
                            _uiState.value = NotifyState(
                                errorMessage = books.message
                            )
                        }
                    }
                }
                is UserDataResult.Error -> {
                    _uiState.value = NotifyState(
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    private fun showRecommendation() {
        val icon = Glide.with(context).asBitmap().load(_uiState.value.bookCover).submit().get()
        val notificationBuilder = notificationBuilder
            .setContentText(_uiState.value.text)
            .setLargeIcon(icon)
            .build()

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
            == PackageManager.PERMISSION_GRANTED)
        {
            notificationManager.notify(2001, notificationBuilder)
        }
    }
}