package com.sildian.apps.restofinder.designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
internal actual fun colorScheme(darkTheme: Boolean, dynamicColor: Boolean): ColorScheme =
    when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }