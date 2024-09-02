package com.example.little_lemon.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.little_lemon.data.MenuEntity
import com.example.little_lemon.ui.theme.Custom_Green

@Composable
fun MealCard(menuEntity: MenuEntity) {
    Card(
        shape = RectangleShape,
        border = BorderStroke(2.dp, Custom_Green),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Row {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(5.dp, 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = menuEntity.title,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = menuEntity.description,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "$ ${menuEntity.price}",
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.width(50.dp))
            MealIcon(url = menuEntity.image)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MealIcon(url: String) {

    val context = LocalContext.current
    GlideImage(
        model = url,
        contentDescription = null,
        modifier = Modifier
            .size(150.dp, 150.dp),
        contentScale = ContentScale.Crop
    ) {
        it.thumbnail(Glide.with(context).asDrawable().load(url))
    }
}