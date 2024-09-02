package com.example.little_lemon.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.little_lemon.R

class UIUtil {
    object Fonts {
        val karlaRegular = FontFamily(Font(R.font.karla_regular))
        val markaziRegular = FontFamily(Font(R.font.markazi_text_regular))
    }

    object Logo {
        @Composable
        fun LemonLogo(modifier: Modifier?) {

            val logoModifier: Modifier =
                modifier ?: Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(top = 20.dp, bottom = 10.dp)

            Image(
                painterResource(
                    id = R.drawable.logo
                ),
                contentDescription = null,
                modifier = logoModifier
            )
        }
    }

}