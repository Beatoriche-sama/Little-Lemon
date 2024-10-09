package com.example.little_lemon.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(onSearch: (s: String) -> Unit) {
    var searchPhrase by remember { mutableStateOf("") }

    TextField(
        value = searchPhrase,
        onValueChange = {
            searchPhrase = it
            onSearch(it)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.LightGray,
            focusedContainerColor = Color.White
        ),
        placeholder = {
            Text(
                "Enter search phrase",
                textAlign = TextAlign.Center
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp)
    )
}


