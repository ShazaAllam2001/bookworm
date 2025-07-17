package com.example.bookworm.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


private val DarkColorScheme = darkColorScheme(
    background = Color(0xFF212121),
    onBackground = Color(0xFFFFFBFE),
    primary = Color(0xFFCDCDD0),
    onPrimary = Color(0xFF646262),
    secondary = Color(0xFF050CB0),
    onSecondary = Color(0xFF646262)
)

private val LightColorScheme = lightColorScheme(
    background = Color(0xFFFFFBFE),
    onBackground = Color(0xFF484648),
    primary = Color(0xFF000000),
    onPrimary = Color(0xFF9D9D9D),
    secondary = Color(0xFF3F51B5),
    onSecondary = Color(0xFFFFFFFF)
)

@Composable
fun BookWormTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}