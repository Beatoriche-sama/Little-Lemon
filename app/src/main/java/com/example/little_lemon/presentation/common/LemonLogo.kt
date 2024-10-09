package com.example.little_lemon.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.little_lemon.R

@Composable
fun LemonLogo(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(90.dp)
        .padding(top = 20.dp, bottom = 10.dp)
) {
    Image(
        painterResource(
            id = R.drawable.logo
        ),
        contentDescription = null,
        modifier = modifier
    )
}