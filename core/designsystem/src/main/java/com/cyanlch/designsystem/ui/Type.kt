package com.cyanlch.designsystem.ui

import androidx.compose.material3.Typography

/**
 * MaterialTheme.typography에 기본 스타일을 제공합니다.
 * 각 슬롯은 HGTypography에 정의된 스타일을 참조하여 앱 전체의 일관성을 보장합니다.
 */
val Typography = Typography(
    bodyLarge = HGTypography.body1Normal,
    bodyMedium = HGTypography.label1Normal,
    bodySmall = HGTypography.label2,

    headlineLarge = HGTypography.title1,
    headlineMedium = HGTypography.title2,
    headlineSmall = HGTypography.heading1,

    titleLarge = HGTypography.heading2,
    titleMedium = HGTypography.headline1,
    titleSmall = HGTypography.headline2,

    labelLarge = HGTypography.label1Normal,
    labelMedium = HGTypography.caption1,
    labelSmall = HGTypography.caption2,
)
