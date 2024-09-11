package com.taman.silmebagcalculator.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFA8D5A2), // Pastel Green for app bar and primary elements
    onPrimary = Color(0xFF004D40), // Dark Green for text and icons on primary color
    primaryContainer = Color(0xFFC1E3C1), // Soft Mint for containers
    onPrimaryContainer = Color(0xFF003B46), // Forest Green for text on containers
    secondary = Color(0xFFC1E3C1), // Soft Mint for secondary elements
    onSecondary = Color(0xFF003B46), // Forest Green for text on secondary elements
    background = Color(0xFFFFFFFF), // White for background
    onBackground = Color(0xFF004D40), // Dark Green for text on white background
    surface = Color(0xFFE8F5E9), // Very Light Green for surfaces
    onSurface = Color(0xFF004D40), // Dark Green for text on surfaces
    error = Color(0xFFFF6F61), // Light Coral for error states
    onError = Color.White, // White text on light coral error states
    outline = Color(0xFFD0D0D0), // Light Grey for outlines
    inverseSurface = Color(0xFF004D40), // Dark Green for dark mode surfaces
    inverseOnSurface = Color(0xFFE8F5E9), // Very Light Green text on dark mode surfaces
    inversePrimary = Color(0xFF004D40) // Dark Green for inverse primary

)

@Composable
fun SilmeBagCalculatorTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}