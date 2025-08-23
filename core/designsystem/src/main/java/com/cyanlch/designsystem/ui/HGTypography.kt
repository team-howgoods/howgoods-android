package com.cyanlch.designsystem.ui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Typography

@Immutable
object HGTypography {
    // Title
    val title1SemiBold = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 48.sp
    )
    val title1Medium = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp,
        lineHeight = 44.sp
    )

    val title2SemiBold = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 38.sp
    )
    val title2Medium = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        lineHeight = 36.sp
    )

    // Heading
    val heading22SemiBold = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 30.sp
    )
    val heading20SemiBold = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp
    )

    // Headline
    val headlineSemiBold = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 26.sp
    )
    val headlineMedium = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )

    // Body
    val body1SemiBold = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 26.sp
    )
    val body1Medium = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 26.sp
    )

    val body2SemiBold = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 24.sp
    )
    val body2Medium = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 24.sp
    )

    // Label
    val label1SemiBold = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 22.sp
    )
    val label1Medium = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 22.sp
    )

    val label2SemiBold = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        lineHeight = 18.sp
    )

    // Caption
    val caption12SemiBold = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )
    val caption11SemiBold = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp,
        lineHeight = 14.sp
    )
}

fun HGTypography.toMaterialTypography(): Typography = Typography(
    displayLarge  = title1SemiBold,     // 32 / 48
    displayMedium = title2SemiBold,     // 28 / 38
    headlineLarge = heading22SemiBold,  // 22 / 30
    headlineMedium= heading20SemiBold,  // 20 / 28
    headlineSmall = headlineSemiBold,   // 18 / 26
    titleLarge    = headlineMedium,     // 16 / 24
    titleMedium   = body1Medium,        // 16 / 26
    titleSmall    = body2Medium,        // 15 / 24
    bodyLarge     = body1Medium,        // 16 / 26
    bodyMedium    = body2Medium,        // 15 / 24
    bodySmall     = label1Medium,       // 14 / 22
    labelLarge    = label1SemiBold,     // 14 / 22
    labelMedium   = label2SemiBold,     // 13 / 18
    labelSmall    = caption12SemiBold   // 12 / 16  (필요시 11로 교체)
)

