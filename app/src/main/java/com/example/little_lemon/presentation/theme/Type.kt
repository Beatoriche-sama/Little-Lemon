package com.example.little_lemon.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.little_lemon.R


object AppFont{
    val karlaRegular = FontFamily(Font(R.font.karla_regular))
    val markaziRegular = FontFamily(Font(R.font.markazi_text_regular))
}
// Set of Material typography styles to start with

private val defaultTypography = Typography()

val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = AppFont.karlaRegular),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = AppFont.karlaRegular),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = AppFont.karlaRegular),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = AppFont.markaziRegular),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = AppFont.karlaRegular),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = AppFont.karlaRegular),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = AppFont.karlaRegular),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = AppFont.karlaRegular),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = AppFont.karlaRegular),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = AppFont.karlaRegular),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = AppFont.karlaRegular),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = AppFont.karlaRegular),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = AppFont.karlaRegular),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = AppFont.karlaRegular),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = AppFont.karlaRegular)
)