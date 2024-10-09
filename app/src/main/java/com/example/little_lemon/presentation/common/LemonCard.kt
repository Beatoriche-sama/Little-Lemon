package com.example.little_lemon.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.little_lemon.R
import com.example.little_lemon.presentation.theme.Custom_Yellow

@Composable
fun CardContents() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Little Lemon",
            fontSize = 40.sp,
            color = Custom_Yellow,
            modifier = Modifier.padding(5.dp, 10.dp)
        )
        Row(
            modifier = Modifier.padding(0.dp, 10.dp)
        ) {
            CardText()
            Spacer(modifier = Modifier.width(40.dp))
            HeroImage()
        }
    }

}


@Composable
fun HeroImage() {
    Image(
        painterResource(
            id = R.drawable.hero_image
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        alignment = Alignment.BottomEnd,
        modifier = Modifier
            .size(width = 150.dp, height = 150.dp)
            .clip(RoundedCornerShape(16.dp))
    )


}

@Composable
fun CardText() {
    Column(
        modifier = Modifier.fillMaxWidth(0.5f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Chicago",
            color = Color.White,
            textAlign = TextAlign.Center,
        )
        Text(
            text = "We are a family-owned Mediterranean restaurant, " +
                    "focused on traditional recipes served with a modern twist.",
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}