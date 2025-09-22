package com.example.bookworm.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

val  LocalDimens = staticCompositionLocalOf { Dimensions() }

val MaterialTheme.dimens: Dimensions
    @Composable
    @ReadOnlyComposable
    get() = LocalDimens.current

private val DarkColorScheme = darkColorScheme(
    background = Black,
    onBackground = BrightWhite,
    primary = BlueWhite,
    onPrimary = Blue,
    secondary = PurpleWhite,
    onSecondary = BlueGray,
    tertiary = PinkWhite,
    onTertiary = DarkRed
)

private val LightColorScheme = lightColorScheme(
    background = YellowWhite,
    onBackground = LightBlack,
    primary = Lavender,
    onPrimary = LightGray,
    secondary = DarkLavender,
    onSecondary = DarkGray,
    tertiary = DarkPink,
    onTertiary = White
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

    CompositionLocalProvider(
        LocalDimens provides Dimensions()
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}