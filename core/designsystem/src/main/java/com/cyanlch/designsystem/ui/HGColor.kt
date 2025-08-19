package com.cyanlch.designsystem.ui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
object HGColor {
    
    // Primary Brand Colors
    val Primary50 = Color(0xFFF0F9FF)
    val Primary100 = Color(0xFFE0F2FE)
    val Primary200 = Color(0xFFBAE6FD)
    val Primary300 = Color(0xFF7DD3FC)
    val Primary400 = Color(0xFF38BDF8)
    val Primary500 = Color(0xFF0EA5E9)
    val Primary600 = Color(0xFF0284C7)
    val Primary700 = Color(0xFF0369A1)
    val Primary800 = Color(0xFF075985)
    val Primary900 = Color(0xFF0C4A6E)
    
    // Grayscale
    val Gray50 = Color(0xFFF9FAFB)
    val Gray100 = Color(0xFFF3F4F6)
    val Gray200 = Color(0xFFE5E7EB)
    val Gray300 = Color(0xFFD1D5DB)
    val Gray400 = Color(0xFF9CA3AF)
    val Gray500 = Color(0xFF6B7280)
    val Gray600 = Color(0xFF4B5563)
    val Gray700 = Color(0xFF374151)
    val Gray800 = Color(0xFF1F2937)
    val Gray900 = Color(0xFF111827)
    
    // Success
    val Success50 = Color(0xFFF0FDF4)
    val Success100 = Color(0xFFDCFCE7)
    val Success500 = Color(0xFF22C55E)
    val Success600 = Color(0xFF16A34A)
    val Success700 = Color(0xFF15803D)
    val Success900 = Color(0xFF14532D)
    
    // Warning
    val Warning50 = Color(0xFFFFFBEB)
    val Warning100 = Color(0xFFFEF3C7)
    val Warning500 = Color(0xFFF59E0B)
    val Warning600 = Color(0xFFD97706)
    val Warning700 = Color(0xFFB45309)
    val Warning900 = Color(0xFF78350F)
    
    // Error
    val Error50 = Color(0xFFFEF2F2)
    val Error100 = Color(0xFFFEE2E2)
    val Error500 = Color(0xFFEF4444)
    val Error600 = Color(0xFFDC2626)
    val Error700 = Color(0xFFB91C1C)
    val Error900 = Color(0xFF7F1D1D)
    
    // Background & Surface
    val Background = Color(0xFFFFFFFF)
    val BackgroundDark = Gray900
    val Surface = Color(0xFFFFFFFF)
    val SurfaceDark = Gray800
    val SurfaceVariant = Gray50
    val SurfaceVariantDark = Gray700
    
    // Text
    val OnBackground = Gray900
    val OnBackgroundDark = Gray50
    val OnSurface = Gray900
    val OnSurfaceDark = Gray50
    val OnSurfaceVariant = Gray600
    val OnSurfaceVariantDark = Gray400
}