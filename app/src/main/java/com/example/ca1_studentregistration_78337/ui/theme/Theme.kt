package com.example.ca1_studentregistration_78337.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

// My own colors (blue and teal)
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF9DC1F2),
    onPrimary = Color(0xFF0B2A52),
    primaryContainer = Color(0xFF1F4E8C),
    onPrimaryContainer = Color(0xFFD6E4F7),
    secondary = Color(0xFF6FD6C8),
    secondaryContainer = Color(0xFF00403A),
    onSecondaryContainer = Color(0xFFD0F0EB),
    background = Color(0xFF10151C),
    onBackground = Color(0xFFE6EAF0),
    surface = Color(0xFF1A212B),
    onSurface = Color(0xFFE6EAF0)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1F4E8C),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD6E4F7),
    onPrimaryContainer = Color(0xFF0B2A52),
    secondary = Color(0xFF00897B),
    secondaryContainer = Color(0xFFD0F0EB),
    onSecondaryContainer = Color(0xFF00403A),
    background = Color(0xFFF5F7FA),
    onBackground = Color(0xFF1A1C20),
    surface = Color.White,
    onSurface = Color(0xFF1A1C20)
)

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */


@Composable
fun CA1_StudentRegistration_78337Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}