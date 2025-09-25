package com.example.bookworm.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.bookworm.R

val PublicSans = FontFamily(Font(R.font.public_sans))

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = PublicSans,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = PublicSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = PublicSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    titleLarge = TextStyle(
        fontFamily = PublicSans,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp
    ),
    titleMedium = TextStyle(
        fontFamily = PublicSans,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    titleSmall = TextStyle(
        fontFamily = PublicSans,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = PublicSans,
        fontSize = 16.sp
    ),
    labelLarge = TextStyle(
        fontFamily = PublicSans,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = PublicSans,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 21.sp
    ),
    labelSmall = TextStyle(
        fontFamily = PublicSans,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)