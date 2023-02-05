package com.hntr.sample.kreactive.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val Mystic = Color(0xFFD7DFE8)
val CatskillWhite = Color(0xFFF2F4F8)
val MineShaft = Color(0xFF323232)
val Thunder = Color(0xFF232323)
val Mako = Color(0xFF404449)
val ElectricViolet = Color(0xFF6200EE)

val DarkColorScheme = darkColorScheme(
    primary = Thunder,
    secondary = MineShaft,
    tertiary = Mako,
    surface = MineShaft,
    onPrimary = Color.White
)

val LightColorScheme = lightColorScheme(
    primary = ElectricViolet,
    secondary = CatskillWhite,
    tertiary = Mystic,
    surface = CatskillWhite,
    onPrimary = Color.White
)

@Composable
fun createColorScheme(dynamicColor: Boolean = true): ColorScheme {
    val darkTheme = isSystemInDarkTheme()
    return when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
}