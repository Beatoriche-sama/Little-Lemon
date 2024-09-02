package com.example.little_lemon.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.little_lemon.PrefManager
import com.example.little_lemon.nav.Destinations

import com.example.little_lemon.ui.screens.UIUtil.Fonts.karlaRegular
import com.example.little_lemon.ui.screens.UIUtil.Fonts.markaziRegular
import com.example.little_lemon.ui.screens.UIUtil.Logo.LemonLogo
import com.example.little_lemon.ui.theme.Custom_Green
import com.example.little_lemon.ui.theme.Custom_Pink

private val keysMap = mutableMapOf<String, String>()

@Composable
fun Onboarding(context: Context, navController: NavHostController) {
    Column {
        LemonLogo(null)
        Greeting()

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Personal information",
                fontFamily = markaziRegular,
                fontSize = 30.sp,
            )

            UserDataField(label = "First name", PrefManager.KEYS.NAME)
            UserDataField(label = "Last name", PrefManager.KEYS.SURNAME)
            UserDataField(label = "Email", PrefManager.KEYS.EMAIL)
        }


        RegisterButton(context = context, navController)
    }

}

@Composable
fun Greeting() {
    Text(
        text = "Let's get to know you",
        fontFamily = karlaRegular,
        fontSize = 30.sp,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = Custom_Green)
            .wrapContentHeight()
    )
}

@Composable
fun UserDataField(label: String, key: String) {
    Text(
        text = label,
        fontFamily = markaziRegular,
        fontSize = 30.sp
    )
    var value by remember { mutableStateOf("") }
    keysMap[key] = value

    TextField(
        value = value,
        onValueChange = {
            value = it
        },
        singleLine = true
    )
}

@Composable
fun RegisterButton(context: Context, navController: NavHostController) {
    Button(
        onClick = { registerUser(context, navController) },
        Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(containerColor = Custom_Pink)
    ) {
        Text(
            "Register",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
    }
}

private fun registerUser(
    context: Context,
    navController: NavHostController
) {
    val hasEmptyInputs = keysMap.filterValues { value -> value.isBlank() }.isNotEmpty()
    if (hasEmptyInputs) {
        Toast.makeText(
            context,
            "Registration unsuccessful. Please enter all data.",
            Toast.LENGTH_SHORT
        ).show()
    } else {
        PrefManager(context = context).saveData(keysMap)
        navController.navigate(Destinations.Home.path)
    }
}


@Preview
@Composable
fun OnboardingPreview() {

}
