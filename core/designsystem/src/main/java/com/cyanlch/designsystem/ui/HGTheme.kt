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
data class HGColorTokens(
    val textDefault: Color,
    val textAlternative: Color,
    val textAssistive: Color,

    val bgDefault: Color,
    val bgAlternative: Color,
    val bgDelete: Color,

    val lineAssistive: Color,
    val lineAlternative: Color,
    val lineDefault: Color,

    val warning: Color,
    val success: Color,
    val dim: Color,
    val brandPrimary: Color,
    val brandSecondary: Color,
)

fun hgColorsTokens(dark: Boolean) = if (dark) {
    HGColorTokens(
        textDefault = HGColors.textDefaultDark,
        textAlternative = HGColors.textAlternativeDark,
        textAssistive = HGColors.textAssistiveDark,
        bgDefault = HGColors.bgDefaultDark,
        bgAlternative = HGColors.bgAlternativeDark,
        bgDelete = HGColors.bgDeleteDark,
        lineDefault = HGColors.lineDefaultDark,
        lineAlternative = HGColors.lineAlternativeDark,
        lineAssistive = HGColors.lineAssistiveDark,
        warning = HGColors.warning,
        success = HGColors.success,
        dim = HGColors.dim,
        brandPrimary = HGColors.primary,
        brandSecondary = HGColors.secondary,
    )
} else {
    HGColorTokens(
        textDefault = HGColors.textDefault,
        textAlternative = HGColors.textAlternative,
        textAssistive = HGColors.textAssistive,
        bgDefault = HGColors.bgDefault,
        bgAlternative = HGColors.bgAlternative,
        bgDelete = HGColors.bgDelete,
        lineDefault = HGColors.lineDefault,
        lineAlternative = HGColors.lineAlternative,
        lineAssistive = HGColors.lineAssistive,
        warning = HGColors.warning,
        success = HGColors.success,
        dim = HGColors.dim,
        brandPrimary = HGColors.primary,
        brandSecondary = HGColors.secondary,
    )
}

val LocalHGColors = staticCompositionLocalOf<HGColorTokens> {
    error("No HGColors provided")
}

fun HGColorTokens.toMaterialColorScheme(isDark: Boolean) =
    if (isDark) darkColorScheme(
        primary = brandPrimary,
        onPrimary = HGColors.white,
        secondary = brandSecondary,
        onSecondary = HGColors.gray900,
        background = bgDefault,
        onBackground = textDefault,
        surface = bgDefault,
        onSurface = textDefault,
        surfaceVariant = bgAlternative,
        onSurfaceVariant = textAlternative,
        outline = lineDefault,
        outlineVariant = lineAlternative,
        error = warning,
        onError = HGColors.white,
        scrim = dim,
    ) else lightColorScheme(
        primary = brandPrimary,
        onPrimary = HGColors.white,
        secondary = brandSecondary,
        onSecondary = HGColors.gray900,
        background = bgDefault,
        onBackground = textDefault,
        surface = bgDefault,
        onSurface = textDefault,
        surfaceVariant = bgAlternative,
        onSurfaceVariant = textAlternative,
        outline = lineDefault,
        outlineVariant = lineAlternative,
        error = warning,
        onError = HGColors.white,
        scrim = dim,
    )

@Composable
fun HGTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val tokens = hgColorsTokens(darkTheme)
    val scheme = tokens.toMaterialColorScheme(darkTheme)

    CompositionLocalProvider(
        LocalHGColors provides tokens,
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

    val tokens: HGColorTokens
        @Composable get() = LocalHGColors.current

    val typography: Typography
        @Composable get() = MaterialTheme.typography
}
