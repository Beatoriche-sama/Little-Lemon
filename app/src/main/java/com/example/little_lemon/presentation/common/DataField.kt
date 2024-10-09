package com.example.little_lemon.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun DataField(
    label: String,
    onValueChange: (String) -> Unit
) {
    Text(
        text = label,
        fontSize = 30.sp
    )
    var value by remember { mutableStateOf("") }

    TextField(
        modifier = Modifier.fillMaxWidth(0.8f),
        value = value,
        onValueChange = {
            value = it
            onValueChange(it)
        },
        singleLine = true
    )
}