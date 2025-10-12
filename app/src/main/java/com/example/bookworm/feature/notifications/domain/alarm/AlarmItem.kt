package com.example.bookworm.feature.notifications.domain.alarm

import com.example.bookworm.feature.notifications.ui.viewModel.NotifyState
import java.time.LocalDateTime

data class AlarmItem(
    val time: LocalDateTime,
    val notifyState: NotifyState
)