package com.example.little_lemon.ui.screens.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.little_lemon.ui.theme.Custom_Light_Green
import java.util.Locale

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


@Composable
fun CategoryButton(
    categoryName: String,
    onSearch: () -> Unit
) {

    Button(
        onClick = { onSearch() },
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Custom_Light_Green,
        ),
        modifier = Modifier
            .padding(4.dp, 10.dp)
    ) {
        Text(
            text = categoryName.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.ROOT)
                else it.toString()
            }
        )
    }

}