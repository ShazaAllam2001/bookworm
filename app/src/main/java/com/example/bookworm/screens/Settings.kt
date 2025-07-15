package com.example.bookworm.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.bookworm.ui.theme.AppTheme


@Composable
fun Settings() {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(AppTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Settings",
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            color = AppTheme.colorScheme.primary
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun SettingsPreview() {
    Settings()
}
