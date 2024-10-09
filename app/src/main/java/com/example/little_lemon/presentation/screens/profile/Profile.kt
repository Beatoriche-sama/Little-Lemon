package com.example.little_lemon.presentation.screens.profile

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.little_lemon.PrefManager
import com.example.little_lemon.R
import com.example.little_lemon.presentation.common.LemonLogo
import com.example.little_lemon.presentation.theme.Custom_Yellow

@Composable
fun Profile(
    context: Context,
    onNavigateToOnboarding: () -> Unit
) {

    val prefManager = PrefManager(context)
    val name = prefManager.getData(PrefManager.KEYS.NAME)
    val surname = prefManager.getData(PrefManager.KEYS.SURNAME)
    val email = prefManager.getData(PrefManager.KEYS.EMAIL)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LemonLogo()
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally)
        )

        ProfileInfo(name, surname, email)

        Button(
            onClick = {
                prefManager.clearData()
                onNavigateToOnboarding()
            },
            Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Custom_Yellow)
        ) {
            Text(
                text = "Log out",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
    }
}

@Composable
private fun ProfileInfo(name: String, surname: String, email: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Profile information:",
            fontSize = 45.sp,
            modifier = Modifier.padding(bottom = 15.dp)
        )
        CompositionLocalProvider(
            LocalTextStyle provides TextStyle(
                fontSize = 40.sp
            )
        ) {
            Text(text = "Name: $name")
            Text(text = "Surname: $surname")
            Text(text = "Surname: $email")
        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    Text(text = "Profile")
}
