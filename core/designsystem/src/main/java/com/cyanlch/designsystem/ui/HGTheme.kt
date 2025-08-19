package com.cyanlch.designsystem.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class HGColors(
    val primary: Color
)

private val LightHGColors = HGColors(
    primary = HGColor.Primary600
)

private val DarkHGColors = HGColors(
    primary = HGColor.Primary400
)

private val LightMaterialColors = lightColorScheme(
    primary = HGColor.Primary600,
    onPrimary = Color.White,
    primaryContainer = HGColor.Primary100,
    onPrimaryContainer = HGColor.Primary900,
    background = HGColor.Background,
    onBackground = HGColor.OnBackground,
    surface = HGColor.Surface,
    onSurface = HGColor.OnSurface,
    surfaceVariant = HGColor.SurfaceVariant,
    onSurfaceVariant = HGColor.OnSurfaceVariant,
    error = HGColor.Error600,
    onError = Color.White,
)

private val DarkMaterialColors = darkColorScheme(
    primary = HGColor.Primary400,
    onPrimary = HGColor.Primary900,
    primaryContainer = HGColor.Primary800,
    onPrimaryContainer = HGColor.Primary100,
    background = HGColor.BackgroundDark,
    onBackground = HGColor.OnBackgroundDark,
    surface = HGColor.SurfaceDark,
    onSurface = HGColor.OnSurfaceDark,
    surfaceVariant = HGColor.SurfaceVariantDark,
    onSurfaceVariant = HGColor.OnSurfaceVariantDark,
    error = HGColor.Error500,
    onError = HGColor.Error900,
)


val LocalHGColors = staticCompositionLocalOf { LightHGColors }

@Composable
fun HGTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkMaterialColors else LightMaterialColors
    val hgColors = if (darkTheme) DarkHGColors else LightHGColors

    CompositionLocalProvider(
        LocalHGColors provides hgColors
    ) {
        MaterialTheme(
            colorScheme = colors,
            typography = HGTypography.toMaterialTypography(),
            content = content
        )
    }
}

object HGTheme {
    val colors: HGColors
        @Composable
        get() = LocalHGColors.current
        
    val typography
        @Composable
        get() = HGTypography
}