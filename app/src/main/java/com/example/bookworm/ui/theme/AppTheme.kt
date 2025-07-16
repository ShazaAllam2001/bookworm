package com.example.bookworm.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import com.example.bookworm.R

private val darkColorScheme = AppColorScheme(
    background = Color(0xFF212121),
    onBackground = Color(0xFFFFFBFE),
    primary = Color(0xFFCDCDD0),
    onPrimary = Color(0xFF646262),
    secondary = Color(0xFF050CB0),
    onSecondary = Color(0xFF646262)
)

private val lightColorScheme = AppColorScheme(
    background = Color(0xFFFFFBFE),
    onBackground = Color(0xFF484648),
    primary = Color(0xFF000000),
    onPrimary = Color(0xFF9D9D9D),
    secondary = Color(0xFF3F51B5),
    onSecondary = Color(0xFFFFFFFF)
)

private val typography = AppTypography(
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.merienda)),
        fontWeight = FontWeight.Bold,
        fontSize = 60.sp
    ),
    titleNormal = TextStyle(
        fontFamily = FontFamily(Font(R.font.merienda)),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    body = TextStyle(
        fontFamily = FontFamily(Font(R.font.merienda)),
        fontSize = 16.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.merienda)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    labelNormal = TextStyle(
        fontFamily = FontFamily(Font(R.font.merienda)),
        fontSize = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.merienda)),
        fontSize = 12.sp
    )
)

private val shape = AppShape(
    container = RoundedCornerShape(50),
    button = RoundedCornerShape(50)
)

private val size = AppSize(
    large = 24.dp,
    medium = 16.dp,
    normal = 12.dp,
    small = 8.dp
)

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if(isDarkTheme) darkColorScheme else lightColorScheme
    CompositionLocalProvider(
        LocalAppColorScheme provides colorScheme,
        LocalAppTypography provides typography,
        LocalAppShape provides shape,
        LocalAppSize provides size,
        content = content
    )
}

object AppTheme {
    val colorScheme: AppColorScheme
        @Composable get() = LocalAppColorScheme.current

    val typography: AppTypography
        @Composable get() = LocalAppTypography.current

    val shape: AppShape
        @Composable get() = LocalAppShape.current

    val size: AppSize
        @Composable get() = LocalAppSize.current
}