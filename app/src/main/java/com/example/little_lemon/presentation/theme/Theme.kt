package com.example.little_lemon.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Custom_Green,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Custom_Light_Green,
    secondary = Corn_Silk,
    tertiary = Pink40,
    background = Anise_Liquor,
    //onBackground = Vanilla,
    // surface = Lemon_Background,
    onPrimary = Color.White,
    onSecondary = Vanilla,
    onTertiary = Custom_Green,

    //onSurface = Custom_Green,

)

@Composable
fun LittleLemonTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    /*val colorScheme = when {
        dynamicColor -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }*/

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}