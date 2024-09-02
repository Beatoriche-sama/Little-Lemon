package com.example.little_lemon.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
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
import androidx.navigation.NavHostController
import com.example.little_lemon.PrefManager
import com.example.little_lemon.R
import com.example.little_lemon.nav.Destinations
import com.example.little_lemon.ui.screens.UIUtil.Fonts.markaziRegular
import com.example.little_lemon.ui.screens.UIUtil.Logo.LemonLogo
import com.example.little_lemon.ui.theme.Custom_Yellow

@Composable
fun Profile(context: Context, navHostController: NavHostController) {
    val prefManager = PrefManager(context)
    val name = prefManager.getData(PrefManager.KEYS.NAME)
    val surname = prefManager.getData(PrefManager.KEYS.SURNAME)
    val email = prefManager.getData(PrefManager.KEYS.EMAIL)

    Column {
        LemonLogo(null)
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
        LogOut(navHostController, prefManager)
    }
}

@Composable
fun ProfileInfo(name: String, surname: String, email: String) {
    Column (
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ){
        Text(
            text = "Profile information:",
            fontSize = 45.sp,
            fontFamily = markaziRegular,
            modifier = Modifier.padding(bottom = 15.dp)
        )
        CompositionLocalProvider(
            LocalTextStyle provides TextStyle(
                fontSize = 40.sp, fontFamily = markaziRegular
            )
        ) {
            Text(text = "Name: $name")
            Text(text = "Surname: $surname")
            Text(text = "Surname: $email")
        }
    }
}

@Composable
fun LogOut(navHostController: NavHostController, prefManager: PrefManager) {
    Button(
        onClick = { logOut(navHostController, prefManager) },
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

private fun logOut(navHostController: NavHostController, prefManager: PrefManager) {
    navHostController.navigate(Destinations.Onboarding.path)
    prefManager.clearData()
}

@Preview
@Composable
fun ProfilePreview() {
    Text(text = "Profile")
}
