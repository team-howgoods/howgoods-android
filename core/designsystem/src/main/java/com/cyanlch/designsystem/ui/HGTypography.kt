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
    val title1 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 48.sp,
        letterSpacing = (-0.02).em
    )
    val title2 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 38.sp,
        letterSpacing = (-0.01).em
    )

    // Heading
    val heading1 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 30.sp,
        letterSpacing = (-0.0002).em
    )
    val heading2 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.012).em
    )

    // Headline
    val headline1 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 26.sp,
        letterSpacing = (-0.002).em
    )
    val headline2 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.01.em
    )

    // Body
    val body1Normal = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.002).em
    )
    val body1Reading = body1Normal.copy(
        lineHeight = 26.sp
    )
    val body2Reading = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.002.em
    )

    // Label
    val label1Normal = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.002).em
    )
    val label1Reading = label1Normal.copy(
        lineHeight = 22.sp
    )
    val label2 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.008.em
    )

    // Caption
    val caption1 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.012.em
    )
    val caption2 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.02.em
    )
}

fun HGTypography.toMaterialTypography(): Typography {
    return Typography(
        displayLarge = title1,       // 32sp
        displayMedium = title2,      // 28sp
        headlineLarge = heading1,    // 22sp
        headlineMedium = heading2,   // 20sp
        headlineSmall = headline1,   // 18sp
        titleLarge = headline2,      // 17sp
        titleMedium = body1Normal,   // 16sp
        titleSmall = body2Reading,   // 15sp
        bodyLarge = body1Normal,     // 16sp
        bodyMedium = label1Normal,   // 14sp
        bodySmall = label2,          // 13sp
        labelLarge = label1Normal,   // 14sp
        labelMedium = caption1,      // 12sp
        labelSmall = caption2        // 11sp
    )
}
