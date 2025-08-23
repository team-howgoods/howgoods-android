package com.cyanlch.designsystem.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

fun hgLightColorScheme() = lightColorScheme(
    primary = HGColors.primary,
    onPrimary = HGColors.white,
    secondary = HGColors.secondary,
    onSecondary = HGColors.gray900,
    background = HGColors.bgDefault,
    onBackground = HGColors.textDefault,
    surface = HGColors.bgDefault,
    onSurface = HGColors.textDefault,
    surfaceVariant = HGColors.bgAlternative,
    onSurfaceVariant = HGColors.textAlternative,
    outline = HGColors.lineDefault,
    outlineVariant = HGColors.lineAlternative,
    error = HGColors.warning,
    onError = HGColors.white,
    scrim = HGColors.dim,
)

fun hgDarkColorScheme() = darkColorScheme(
    primary = HGColors.primary,
    onPrimary = HGColors.white,
    secondary = HGColors.secondary,
    onSecondary = HGColors.gray900,
    background = HGColors.bgDefaultDark,
    onBackground = HGColors.textDefaultDark,
    surface = HGColors.bgDefaultDark,
    onSurface = HGColors.textDefaultDark,
    surfaceVariant = HGColors.bgAlternativeDark,
    onSurfaceVariant = HGColors.textAlternativeDark,
    outline = HGColors.lineDefaultDark,
    outlineVariant = HGColors.lineAlternativeDark,
    error = HGColors.warning,
    onError = HGColors.white,
    scrim = HGColors.dim,
)

@Immutable
data class HGExtraColors(
    val textAssistive: Color,
    val lineAssistive: Color,
    val lineDefault: Color,
    val warning: Color,
    val success: Color,
    val dim: Color,
)

fun hgExtraColors(dark: Boolean) = if (dark) {
    HGExtraColors(
        textAssistive = HGColors.textAssistiveDark,
        lineAssistive = HGColors.lineAssistiveDark,
        lineDefault = HGColors.lineDefault,
        warning = HGColors.warning,
        success = HGColors.success,
        dim = HGColors.dim,
    )
} else {
    HGExtraColors(
        textAssistive = HGColors.textAssistive,
        lineAssistive = HGColors.lineAssistive,
        lineDefault = HGColors.lineDefault,
        warning = HGColors.warning,
        success = HGColors.success,
        dim = HGColors.dim,
    )
}

val LocalHGExtraColors = staticCompositionLocalOf {
    hgExtraColors(dark = false)
}

@Composable
fun HGTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val scheme = if (darkTheme) hgDarkColorScheme() else hgLightColorScheme()
    val extra = hgExtraColors(darkTheme)

    CompositionLocalProvider(
        LocalHGExtraColors provides extra,
    ) {
        MaterialTheme(
            colorScheme = scheme,
            typography = HGTypography.toMaterialTypography(),
            content = content,
        )
    }
}

object HGTheme {
    val colors: ColorScheme
        @Composable
        get() = MaterialTheme.colorScheme

    val extras: HGExtraColors
        @Composable get() = LocalHGExtraColors.current

    val typography: Typography
        @Composable get() = MaterialTheme.typography
}
