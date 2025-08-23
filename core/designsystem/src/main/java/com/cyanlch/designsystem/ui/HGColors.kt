package com.cyanlch.designsystem.ui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
object HGColors {
    // Gray scale
    val white = Color(0xFFFFFFFF)
    val gray10 = Color(0xFFFAFAFA)
    val gray50 = Color(0xFFF3F3F3)
    val gray100 = Color(0xFFDDDDDD)
    val gray200 = Color(0xFFC6C6C6)
    val gray300 = Color(0xFFB0B0B0)
    val gray400 = Color(0xFF9B9B9B)
    val gray500 = Color(0xFF868686)
    val gray600 = Color(0xFF727272)
    val gray700 = Color(0xFF5E5E5E)
    val gray800 = Color(0xFF4B4B4B)
    val gray900 = Color(0xFF393939)
    val black = Color(0xFF000000)

    // Semantic (light)
    val bgDefault = white
    val bgAlternative = gray10
    val bgDelete = gray300
    val textDefault = gray900
    val textAlternative = gray700
    val textAssistive = gray500
    val lineDefault = gray200
    val lineAlternative = gray100
    val lineAssistive = gray50

    // Semantic (dark)
    val bgDefaultDark = black
    val bgAlternativeDark = gray900
    val bgDeleteDark = gray700
    val textDefaultDark = gray10
    val textAlternativeDark = gray300
    val textAssistiveDark = gray600
    val lineDefaultDark = gray700
    val lineAlternativeDark = gray800
    val lineAssistiveDark = gray900

    // System
    val warning = Color(0xFFFF6F6F)
    val success = Color(0xFF72B8FF)
    val dim = Color(0x80000000) // #00000080

    // Brand
    val primary = Color(0xFF27C68A)
    val secondary = Color(0xFFD1EEFE)
}
