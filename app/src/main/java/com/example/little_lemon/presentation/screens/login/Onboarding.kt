package com.example.little_lemon.presentation.screens.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.little_lemon.PrefManager
import com.example.little_lemon.presentation.common.DataField
import com.example.little_lemon.presentation.common.LemonLogo
import com.example.little_lemon.presentation.theme.Custom_Green
import com.example.little_lemon.presentation.theme.Custom_Pink

@Composable
fun Onboarding(
    context: Context,
    onNavigateHome: () -> Unit
) {

    val keysMap = mutableMapOf<String, String>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        LemonLogo()

        // Greeting
        Text(
            text = "Let's get to know you",
            fontSize = 30.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(color = Custom_Green)
                .wrapContentHeight()
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Personal information",
                fontSize = 30.sp,
            )

            DataField(label = "First name", onValueChange = {
                keysMap[PrefManager.KEYS.NAME] = it
            })
            DataField(label = "Last name", onValueChange = {
                keysMap[PrefManager.KEYS.SURNAME] = it
            })
            DataField(label = "Email", onValueChange = {
                keysMap[PrefManager.KEYS.EMAIL] = it
            })
        }


        // Register button
        Button(
            onClick = {
                val hasEmptyInputs = keysMap.filterValues { value -> value.isBlank() }.isNotEmpty()
                if (hasEmptyInputs) {
                    Toast.makeText(
                        context,
                        "Registration unsuccessful. Please enter all data.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    PrefManager(context).saveData(keysMap)
                    onNavigateHome()
                }
            },
            modifier = Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp),
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

}



