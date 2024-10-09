package com.example.little_lemon.presentation.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import java.util.Locale

@Composable
fun CategoryButton(
    categoryName: String,
    onSearch: () -> Unit
) {

    Button(
        onClick = { onSearch() },
        shape = RectangleShape,
        modifier = Modifier
            .padding(3.dp, 10.dp)
    ) {
        Text(
            text = categoryName.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.ROOT)
                else it.toString()
            }
        )
    }

}